package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    private Long id;

    private String username;

    private String password;


}
