package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 学生 实体类
 *
 * @author weizujie
 * @date 2021/03/02
 */
@Data
@TableName("s_student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学号
     */
    private String sn;

    /**
     * 学生登录用户名
     */
    private String username;

    /**
     * 学生登录密码
     */
    private String password;

    /**
     * 学生所在的班级 id
     */
    private Integer clazzId;

    /**
     * 学生性别
     */
    private String sex = "男";

    /**
     * 学生联系方式
     */
    private String mobile;

}
