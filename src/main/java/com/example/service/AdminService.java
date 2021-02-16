package com.example.service;

import com.example.entity.Admin;

public interface AdminService {

    Admin findByAdmin(Admin admin);

    int editPwdByAdmin(Admin admin);
}
