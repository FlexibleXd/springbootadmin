package com.flexible.springbootadmin.service.impl;

import com.flexible.springbootadmin.entity.SysRole;
import com.flexible.springbootadmin.entity.SysUser;
import com.flexible.springbootadmin.entity.vo.SysRoleVo;
import com.flexible.springbootadmin.entity.vo.SysUserVo;
import com.flexible.springbootadmin.repository.SysUserRepository;
import com.flexible.springbootadmin.service.SysRoleService;
import com.flexible.springbootadmin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    SysUserRepository sysUserRepository;
    @Resource
    SysRoleService sysRoleService;

    @Override
    public SysUser searchByName(String name) {
        return sysUserRepository.getByAccount(name);
    }

    @Override
    public SysUser searchById(Integer id) {
        return sysUserRepository.findById(id).get();
    }


    @Override
    public Map<String, Object> getUser(Pageable pageable) {
        Map<String, Object> data = new HashMap<>();
        Page<SysUser> userList = sysUserRepository.findAll(pageable);
        List<SysUserVo> sysUserVoList = new ArrayList<>();
        for (SysUser sysUser : userList.getContent()) {
            SysUserVo userVo = new SysUserVo();
            userVo.setAccount(sysUser.getAccount());
            userVo.setCreateTime(sysUser.getCreate_time());
            userVo.setLocked(sysUser.getLocked());
            userVo.setId(sysUser.getUser_id());
            userVo.setUserName(sysUser.getUserName());
            List<SysRole> userRole = sysRoleService.getRoleByUser(sysUser.getUser_id());
            userVo.setRoleList(getRoleVo(userRole));
            sysUserVoList.add(userVo);
        }
        data.put("items", sysUserVoList);
        data.put("total", userList.getTotalElements());
        return data;
    }

    /**
     * 根据sysRole，获取vo
     *
     * @param roleList
     * @return
     */
    private List<SysRoleVo> getRoleVo(List<SysRole> roleList) {
        List<SysRoleVo> sysRoleVoList = new ArrayList<>();
        for (SysRole sysRole : roleList) {
            SysRoleVo sysRoleVo = new SysRoleVo();
            sysRoleVo.setRoleName(sysRole.getRoleName());
            sysRoleVo.setRoleId(sysRole.getRoleId());
            sysRoleVoList.add(sysRoleVo);
        }
        return sysRoleVoList;
    }
}
