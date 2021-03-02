package com.example.entity;

import lombok.Data;

/**
 * 学生 实体类
 *
 * @author weizujie
 * @date 2021/03/02
 */
@Data
public class Student {

    /**
     * 学生 id
     */
    private int id;

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
    private int clazzId;

    /**
     * 学生性别
     */
    private String sex = "男";

    /**
     * 学生联系方式
     */
    private String mobile;

}
