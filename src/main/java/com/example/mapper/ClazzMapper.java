package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Clazz;

import java.util.List;
import java.util.Map;

public interface ClazzMapper extends BaseMapper<Clazz> {

    List<Clazz> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int addClazz(Clazz aClazz);

    int deleteClazz(List<Integer> ids);

    int editClazz(Clazz clazz);

    Clazz findByName(String clazzName);
}
