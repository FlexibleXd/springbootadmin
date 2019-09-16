package com.flexible.springbootadmin.service;

import com.flexible.springbootadmin.entity.SysUser;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface SysUserService {
    SysUser searchByName(String name);

    SysUser searchById(Integer id);

    Map<String, Object> getUser(Pageable pageable);
}
