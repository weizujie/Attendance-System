package com.example.entity;

import lombok.Data;

/**
 * 考勤 实体类
 *
 * @author weizujie
 * @date 2021/03/02
 */
@Data
public class Attendance {

    /**
     * 考勤 id
     */
    private Integer id;

    /**
     * 课程 id
     */
    private Integer courseId;

    /**
     * 学生 id
     */
    private Integer studentId;

    /**
     * 考勤类型
     */
    private String type;

    /**
     * 考勤时间
     */
    private String date;

}
