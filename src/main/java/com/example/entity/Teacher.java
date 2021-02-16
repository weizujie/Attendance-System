package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 教师 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    private int id;

    private String sn; // 学号

    private String username;

    private String password;

    private int clazzId;

    private String sex = "男";

    private String mobile;

    private String avatar; // 头像

}
