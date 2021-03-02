package com.example.service;

import com.example.entity.Teacher;
import com.example.util.PageBean;

import java.util.List;
import java.util.Map;


public interface TeacherService {

    PageBean<Teacher> queryPage(Map<String, Object> paramMap);

    int deleteTeacher(List<Integer> ids);

    int addTeacher(Teacher aTeacher);

    Teacher getById(Integer tid);

    int updateTeacher(Teacher aTeacher);

    Teacher login(Teacher aTeacher);

    int updatePasswordByTeacher(Teacher aTeacher);

}
