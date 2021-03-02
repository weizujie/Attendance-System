package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Teacher;
import com.example.util.AjaxResult;
import com.example.util.PageBean;

import java.util.List;
import java.util.Map;


public interface TeacherService {

    int deleteTeacher(List<Integer> ids);

    int addTeacher(Teacher aTeacher);

    Teacher getById(Integer tid);

    int updateTeacher(Teacher aTeacher);

    Teacher login(Teacher aTeacher);

    int updatePasswordByTeacher(Teacher aTeacher);

    Object selectList(Page<Teacher> teacherPage);
}
