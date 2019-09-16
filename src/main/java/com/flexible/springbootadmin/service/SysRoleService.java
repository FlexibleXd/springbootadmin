package com.flexible.springbootadmin.service;

import com.flexible.springbootadmin.base.AlertException;
import com.flexible.springbootadmin.entity.SysRole;
import com.flexible.springbootadmin.entity.vo.SysRoleVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface SysRoleService {
    List<SysRoleVo> getRole();

    @Transactional
    void addRole(SysRoleVo sysRoleVo) throws AlertException;

    /**
     * 删除
     *
     * @param roleId
     * @return
     */
    Boolean deleteRole(String roleId) throws AlertException;

    /**
     * 更新role
     *
     * @param roleVo
     * @return
     */
    @Transactional
    Boolean updateRole(SysRoleVo roleVo) throws AlertException;
    /**
     * 获取用户权限
     *
     * @param userId
     * @return
     */
    List<SysRole> getRoleByUser(Integer userId);
}
