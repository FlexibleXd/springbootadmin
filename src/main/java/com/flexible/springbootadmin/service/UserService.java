package com.flexible.springbootadmin.service;

import com.flexible.springbootadmin.entity.SysUser;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    SysUser searchByName(String name);

    SysUser searchById(Integer id);
}
