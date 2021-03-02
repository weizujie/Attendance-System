package com.example.mapper;

import com.example.entity.Admin;
import org.springframework.stereotype.Repository;

public interface AdminMapper {

    Admin getByAdmin(Admin admin);

    int updatePasswordByAdmin(Admin admin);
}
