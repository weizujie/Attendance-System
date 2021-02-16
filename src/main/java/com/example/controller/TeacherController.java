package com.example.controller;

import com.example.entity.Teacher;
import com.example.service.TeacherService;
import com.example.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 教师管理 前端控制器
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    @RequestMapping("/teacher_list")
    public String teacherList() {
        return "/teacher/teacherList";
    }

    /**
     * 异步加载 老师数据列表
     */
    @PostMapping("/getTeacherList")
    @ResponseBody
    public Object getTeacherList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                                 String teacherName,
                                 @RequestParam(value = "clazzid", defaultValue = "0") String clazzid, String from, HttpSession session) {
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("pageno", page);
        paramMap.put("pagesize", rows);
        if (!StringUtils.isEmpty(teacherName)) paramMap.put("username", teacherName);
        if (!clazzid.equals("0")) paramMap.put("clazzid", clazzid);

        //判断是老师还是学生权限
        Teacher teacher = (Teacher) session.getAttribute(Const.TEACHER);
        if (!StringUtils.isEmpty(teacher)) {
            //是老师权限，只能查询自己的信息
            paramMap.put("teacherid", teacher.getId());
        }

        PageBean<Teacher> pageBean = teacherService.queryPage(paramMap);
        if (!StringUtils.isEmpty(from) && from.equals("combox")) {
            return pageBean.getDatas();
        } else {
            Map<String, Object> result = new HashMap();
            result.put("total", pageBean.getTotalsize());
            result.put("rows", pageBean.getDatas());
            return result;
        }
    }

    /**
     * 删除教师
     */
    @PostMapping("/deleteTeacher")
    @ResponseBody
    public AjaxResult deleteTeacher(Data data) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            File fileDir = UploadUtil.getImgDirFile();
            for (Integer id : data.getIds()) {
                Teacher byId = teacherService.findById(id);
                if (!byId.getAvatar().isEmpty()) {
                    File file = new File(fileDir.getAbsolutePath() + File.separator + byId.getAvatar());
                    file.delete();
                }
            }
            int count = teacherService.deleteTeacher(data.getIds());
            if (count > 0) {
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("删除成功");
            } else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("删除失败");
        }
        return ajaxResult;
    }

    /**
     * 添加教师
     */
    @RequestMapping("/addTeacher")
    @ResponseBody
    public AjaxResult addTeacher(@RequestParam("file") MultipartFile[] files, Teacher teacher) throws IOException {

        AjaxResult ajaxResult = new AjaxResult();
        teacher.setSn(SnGenerateUtil.generateTeacherSn(teacher.getClazzId()));

        // TODO: 将头像上传至 OSS
        // 存放上传图片的文件夹
        File fileDir = UploadUtil.getImgDirFile();
        for (MultipartFile fileImg : files) {

            // 获取文件名
            String extName = fileImg.getOriginalFilename().substring(fileImg.getOriginalFilename().lastIndexOf("."));
            String uuidName = UUID.randomUUID().toString();

            try {
                // 构建真实的文件路径
                File newFile = new File(fileDir.getAbsolutePath() + File.separator + uuidName + extName);

                // 上传图片到 -》 “绝对路径”
                fileImg.transferTo(newFile);

            } catch (IOException e) {
                e.printStackTrace();
            }
            teacher.setAvatar(uuidName + extName);
        }

        //保存学生信息到数据库
        try {
            int count = teacherService.addTeacher(teacher);
            if (count > 0) {
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("保存成功");
            } else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("保存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("保存失败");
        }

        ajaxResult.setSuccess(true);
        return ajaxResult;
    }

    @PostMapping("/editTeacher")
    @ResponseBody
    public AjaxResult editTeacher(@RequestParam("file") MultipartFile[] files, Teacher teacher) {
        AjaxResult ajaxResult = new AjaxResult();

        // 存放上传图片的文件夹
        File fileDir = UploadUtil.getImgDirFile();
        for (MultipartFile fileImg : files) {

            String name = fileImg.getOriginalFilename();
            if ("".equals(name)) {
                break;
            }

            // 拿到文件名
            String extName = fileImg.getOriginalFilename().substring(fileImg.getOriginalFilename().lastIndexOf("."));
            String uuidName = UUID.randomUUID().toString();

            try {
                // 构建真实的文件路径
                File newFile = new File(fileDir.getAbsolutePath() + File.separator + uuidName + extName);
                // 上传图片到 -》 “绝对路径”
                fileImg.transferTo(newFile);

                Teacher byId = teacherService.findById(teacher.getId());
                File file = new File(fileDir.getAbsolutePath() + File.separator + byId.getAvatar());
                if (file != null) {
                    file.delete();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            teacher.setAvatar(uuidName + extName);
        }

        try {
            int count = teacherService.editTeacher(teacher);
            if (count > 0) {
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("修改成功");
            } else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }
        return ajaxResult;
    }
}
