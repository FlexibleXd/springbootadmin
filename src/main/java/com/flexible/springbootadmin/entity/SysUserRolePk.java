package com.flexible.springbootadmin.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SysUserRolePk implements Serializable {
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "role_id",length = 64)
    private String roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
