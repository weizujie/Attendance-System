package com.example.mapper;

import com.example.entity.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherMapper {

    List<Teacher> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int deleteTeacher(List<Integer> ids);

    int addTeacher(Teacher teacher);

    Teacher findById(Integer tid);

    int editTeacher(Teacher teacher);

    Teacher findByTeacher(Teacher teacher);

    int editPwdByTeacher(Teacher teacher);
}
