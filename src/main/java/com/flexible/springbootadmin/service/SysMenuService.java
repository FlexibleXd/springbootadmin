package com.flexible.springbootadmin.service;

import com.flexible.springbootadmin.entity.vo.SysMenuVo;

import java.util.List;

public interface SysMenuService {
    List<SysMenuVo> getMenu();

    List<SysMenuVo> getMenuTree();

    void addMenu(SysMenuVo menuVo);

    void updateMenu(SysMenuVo menuVo);

    void deleterMenu(Integer id);
}
