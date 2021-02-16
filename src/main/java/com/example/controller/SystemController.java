package com.example.controller;

import com.example.entity.Admin;
import com.example.entity.Student;
import com.example.entity.Teacher;
import com.example.service.AdminService;
import com.example.service.StudentService;
import com.example.service.TeacherService;
import com.example.util.AjaxResult;
import com.example.util.Const;
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
     * 跳转登录界面
     */
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/personalView")
    public String personalView() {
        return "/system/personalView";
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
     * 跳转后台主页
     */
    @GetMapping("/index")
    public String index() {
        return "/system/index";
    }

    /**
     * 获取图片地址
     */
    @GetMapping("/getPhoto")
    @ResponseBody
    public AjaxResult getPhoto(@RequestParam(value = "sid", defaultValue = "0") Integer sid,
                               @RequestParam(value = "tid", defaultValue = "0") Integer tid) {
        AjaxResult ajaxResult = new AjaxResult();
        if (sid != 0) {
            Student student = studentService.findById(sid);
            ajaxResult.setImgurl(student.getAvatar());
            return ajaxResult;
        }
        if (tid != 0) {
            Teacher teacher = teacherService.findById(tid);
            ajaxResult.setImgurl(teacher.getAvatar());
            return ajaxResult;
        }

        return ajaxResult;
    }


    /**
     * 登录表单提交 校验
     */
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult login(String username, String password, String type, HttpSession session) {

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

        // 数据库校验
        switch (type) {
            case "1": { // 管理员
                Admin admin = new Admin();
                admin.setPassword(password);
                admin.setUsername(username);
                Admin ad = adminService.findByAdmin(admin);
                if (StringUtils.isEmpty(ad)) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                session.setAttribute(Const.ADMIN, ad);
                session.setAttribute(Const.USERTYPE, "1");
                break;
            }
            case "2": { // 学生
                Student student = new Student();
                student.setPassword(password);
                student.setUsername(username);
                Student st = studentService.findByStudent(student);
                if (StringUtils.isEmpty(st)) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                session.setAttribute(Const.STUDENT, st);
                session.setAttribute(Const.USERTYPE, "2");
                break;
            }
            case "3": { // 老师
                Teacher teacher = new Teacher();
                teacher.setPassword(password);
                teacher.setUsername(username);
                Teacher tr = teacherService.findByTeacher(teacher);
                if (StringUtils.isEmpty(tr)) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                session.setAttribute(Const.TEACHER, tr);
                session.setAttribute(Const.USERTYPE, "3");
                break;
            }
        }
        return ajaxResult;
    }

    /**
     * 修改密码
     */
    @PostMapping("/editPassword")
    @ResponseBody
    public AjaxResult editPassword(String password, String newpassword, HttpSession session) {
        AjaxResult ajaxResult = new AjaxResult();
        String usertype = (String) session.getAttribute(Const.USERTYPE);
        if (usertype.equals("1")) {
            //管理员
            Admin admin = (Admin) session.getAttribute(Const.ADMIN);
            if (!password.equals(admin.getPassword())) {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
                return ajaxResult;
            }
            admin.setPassword(newpassword);
            try {
                int count = adminService.editPwdByAdmin(admin);
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
        if (usertype.equals("2")) {
            //学生
            Student student = (Student) session.getAttribute(Const.STUDENT);
            if (!password.equals(student.getPassword())) {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
                return ajaxResult;
            }
            student.setPassword(newpassword);
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
        if (usertype.equals("3")) {
            //教师
            Teacher teacher = (Teacher) session.getAttribute(Const.TEACHER);
            if (!password.equals(teacher.getPassword())) {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
                return ajaxResult;
            }
            teacher.setPassword(newpassword);
            try {
                int count = teacherService.editPwdByTeacher(teacher);
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
