package com.example.mapper;

import com.example.entity.Student;

import java.util.List;
import java.util.Map;


public interface StudenetMapper {

    List<Student> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int deleteStudent(List<Integer> ids);

    int addStudent(Student student);

    Student findById(Integer sid);

    int editStudent(Student student);

    Student findByStudent(Student student);

    List<Student> isStudentByClazzId(Integer id);

    int editPwdByStudent(Student student);

    int findByName(String name);
}
