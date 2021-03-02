package com.example.service;

import com.example.entity.Attendance;
import com.example.util.PageBean;

import java.util.Map;

public interface AttendanceService {

    PageBean<Attendance> queryPage(Map<String, Object> paramMap);

    boolean isAttendance(Attendance attendance);

    int addAttendance(Attendance attendance);

    int deleteAttendance(Integer id);

}
