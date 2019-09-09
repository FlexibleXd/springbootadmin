package com.flexible.springbootadmin.service;

import com.flexible.springbootadmin.base.AlertException;
import com.flexible.springbootadmin.entity.SysRole;
import com.flexible.springbootadmin.entity.vo.SysRoleVo;

import java.util.List;

public interface SysRoleService {
    List<SysRole> getRole();

    void addRole(SysRoleVo sysRoleVo) throws AlertException;

    List<SysRole> deleteRole();

    List<SysRole> updateRole();

    List<SysRole> getRoleByUser(Integer userId);
}
