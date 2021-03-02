package com.example.entity;

import lombok.Data;

/**
 * 班级 实体类
 *
 * @author weizujie
 * @date 2021/03/02
 */
@Data
public class Clazz {

    /**
     * 班级 id
     */
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
