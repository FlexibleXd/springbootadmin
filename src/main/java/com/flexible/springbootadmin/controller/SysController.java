package com.flexible.springbootadmin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.flexible.springbootadmin.base.Result;
import com.flexible.springbootadmin.config.JwtUtil;
import com.flexible.springbootadmin.entity.SysMenu;
import com.flexible.springbootadmin.entity.SysPermission;
import com.flexible.springbootadmin.entity.SysRole;
import com.flexible.springbootadmin.entity.SysUser;
import com.flexible.springbootadmin.entity.vo.SysMenuVo;
import com.flexible.springbootadmin.repository.SysPermissionRepository;
import com.flexible.springbootadmin.repository.SysUserRepository;
import com.flexible.springbootadmin.service.SysService;
import com.flexible.springbootadmin.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/sys")
public class SysController {

    @Autowired
    SysPermissionRepository sysPermissionRepository;

    @Autowired
    SysService sysService;

    @Autowired
    SysUserRepository sysUserRepository;

    @PostMapping("/login")
    public Object login(@RequestBody() String data) {
        JSONObject userJson = JSON.parseObject(data);
        Result result = new Result();
        String token = JwtUtil.sign(sysUserRepository.getByAccount(userJson.getString("username")).getUser_id(), userJson.getString("password"));
        UsernamePasswordToken upt = new UsernamePasswordToken(userJson.getString("username"), userJson.getString("password"));
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(upt);
        } catch (Exception e) {
            System.out.println(e.toString());
            result.setCode(0);
            result.setMsg("登录失败-" + e.toString());
            return result;
        }
        result.setCode(1);
        result.setMsg("登录成功");
        result.setData(token);
        return result;
    }


    @GetMapping("/info")
    public Object info() {
        SysUser sysUser = ShiroUtils.getCurrentUser();
        Result result = new Result();
        result.setCode(1);
        result.setMsg("登录成功");
        result.setData(sysUser);
//        result.setData(JwtUtil.sign(userJson.getString("username"), userJson.getString("password")));
        return result;
    }

    @GetMapping("/user/list")
    public Object getUserList(@RequestParam("page") Integer page, @RequestParam("limit") Integer size) {
        Map<String, Object> data = new HashMap<>();
        Result result = new Result();
        result.setCode(1);
        result.setMsg("请求成功");

        Pageable pageable = PageRequest.of(--page, size);
        Page<SysUser> user = sysService.getUser(pageable);
        data.put("items", user.getContent());
        data.put("total", user.getTotalElements());
        result.setData(data);
        return result;
    }

    @GetMapping("/permission/list")
    public Object getPermissionList() {
        Map<String, Object> data = new HashMap<>();
        Result result = new Result();
        result.setCode(1);
        result.setMsg("请求成功");
        List<SysPermission> permission = sysService.getPermission();
        data.put("items", permission);
        result.setData(data);
        return result;
    }



//    @GetMapping("/menu/list")
//    public Object getMenuList() {
//        Map<String, Object> data = new HashMap<>();
//        Result result = new Result();
//        result.setCode(1);
//        result.setMsg("请求成功");
//        List<SysMenuVo> menu = sysService.getMenu();
//        data.put("items", menu);
//        result.setData(data);
//        return result;
//    }
//
//    @PostMapping("/menu")
//    public Object addMenu(@RequestBody SysMenuVo menuVo) {
//        Result result = new Result();
//        result.setCode(1);
//        result.setMsg("请求成功");
//        sysService.addMenu(menuVo);
//        return result;
//    }
//
//    @PutMapping("/menu")
//    public Object updateMenu(@RequestBody SysMenuVo menuVo) {
//        Result result = new Result();
//        result.setCode(1);
//        result.setMsg("请求成功");
//        System.out.println(JSON.toJSONString(menuVo, true));
//        sysService.updateMenu(menuVo);
//        return result;
//    }
//
//    @DeleteMapping("/menu/{menuId}")
//    public Object deleterMenu(@PathVariable ("menuId") Integer menuId) {
//        Result result = new Result();
//        result.setCode(1);
//        result.setMsg("请求成功");
//        System.out.println(JSON.toJSONString(menuId, true));
//        sysService.deleterMenu(menuId);
//        return result;
//    }

//    @GetMapping("/permission")
//    public Object getPermissionByRole(@PathVariable("roleId") Integer id) {
//        return sysService.getRoleByUser(1);
//    }


}
