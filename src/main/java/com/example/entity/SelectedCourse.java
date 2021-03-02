package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 选课信息 实体类
 *
 * @author weizujie
 * @date 2021/03/02
 */
@Data
@TableName("s_selected_course")
public class SelectedCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 选课信息 id
     */
    @TableId(value = "id", type = IdType.AUTO)
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
