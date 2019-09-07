package com.flexible.springbootadmin.controller;

import com.flexible.springbootadmin.base.Result;
import com.flexible.springbootadmin.entity.vo.SysMenuVo;
import com.flexible.springbootadmin.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
public class SysMenuController {

    @Autowired
    SysMenuService sysMenuService;


    @GetMapping("/menu/list")
    public Object getMenuList() {
        Map<String, Object> data = new HashMap<>();
        Result result = Result.success();
        List<SysMenuVo> menu = sysMenuService.getMenu();
        data.put("items", menu);
        result.setData(data);
        return result;
    }

    @GetMapping("/menu/tree")
    public Object getMenuTreeList() {
        Result result = Result.success();
        List<SysMenuVo> menu = sysMenuService.getMenuTree();
        result.setData(menu);
        return result;
    }

    @PostMapping("/menu")
    public Object addMenu(@RequestBody SysMenuVo menuVo) {
        Result result = Result.success();
        sysMenuService.addMenu(menuVo);
        return result;
    }

    @PutMapping("/menu")
    public Object updateMenu(@RequestBody SysMenuVo menuVo) {
        Result result = Result.success();
        sysMenuService.updateMenu(menuVo);
        return result;
    }

    @DeleteMapping("/menu/{menuId}")
    public Object deleterMenu(@PathVariable("menuId") Integer menuId) {
        Result result = Result.success();
        sysMenuService.deleterMenu(menuId);
        return result;
    }


}
