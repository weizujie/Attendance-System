package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


/**
 * 课程 实体类
 *
 * @author weizujie
 * @date 2021/03/02
 */
@Data
@TableName("s_course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 授课教师
     */
    private Integer teacherId;

    /**
     * 上课时间
     */
    private String courseDate;

    /**
     * 已选人数
     */
    private Integer selectedNum = 0;

    /**
     * 最大可选人数
     */
    private Integer maxNum = 50;

    /**
     * 课程信息
     */
    private String info;

}
