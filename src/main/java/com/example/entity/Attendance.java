package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 考勤 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

    private Integer id;

    private Integer courseId;

    private Integer studentId;

    private String type;

    private String date;

}
