package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 班级 实体类
 *
 * @author weizujie
 * @date 2021/03/02
 */
@Data
@TableName("s_clazz")
public class Clazz {

    /**
     * 班级 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 班级名称
     */
    private String name;

    /**
     * 班级信息
     */
    private String info;

}
