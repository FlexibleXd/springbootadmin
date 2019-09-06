package com.flexible.springbootadmin.controller;

import com.flexible.springbootadmin.base.Result;
import com.flexible.springbootadmin.entity.SysRole;
import com.flexible.springbootadmin.repository.SysPermissionRepository;
import com.flexible.springbootadmin.service.SysRoleService;
import com.flexible.springbootadmin.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
public class SysRoleController {

    @Autowired
    SysPermissionRepository sysPermissionRepository;

    @Autowired
    SysService sysService;

    @Autowired
    SysRoleService sysRoleService;

    @GetMapping("/role/list")
    public Object getRoleList() {
        Map<String, Object> data = new HashMap<>();
        Result result = Result.success();
        List<SysRole> role = sysRoleService.getRole();
        data.put("items", role);
        result.setData(data);
        return result;
    }
}
