package com.flexible.springbootadmin.service;

import com.flexible.springbootadmin.entity.SysRole;

import java.util.List;

public interface SysRoleService {
    List<SysRole> getRole();

    List<SysRole> addRole();

    List<SysRole> deleteRole();

    List<SysRole> updateRole();

    List<SysRole> getRoleByUser(Integer userId);
}
