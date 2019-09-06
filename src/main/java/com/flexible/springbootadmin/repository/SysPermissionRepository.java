package com.flexible.springbootadmin.repository;

import com.flexible.springbootadmin.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysPermissionRepository extends JpaRepository<SysPermission, String> {

}
