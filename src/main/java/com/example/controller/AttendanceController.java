package com.example.controller;

import com.example.entity.Attendance;
import com.example.entity.Course;
import com.example.entity.SelectedCourse;
import com.example.entity.Student;
import com.example.service.AttendanceService;
import com.example.service.CourseService;
import com.example.service.SelectedCourseService;
import com.example.util.AjaxResult;
import com.example.util.Const;
import com.example.util.DateFormatUtil;
import com.example.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 考勤管理 前端控制器
 */
@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private SelectedCourseService selectedCourseService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/attendance_list")
    public String attendanceList() {
        return "/attendance/attendanceList";
    }


    /**
     * 异步获取考勤列表数据
     */
    @RequestMapping("/getAttendanceList")
    @ResponseBody
    public Object getAttendanceList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                                    @RequestParam(value = "studentid", defaultValue = "0") String studentid,
                                    @RequestParam(value = "courseid", defaultValue = "0") String courseid,
                                    String type, String date, String from, HttpSession session) {
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("pageno", page);
        paramMap.put("pagesize", rows);
        if (!studentid.equals("0")) paramMap.put("studentid", studentid);
        if (!courseid.equals("0")) paramMap.put("courseid", courseid);
        if (!StringUtils.isEmpty(type)) paramMap.put("type", type);
        if (!StringUtils.isEmpty(date)) paramMap.put("date", date);

        //判断是老师还是学生权限
        Student student = (Student) session.getAttribute(Const.STUDENT);
        if (!StringUtils.isEmpty(student)) {
            //是学生权限，只能查询自己的信息
            paramMap.put("studentid", student.getId());
        }
        PageBean<Attendance> pageBean = attendanceService.queryPage(paramMap);
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
     * 通过 选课信息中的课程id 查询 学生所选择的课程
     */
    @RequestMapping("/getStudentSelectedCourseList")
    @ResponseBody
    public Object getStudentSelectedCourseList(@RequestParam(value = "studentid", defaultValue = "0") String studentid) {
        //通过学生id 查询 选课信息
        List<SelectedCourse> selectedCourseList = selectedCourseService.getAllBySid(Integer.parseInt(studentid));
        //通过 选课信息中的课程id 查询 学生所选择的课程
        List<Integer> ids = new ArrayList<>();
        for (SelectedCourse selectedCourse : selectedCourseList) {
            ids.add(selectedCourse.getCourseId());
        }
        List<Course> courseList = courseService.getCourseById(ids);
        return courseList;
    }


    /**
     * 添加考勤签到
     */
    @PostMapping("/addAttendance")
    @ResponseBody
    public AjaxResult addAttendance(Attendance attendance) {
        AjaxResult ajaxResult = new AjaxResult();
        attendance.setDate(DateFormatUtil.getFormatDate(new Date(), "yyyy-MM-dd"));
        //判断是否已签到
        if (attendanceService.isAttendance(attendance)) {
            //true为已签到
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("已签到，请勿重复签到！");
        } else {
            int count = attendanceService.addAttendance(attendance);
            if (count > 0) {
                //签到成功
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("签到成功");
            } else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("系统错误，请重新签到");
            }
        }
        return ajaxResult;
    }

    /**
     * 删除考勤签到
     */
    @PostMapping("/deleteAttendance")
    @ResponseBody
    public AjaxResult deleteAttendance(Integer id) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = attendanceService.deleteAttendance(id);
            if (count > 0) {
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("删除成功");
            } else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("删除失败");
            }
        } catch (Exception e) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("系统异常,请重试");
            e.printStackTrace();
        }
        return ajaxResult;
    }
}
