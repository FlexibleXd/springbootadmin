package com.flexible.springbootadmin.service.impl;

import com.flexible.springbootadmin.base.AlertException;
import com.flexible.springbootadmin.entity.*;
import com.flexible.springbootadmin.entity.vo.SysMenuVo;
import com.flexible.springbootadmin.entity.vo.SysRoleVo;
import com.flexible.springbootadmin.repository.*;
import com.flexible.springbootadmin.service.SysMenuService;
import com.flexible.springbootadmin.service.SysRoleService;
import com.flexible.springbootadmin.util.AppUtils;
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

    @Resource
    private SysMenuService sysMenuService;

    @Override
    public List<SysRoleVo> getRole() {
        List<SysRole> allRoleList = sysRoleRepository.findAll();
        List<SysRoleVo> roleVoList = new ArrayList<>();
        for (SysRole sysRole : allRoleList) {
            SysRoleVo sysRoleVo = new SysRoleVo();
            sysRoleVo.setRoleId(sysRole.getRoleId());
            sysRoleVo.setRoleName(sysRole.getRoleName());
            sysRoleVo.setRoutes(sysMenuService.getRoleMenuTree(sysRole.getRoleId()));
            roleVoList.add(sysRoleVo);
        }
        return roleVoList;
    }

    @Override
    public void addRole(SysRoleVo sysRoleVo) throws AlertException {
        if (sysRoleRepository.countByRoleId(sysRoleVo.getRoleId()) != 0) {
            throw new AlertException("角色已经存在");
        }
        List<SysMenuVo> routes = sysRoleVo.getRoutes();
        List<SysRolePermission> rolePermissionList = new ArrayList<>();
        for (SysMenuVo route : routes) {
            rolePermissionList.addAll(getRolePermissionByMenuVo(route, sysRoleVo.getRoleId()));
        }
        addRolePermissionRelation(sysRoleVo.getRoleId(), rolePermissionList);
        SysRole role = new SysRole();
        role.setRoleId(sysRoleVo.getRoleId());
        role.setRoleName(sysRoleVo.getRoleName());
        role.setCreate_time(new Timestamp(new Date().getTime()));
        sysRoleRepository.save(role);
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
    public Boolean deleteRole(String roleId) throws AlertException {
        if (sysRoleRepository.countByRoleId(roleId) == 0) {
            throw new AlertException("角色不存在！");
        }
        List<SysRolePermission> rolePermissionList = sysRolePermissionRepository.getByRoleId(roleId);
        sysRolePermissionRepository.deleteAll(rolePermissionList);
        sysRoleRepository.deleteById(roleId);
        return true;
    }

    @Override
    public Boolean updateRole(SysRoleVo sysRoleVo) throws AlertException {
        if (sysRoleRepository.countByRoleId(sysRoleVo.getRoleId()) == 0) {
            throw new AlertException("角色不存在！");
        }
        List<SysMenuVo> routes = sysRoleVo.getRoutes();
        List<SysRolePermission> rolePermissionList = new ArrayList<>();
        for (SysMenuVo route : routes) {
            rolePermissionList.addAll(getRolePermissionByMenuVo(route, sysRoleVo.getRoleId()));
        }
        addRolePermissionRelation(sysRoleVo.getRoleId(), rolePermissionList);
        SysRole role = new SysRole();
        role.setRoleId(sysRoleVo.getRoleId());
        role.setRoleName(sysRoleVo.getRoleName());
        role.setCreate_time(new Timestamp(new Date().getTime()));
        sysRoleRepository.save(role);
        return true;
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


    public List<SysRolePermission> getRolePermissionByMenuVo(SysMenuVo route, String roleId) {
        List<SysRolePermission> rolePermissionList = new ArrayList<>();
        SysRolePermission sysRolePermission = new SysRolePermission();
        sysRolePermission.setRoleId(roleId);
        sysRolePermission.setPermissionId(route.getPermissionId());
        sysRolePermission.setCreate_time(AppUtils.getNowTime());
        rolePermissionList.add(sysRolePermission);
        List<SysMenuVo> children = route.getChildren();
        if (children != null && children.size() != 0) {
            for (SysMenuVo child : children) {
                SysRolePermission childRolePermission = new SysRolePermission();
                childRolePermission.setRoleId(roleId);
                childRolePermission.setPermissionId(child.getPermissionId());
                childRolePermission.setCreate_time(AppUtils.getNowTime());
                rolePermissionList.add(childRolePermission);
            }
        }
        return rolePermissionList;
    }
}
