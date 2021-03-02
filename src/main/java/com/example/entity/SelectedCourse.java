package com.example.entity;

import lombok.Data;

/**
 * 选课信息 实体类
 *
 * @author weizujie
 * @date 2021/03/02
 */
@Data
public class SelectedCourse {

    /**
     * 选课信息 id
     */
    private Integer id;

    /**
     * 选课的学生 id
     */
    private Integer studentId;

    /**
     * 选的课程的 id
     */
    private Integer courseId;
}
