package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Leave;

import java.util.List;
import java.util.Map;

public interface LeaveMapper extends BaseMapper<Leave> {

    List<Leave> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int addLeave(Leave aLeave);

    int editLeave(Leave aLeave);

    int checkLeave(Leave aLeave);

    int deleteLeave(Integer id);
}
