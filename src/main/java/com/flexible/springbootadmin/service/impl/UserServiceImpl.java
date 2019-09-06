package com.flexible.springbootadmin.service.impl;

import com.flexible.springbootadmin.entity.SysUser;
import com.flexible.springbootadmin.repository.SysUserRepository;
import com.flexible.springbootadmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public SysUser searchByName(String name) {
        return sysUserRepository.getByAccount(name);
    }

    @Override
    public SysUser searchById(Integer id) {
        return sysUserRepository.findById(id).get();
    }
}
