package com.flexible.springbootadmin.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "sys_user_role")
@Entity
@IdClass(SysUserRolePk.class)
public class SysUserRole {
//    @EmbeddedId
//    private SysUserRolePk id;
    @Id
    @Column(name = "user_id")
    private Integer userId;
    @Id
    @Column(name = "role_id", length = 64)
    private String roleId;
    @Column
    private Timestamp create_time;

    public SysUserRole() {
    }

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

//    public SysUserRolePk getId() {
//        return id;
//    }
//
//    public void setId(SysUserRolePk id) {
//        this.id = id;
//    }

    public Timestamp getCreate_time() {
        return this.create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}
