package com.flexible.springbootadmin.repository;

import com.flexible.springbootadmin.entity.SysUser;
import com.flexible.springbootadmin.entity.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser, Integer> {
    SysUser getByAccount(String account);
}
