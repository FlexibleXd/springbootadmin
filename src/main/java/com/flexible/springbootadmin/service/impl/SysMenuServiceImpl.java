package com.flexible.springbootadmin.service.impl;

import com.flexible.springbootadmin.entity.*;
import com.flexible.springbootadmin.entity.vo.SysMenuVo;
import com.flexible.springbootadmin.repository.*;
import com.flexible.springbootadmin.service.SysMenuService;
import com.flexible.springbootadmin.service.SysService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Resource
    private SysUserRoleRepository sysUserRoleRepository;

    @Resource
    private SysMenuRepository sysMenuRepository;

    @Resource
    private SysPermissionRepository sysPermissionRepository;

    @Resource
    private SysRolePermissionRepository sysRolePermissionRepository;

    @Resource
    private SysRoleRepository sysRoleRepository;

    @Resource
    private SysUserRepository sysUserRepository;


    @Override
    public List<SysMenuVo> getMenu() {
        List<SysMenuVo> returnList = new ArrayList<>();
        List<SysMenu> firstMenu = sysMenuRepository.findByMenuParentOrderByMenuSortAsc(0);
        for (SysMenu menu : firstMenu) {
            int level = 1;
            returnList.addAll(getChildMenu(menu, level));
        }
        return returnList;
    }

    @Override
    public List<SysMenuVo> getMenuTree() {
        List<SysMenuVo> returnList = new ArrayList<>();
        List<SysMenu> firstMenu = sysMenuRepository.findByMenuParentOrderByMenuSortAsc(0);
        for (SysMenu menu : firstMenu) {
            int level = 1;
            returnList.add(getChildMenuTree(menu, level));
        }
        return returnList;
    }

    @Override
    public void addMenu(SysMenuVo menuVo) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setCreate_time(new Timestamp(new Date().getTime()));
        sysMenu.setMenu_name(menuVo.getMenuName());
        sysMenu.setMenu_desc(menuVo.getMenuDesc());
        sysMenu.setMenu_uri(menuVo.getMenuUri());
        sysMenu.setMenuParent(menuVo.getParentId());
        sysMenu.setMenuSort(menuVo.getMenuSort());
        sysMenu.setPermission_id(menuVo.getPermissionId());
        sysMenuRepository.save(sysMenu);
    }

    @Override
    public void updateMenu(SysMenuVo menuVo) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setMenu_id(menuVo.getMenuId());
        sysMenu.setCreate_time(menuVo.getCreateTime());
        sysMenu.setMenu_name(menuVo.getMenuName());
        sysMenu.setMenu_desc(menuVo.getMenuDesc());
        sysMenu.setMenu_uri(menuVo.getMenuUri());
        sysMenu.setMenuParent(menuVo.getParentId());
        sysMenu.setMenuSort(menuVo.getMenuSort());
        sysMenu.setPermission_id(menuVo.getPermissionId());
        sysMenuRepository.save(sysMenu);
    }

    @Override
    public void deleterMenu(Integer id) {
        if (id == null) {
            throw new RuntimeException("请传入删除菜单id");
        }
        sysMenuRepository.deleteById(id);
    }

    private List<SysMenuVo> getChildMenu(SysMenu parentMenu, int level) {
        List<SysMenuVo> returnList = new ArrayList<>();
        SysMenuVo sysMenuVo = new SysMenuVo();
        sysMenuVo.setCreateTime(parentMenu.getCreate_time());
        sysMenuVo.setMenuSort(parentMenu.getMenuSort());
        sysMenuVo.setLevel(level);
        sysMenuVo.setMenuId(parentMenu.getMenu_id());
        sysMenuVo.setParentId(parentMenu.getMenuParent());
        sysMenuVo.setMenuDesc(parentMenu.getMenu_desc());
        sysMenuVo.setMenuUri(parentMenu.getMenu_uri());
        sysMenuVo.setPermissionId(parentMenu.getPermission_id());
        sysMenuVo.setMenuName(parentMenu.getMenu_name());
        returnList.add(sysMenuVo);
        List<SysMenu> menuList = sysMenuRepository.findByMenuParentOrderByMenuSortAsc(parentMenu.getMenu_id());
        int nextLevel = level + 1;
        for (SysMenu sysMenu : menuList) {
            returnList.addAll(getChildMenu(sysMenu, nextLevel));
        }
        return returnList;
    }

    private SysMenuVo getChildMenuTree(SysMenu parentMenu, int level) {
        SysMenuVo sysMenuVo = new SysMenuVo();
        sysMenuVo.setCreateTime(parentMenu.getCreate_time());
        sysMenuVo.setMenuSort(parentMenu.getMenuSort());
        sysMenuVo.setLevel(level);
        sysMenuVo.setMenuId(parentMenu.getMenu_id());
        sysMenuVo.setParentId(parentMenu.getMenuParent());
        sysMenuVo.setMenuDesc(parentMenu.getMenu_desc());
        sysMenuVo.setMenuUri(parentMenu.getMenu_uri());
        sysMenuVo.setPermissionId(parentMenu.getPermission_id());
        sysMenuVo.setMenuName(parentMenu.getMenu_name());
        List<SysMenu> menuList = sysMenuRepository.findByMenuParentOrderByMenuSortAsc(parentMenu.getMenu_id());

        int nextLevel = level + 1;
        List<SysMenuVo> children = new ArrayList<>();
        for (SysMenu sysMenu : menuList) {
            children.add(getChildMenuTree(sysMenu, nextLevel));
        }
        sysMenuVo.setChildren(children);
        return sysMenuVo;
    }
}
