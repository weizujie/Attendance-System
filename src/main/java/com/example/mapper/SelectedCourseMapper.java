package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.SelectedCourse;

import java.util.List;
import java.util.Map;

public interface SelectedCourseMapper extends BaseMapper<SelectedCourse> {
    
    List<SelectedCourse> queryList(Map<String, Object> paramMap);

    Integer count(Map<String, Object> paramMap);

    int addSelectedCourse(SelectedCourse selectedCourse);

    SelectedCourse findBySelectedCourse(SelectedCourse selectedCourse);

    SelectedCourse findById(Integer id);

    int deleteSelectedCourse(Integer id);

    List<SelectedCourse> isStudentId(int id);

    List<SelectedCourse> getAllBySid(int studentid);
}
