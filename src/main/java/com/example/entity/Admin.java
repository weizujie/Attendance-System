package com.example.entity;

import lombok.Data;

/**
 * 管理员 实体类
 *
 * @author weizujie
 * @date 2021/03/02
 */
@Data
public class Admin {

    /**
     * 管理员 id
     */
    private Long id;

    /**
     * 管理员登录用户
     */
    private String username;

    /**
     * 管理员登录密码
     */
    private String password;


}
