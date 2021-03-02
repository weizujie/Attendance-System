package com.example.service;

import com.example.entity.Student;
import com.example.util.PageBean;

import java.util.List;
import java.util.Map;


public interface StudentService {
    PageBean<Student> queryPage(Map<String, Object> paramMap);

    int deleteStudent(List<Integer> ids);

    int addStudent(Student student);

    Student findById(Integer sid);

    int editStudent(Student student);

    Student login(Student student);

    boolean isStudentByClazzId(Integer next);

    int editPwdByStudent(Student student);

    int findByName(String username);
}
