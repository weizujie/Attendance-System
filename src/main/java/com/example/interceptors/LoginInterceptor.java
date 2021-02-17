package com.example.interceptors;

import com.example.entity.Admin;
import com.example.entity.Student;
import com.example.entity.Teacher;
import com.example.util.Const;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     * 检查是否存在 session,具体拦截哪些页面可以在 config/WebMvcConfig.java 中查看
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        Admin admin = (Admin) request.getSession().getAttribute(Const.ADMIN);

        Teacher teacher = (Teacher) request.getSession().getAttribute(Const.TEACHER);

        Student student = (Student) request.getSession().getAttribute(Const.STUDENT);

        if (!StringUtils.isEmpty(admin) || !StringUtils.isEmpty(teacher) || !StringUtils.isEmpty(student)) {
            return true;
        }

        response.sendRedirect(request.getContextPath() + "/system/login");
        return false;
    }

}
