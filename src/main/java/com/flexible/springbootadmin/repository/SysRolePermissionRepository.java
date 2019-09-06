package com.flexible.springbootadmin.repository;

import com.flexible.springbootadmin.entity.SysPermission;
import com.flexible.springbootadmin.entity.SysRolePermission;
import com.flexible.springbootadmin.entity.SysRolePermissionPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysRolePermissionRepository extends JpaRepository<SysRolePermission, SysRolePermissionPk> {
    List<SysRolePermission> getByRoleId(String roleId);
}
