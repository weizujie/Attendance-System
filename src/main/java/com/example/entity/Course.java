package com.example.entity;

import lombok.Data;


/**
 * 课程 实体类
 *
 * @author weizujie
 * @date 2021/03/02
 */
@Data
public class Course {

    /**
     * 课程 id
     */
    private int id;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 授课教师
     */
    private int teacherId;

    /**
     * 上课时间
     */
    private String courseDate;

    /**
     * 已选人数
     */
    private int selectedNum = 0;

    /**
     * 最大可选人数
     */
    private int maxNum = 50;

    /**
     * 课程信息
     */
    private String info;

}
