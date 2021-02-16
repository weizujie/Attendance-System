package com.example.mapper;

import com.example.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {

    Admin findByAdmin(Admin admin);

    int editPwdByAdmin(Admin admin);
}
