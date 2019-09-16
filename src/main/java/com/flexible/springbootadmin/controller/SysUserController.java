package com.flexible.springbootadmin.controller;

import com.flexible.springbootadmin.base.Result;
import com.flexible.springbootadmin.repository.SysPermissionRepository;
import com.flexible.springbootadmin.repository.SysUserRepository;
import com.flexible.springbootadmin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @GetMapping("/user/list")
    public Object getUserList(@RequestParam("page") Integer page, @RequestParam("limit") Integer size) {
        Result result = Result.success();
        Pageable pageable = PageRequest.of(--page, size);
        Map<String, Object> user = sysUserService.getUser(pageable);
        result.setData(user);
        return result;
    }
}
