package com.example.controller;

import com.example.constant.UserTypeConstant;
import com.example.entity.Student;
import com.example.service.SelectedCourseService;
import com.example.service.StudentService;
import com.example.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 学生管理 前端控制器
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SelectedCourseService selectedCourseService;

    /**
     * 跳转学生列表页面
     */
    @GetMapping("/student_list")
    public String studentList() {
        return "/student/studentList";
    }

    /**
     * 异步加载学生列表
     */
    @RequestMapping("/getStudentList")
    @ResponseBody
    public Object getStudentList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                                 @RequestParam(value = "clazzid", defaultValue = "0") String clazzid, String studentName, String from, HttpSession session) {
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("pageno", page);
        paramMap.put("pagesize", rows);
        if (!StringUtils.isEmpty(studentName)) paramMap.put("username", studentName);
        if (!"0".equals(clazzid)) paramMap.put("clazzid", clazzid);

        //判断是老师还是学生权限
        Student student = (Student) session.getAttribute(UserTypeConstant.STUDENT);
        if (!StringUtils.isEmpty(student)) {
            //是学生权限，只能查询自己的信息
            paramMap.put("studentid", student.getId());
        }

        PageBean<Student> pageBean = studentService.queryPage(paramMap);
        if (!StringUtils.isEmpty(from) && "combox".equals(from)) {
            return pageBean.getDatas();
        } else {
            Map<String, Object> result = new HashMap();
            result.put("total", pageBean.getTotalsize());
            result.put("rows", pageBean.getDatas());
            return result;
        }
    }

    /**
     * 删除学生
     */
    @PostMapping("/deleteStudent")
    @ResponseBody
    public AjaxResult deleteStudent(Data data) {

        AjaxResult ajaxResult = new AjaxResult();
        try {
            List<Integer> ids = data.getIds();
            Iterator<Integer> iterator = ids.iterator();
            while (iterator.hasNext()) {  //判断是否存在课程关联学生
                if (!selectedCourseService.isStudentId(iterator.next())) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("无法删除,存在课程关联学生");
                    return ajaxResult;
                }
            }

            int count = studentService.deleteStudent(ids);
            if (count > 0) {
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("全部删除成功");
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
     * 添加学生
     */
    @RequestMapping("/addStudent")
    @ResponseBody
    public AjaxResult addStudent(Student student) {

        AjaxResult ajaxResult = new AjaxResult();
        // 设置随机学号
        student.setSn(SnGenerateUtil.generateSn(student.getClazzId()));

        //保存学生信息到数据库
        try {
            int count = studentService.addStudent(student);
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

        return ajaxResult;
    }

    /**
     * 修改学生信息
     */
    @PostMapping("/editStudent")
    @ResponseBody
    public AjaxResult editStudent(Student student) {

        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = studentService.editStudent(student);
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
