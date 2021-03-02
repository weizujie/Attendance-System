package com.example.service;

import com.example.entity.Admin;

public interface AdminService {

    Admin login(Admin admin);

    int updatePasswordByAdmin(Admin admin);

}
