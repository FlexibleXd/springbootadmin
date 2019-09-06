package com.flexible.springbootadmin.service.impl;

import com.flexible.springbootadmin.entity.*;
import com.flexible.springbootadmin.repository.*;
import com.flexible.springbootadmin.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysUserRoleRepository sysUserRoleRepository;


    @Resource
    private SysRoleRepository sysRoleRepository;


    @Override
    public List<SysRole> getRole() {
        return sysRoleRepository.findAll();
    }

    @Override
    public List<SysRole> addRole() {
        return null;
    }

    @Override
    public List<SysRole> deleteRole() {
        return null;
    }

    @Override
    public List<SysRole> updateRole() {
        return null;
    }

    @Override
    public List<SysRole> getRoleByUser(Integer userId) {
        List<SysUserRole> userRoleList = sysUserRoleRepository.getByUserId(userId);
        List<SysRole> roleList = new ArrayList<>();
        for (SysUserRole sysUserRole : userRoleList) {
            SysRole role = sysRoleRepository.findById(sysUserRole.getRoleId()).get();
            roleList.add(role);
        }
        return roleList;
    }


}
