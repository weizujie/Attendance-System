package com.example.entity;

import lombok.Data;

/**
 * 教师 实体类
 *
 * @author weizujie
 * @date 2021/03/02
 */
@Data
public class Teacher {

    /**
     * 教师 id
     */
    private int id;

    /**
     * 教师工号（随机生成）
     */
    private String sn;

    /**
     * 教师登录名
     */
    private String username;

    /**
     * 教师登录密码
     */
    private String password;

    /**
     * 班级 id
     */
    private int clazzId;

    /**
     * 性别
     */
    private String sex = "男";

    /**
     * 联系方式
     */
    private String mobile;

}
