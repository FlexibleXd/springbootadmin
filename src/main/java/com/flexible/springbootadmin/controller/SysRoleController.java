package com.flexible.springbootadmin.controller;

import com.alibaba.fastjson.JSON;
import com.flexible.springbootadmin.base.AlertException;
import com.flexible.springbootadmin.base.Result;
import com.flexible.springbootadmin.entity.SysRole;
import com.flexible.springbootadmin.entity.vo.SysRoleVo;
import com.flexible.springbootadmin.repository.SysPermissionRepository;
import com.flexible.springbootadmin.service.SysRoleService;
import com.flexible.springbootadmin.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        Result result = Result.success();
        List<SysRoleVo> role = sysRoleService.getRole();
        result.setData(role);
        return result;
    }

    @PostMapping("/role")
    public Object addRoleList(@RequestBody SysRoleVo sysRoleVo) throws AlertException {
        System.out.println(JSON.toJSONString(sysRoleVo));
        Result result = Result.success();
        sysRoleService.addRole(sysRoleVo);
        return result;
    }
}
