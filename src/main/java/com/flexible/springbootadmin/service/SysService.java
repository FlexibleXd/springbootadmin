package com.flexible.springbootadmin.service;

import com.flexible.springbootadmin.entity.SysPermission;
import com.flexible.springbootadmin.entity.SysUser;
import com.flexible.springbootadmin.entity.vo.SysMenuVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SysService {

    List<SysPermission> getPermissionByRole(String roleId);

    boolean updatePermission(Map<String, Object> permission);

    List<SysPermission> getPermission();

}
