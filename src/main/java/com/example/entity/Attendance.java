package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 考勤 实体类
 *
 * @author weizujie
 * @date 2021/03/02
 */
@Data
@TableName("s_attendance")
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 考勤 id
     */
    @TableId(value = "id", type = IdType.AUTO)
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
