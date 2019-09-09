package com.flexible.springbootadmin.service.impl;

import com.flexible.springbootadmin.base.AlertException;
import com.flexible.springbootadmin.entity.*;
import com.flexible.springbootadmin.entity.vo.SysMenuVo;
import com.flexible.springbootadmin.entity.vo.SysRoleVo;
import com.flexible.springbootadmin.repository.*;
import com.flexible.springbootadmin.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysUserRoleRepository sysUserRoleRepository;

    @Resource
    private SysRoleRepository sysRoleRepository;

    @Resource
    private SysRolePermissionRepository sysRolePermissionRepository;

    @Override
    public List<SysRole> getRole() {
        return sysRoleRepository.findAll();
    }

    @Override
    public void addRole(SysRoleVo sysRoleVo) throws AlertException {
        if (sysRoleRepository.countByRoleId(sysRoleVo.getRoleId()) != 0) {
            throw new AlertException("角色已经存在");
        }
        List<SysMenuVo> routes = sysRoleVo.getRoutes();
        List<SysRolePermission> rolePermissionList = new ArrayList<>();
        for (SysMenuVo route : routes) {
            SysRolePermission sysRolePermission = new SysRolePermission();
            sysRolePermission.setRoleId(sysRoleVo.getRoleId());
            sysRolePermission.setPermissionId(route.getPermissionId());
            sysRolePermission.setCreate_time(new Timestamp(new Date().getTime()));
            rolePermissionList.add(sysRolePermission);
        }
        addRolePermissionRelation(sysRoleVo.getRoleId(), rolePermissionList);
    }

    /**
     * 添加角色关联表权限的关系
     *
     * @param roleId             角色id
     * @param rolePermissionList 拼装好的中间表list
     */
    private void addRolePermissionRelation(String roleId, List<SysRolePermission> rolePermissionList) {
        List<SysRolePermission> oldPermissionList = sysRolePermissionRepository.getByRoleId(roleId);
        sysRolePermissionRepository.deleteAll(oldPermissionList);
        sysRolePermissionRepository.saveAll(rolePermissionList);
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
