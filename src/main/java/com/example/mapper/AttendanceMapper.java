package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Attendance;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface AttendanceMapper extends BaseMapper<Attendance> {

    List<Attendance> queryList(Map<String, Object> paramMap);

    Integer count(Map<String, Object> paramMap);

    int addAttendance(Attendance aAttendance);

    Attendance isAttendance(Attendance aAttendance);

    int deleteAttendance(Integer id);
}
