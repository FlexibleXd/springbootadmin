package com.flexible.springbootadmin.service;

import com.flexible.springbootadmin.entity.vo.SysMenuVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface SysMenuService {
    /**
     * 获取菜单列表
     *
     * @return
     */
    List<SysMenuVo> getMenu();

    /**
     * 获取菜单树
     *
     * @return
     */
    List<SysMenuVo> getMenuTree();

    /**
     * 根据角色，获取菜单树
     *
     * @param roleId
     * @return
     */
    List<SysMenuVo> getRoleMenuTree(String roleId);

    void addMenu(SysMenuVo menuVo);

    @Transactional
    void updateMenu(SysMenuVo menuVo);

    void deleterMenu(Integer id);
}
