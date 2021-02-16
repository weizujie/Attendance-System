package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 课程 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    private int id;

    private String name;

    private int teacherId;

    private String courseDate;

    private int selectedNum = 0; // 已选人数

    private int maxNum = 50; // 课程最大选课人数

    private String info;

}
