package com.example.controller;

import com.example.entity.Admin;
import com.example.entity.Student;
import com.example.entity.Teacher;
import com.example.service.AdminService;
import com.example.service.StudentService;
import com.example.service.TeacherService;
import com.example.util.AjaxResult;
import com.example.constant.UserTypeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    /**
     * 跳转后台主页
     */
    @GetMapping("/index")
    public String index() {
        return "/system/index";
    }

    /**
     * 跳转登录界面
     */
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    /**
     * 跳转修改密码页面
     */
    @GetMapping("/updatePassword")
    public String updatePassword() {
        return "updatePassword";
    }

    /**
     * 登出
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/login";
    }

    /**
     * 登录表单提交 校验
     */
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult login(String username, String password, String type, HttpSession session) {

        /**
         * 空值判断
         */
        AjaxResult ajaxResult = new AjaxResult();
        if (StringUtils.isEmpty(username)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请填写用户名");
            return ajaxResult;
        }
        if (StringUtils.isEmpty(password)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请填写密码");
            return ajaxResult;
        }

        // TODO:将三个角色合成一张表 s_user 并且设置一个字段 user_type 来存储用户的类型
        // 数据库校验
        switch (type) {
            case "1": { // 管理员
                Admin admin = new Admin();
                admin.setPassword(password);
                admin.setUsername(username);
                Admin dbAdmin = adminService.login(admin);
                if (StringUtils.isEmpty(dbAdmin)) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                // 将登录对象存到 session 对象中
                session.setAttribute(UserTypeConstant.ADMIN, dbAdmin);
                session.setAttribute(UserTypeConstant.USERTYPE, "1");
                break;
            }
            case "2": { // 学生
                Student student = new Student();
                student.setPassword(password);
                student.setUsername(username);
                Student dbStudent = studentService.login(student);
                if (StringUtils.isEmpty(dbStudent)) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或密码错误");
                    return ajaxResult;
                }
                // 将登录对象存到 session 对象中
                ajaxResult.setSuccess(true);
                session.setAttribute(UserTypeConstant.STUDENT, dbStudent);
                session.setAttribute(UserTypeConstant.USERTYPE, "2");
                break;
            }
            case "3": { // 教师
                Teacher teacher = new Teacher();
                teacher.setPassword(password);
                teacher.setUsername(username);
                Teacher dbTeacher = teacherService.login(teacher);
                if (StringUtils.isEmpty(dbTeacher)) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或密码错误");
                    return ajaxResult;
                }
                // 将登录对象存到 session 对象中
                ajaxResult.setSuccess(true);
                session.setAttribute(UserTypeConstant.TEACHER, dbTeacher);
                session.setAttribute(UserTypeConstant.USERTYPE, "3");
                break;
            }
        }
        return ajaxResult;
    }

    /**
     * 修改密码
     */
    @PostMapping("/updatePassword")
    @ResponseBody
    public AjaxResult editPassword(String password, String newPassword, HttpSession session) {

        AjaxResult ajaxResult = new AjaxResult();
        // 从 session 中获取登录用户的类型
        String usertype = (String) session.getAttribute(UserTypeConstant.USERTYPE);
        // 判断是哪类用户登录
        if ("1".equals(usertype)) {
            // 从 session 中获取管理员对象
            Admin admin = (Admin) session.getAttribute(UserTypeConstant.ADMIN);
            if (!password.equals(admin.getPassword())) {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
                return ajaxResult;
            }
            // 设置新密码
            admin.setPassword(newPassword);
            try {
                // count: 影响行数，一般值为 1，除非有一模一样的用户数据
                int count = adminService.updatePasswordByAdmin(admin);
                if (count > 0) {
                    ajaxResult.setSuccess(true);
                    ajaxResult.setMessage("修改成功,请重新登录");
                } else {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("修改失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        }

        if ("2".equals(usertype)) {
            //从 session 中获取学生对象
            Student student = (Student) session.getAttribute(UserTypeConstant.STUDENT);
            if (!password.equals(student.getPassword())) {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
                return ajaxResult;
            }
            student.setPassword(newPassword);
            try {
                int count = studentService.editPwdByStudent(student);
                if (count > 0) {
                    ajaxResult.setSuccess(true);
                    ajaxResult.setMessage("修改成功,请重新登录");
                } else {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("修改失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        }

        if ("3".equals(usertype)) {
            // 从 session 中获取教师对象
            Teacher teacher = (Teacher) session.getAttribute(UserTypeConstant.TEACHER);
            if (!password.equals(teacher.getPassword())) {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
                return ajaxResult;
            }
            teacher.setPassword(newPassword);
            try {
                int count = teacherService.updatePasswordByTeacher(teacher);
                if (count > 0) {
                    ajaxResult.setSuccess(true);
                    ajaxResult.setMessage("修改成功,请重新登录");
                } else {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("修改失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        }
        return ajaxResult;
    }

}
