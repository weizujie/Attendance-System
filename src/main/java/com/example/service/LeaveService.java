package com.example.service;

import com.example.entity.Leave;
import com.example.util.PageBean;

import java.util.Map;


public interface LeaveService {
    PageBean<Leave> queryPage(Map<String, Object> paramMap);

    int addLeave(Leave leave);

    int editLeave(Leave leave);

    int checkLeave(Leave leave);

    int deleteLeave(Integer id);
}
