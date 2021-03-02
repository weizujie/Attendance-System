package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.constant.UserTypeConstant;
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

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
    public Object getTeacherList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "100") Integer rows,
            @RequestParam(value = "clazzId", defaultValue = "0") String clazzId,
            String teacherName, HttpSession session) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageno", page);
        paramMap.put("pagesize", rows);
        if (!StringUtils.isEmpty(teacherName)) paramMap.put("username", teacherName);
        if (!clazzId.equals("0")) paramMap.put("clazzid", clazzId);

        //判断是老师还是学生权限
        Teacher teacher = (Teacher) session.getAttribute(UserTypeConstant.TEACHER);
        if (!StringUtils.isEmpty(teacher)) {
            //是老师权限，只能查询自己的信息
            paramMap.put("teacherId", teacher.getId());
        }

        // 分页
        Map<String, Object> result = new HashMap<>();
        Page<Teacher> teacherPage = new Page<>(page, rows);
        Object list = teacherService.selectList(teacherPage);
        result.put("total", teacherPage.getTotal());
        result.put("rows", list);
        return result;

    }

    /**
     * 删除教师
     */
    @PostMapping("/deleteTeacher")
    @ResponseBody
    public AjaxResult deleteTeacher(Data data) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
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
    public AjaxResult addTeacher(Teacher teacher) {

        AjaxResult ajaxResult = new AjaxResult();
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

    /**
     * 修改教师信息
     */
    @PostMapping("/editTeacher")
    @ResponseBody
    public AjaxResult editTeacher(Teacher teacher) {

        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = teacherService.updateTeacher(teacher);
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
