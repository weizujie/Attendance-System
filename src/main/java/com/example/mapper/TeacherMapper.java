package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherMapper extends BaseMapper<Teacher> {

    List<Teacher> queryList(Map<String, Object> paramMap);

    Integer count(Map<String, Object> paramMap);

    int deleteTeacher(List<Integer> ids);

    int addTeacher(Teacher aTeacher);

    Teacher getById(Integer tid);

    int updateTeacher(Teacher aTeacher);

    Teacher login(Teacher aTeacher);

    int updatePasswordByTeacher(Teacher aTeacher);
}
