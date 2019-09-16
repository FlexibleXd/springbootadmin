package com.flexible.springbootadmin.service.impl;

import com.flexible.springbootadmin.entity.SysPermission;
import com.flexible.springbootadmin.entity.SysRolePermission;
import com.flexible.springbootadmin.entity.SysUser;
import com.flexible.springbootadmin.repository.*;
import com.flexible.springbootadmin.service.SysPermissionService;
import com.flexible.springbootadmin.service.SysService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Resource
    private SysPermissionRepository sysPermissionRepository;

    @Resource
    private SysRolePermissionRepository sysRolePermissionRepository;


    @Override
    public List<SysPermission> getPermissionByRole(String roleId) {
        List<SysRolePermission> rolePermissionList = sysRolePermissionRepository.getByRoleId(roleId);
        List<SysPermission> permissionList = new ArrayList<>();
        for (SysRolePermission sysRolePermission : rolePermissionList) {
            SysPermission permission = sysPermissionRepository.findById(sysRolePermission.getPermissionId()).get();
            permissionList.add(permission);
        }
        return permissionList;
    }

    @Override
    public boolean updatePermission(Map<String, Object> permission) {
        List<SysPermission> permissionList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : permission.entrySet()) {
            SysPermission sysPermission = new SysPermission();
            sysPermission.setPermissionId(entry.getKey());
            sysPermission.setPermission_name((String) entry.getValue());
            sysPermission.setCreate_time(new Date());
            permissionList.add(sysPermission);
        }
        sysPermissionRepository.saveAll(permissionList);
        return true;
    }

    @Override
    public List<SysPermission> getPermission() {
        return sysPermissionRepository.findAll();
    }


}
