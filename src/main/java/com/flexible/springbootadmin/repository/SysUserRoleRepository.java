package com.flexible.springbootadmin.repository;

import com.flexible.springbootadmin.entity.SysUserRole;
import com.flexible.springbootadmin.entity.SysUserRolePk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysUserRoleRepository extends JpaRepository<SysUserRole, SysUserRolePk> {
    List<SysUserRole> getByUserId(Integer id);
}
