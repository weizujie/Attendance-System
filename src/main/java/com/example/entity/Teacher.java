package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 教师 实体类
 *
 * @author weizujie
 * @date 2021/03/02
 */
@Data
@TableName("st_teacher")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教师 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
    private Integer clazzId;

    /**
     * 性别
     */
    private String sex = "男";

    /**
     * 联系方式
     */
    private String mobile;

}
