package com.flexible.springbootadmin.service;

import com.flexible.springbootadmin.entity.SysPermission;
import com.flexible.springbootadmin.entity.SysUser;
import com.flexible.springbootadmin.entity.vo.SysMenuVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface SysService {

    List<SysPermission> getPermissionByRole(String roleId);

    boolean updatePermission(Map<String, Object> permission);

    List<SysPermission> getPermission();

    Page<SysUser> getUser(Pageable pageable);

    List<SysMenuVo> getMenu();

    void addMenu(SysMenuVo menuVo);

    void updateMenu(SysMenuVo menuVo);

    void deleterMenu(Integer id);
}
