package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 管理员 实体类
 *
 * @author weizujie
 * @date 2021/03/02
 */
@Data
@TableName("s_admin")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员 id
     */
    @TableId(value = "id", type = IdType.AUTO)
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
