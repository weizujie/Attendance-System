package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 班级 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clazz {

    private Integer id;

    private String name;
	
    private String info;

}
