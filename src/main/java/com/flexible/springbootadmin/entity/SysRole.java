
package com.flexible.springbootadmin.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "sys_role")
@Entity
public class SysRole implements Serializable {

    @Id
    @Column(length = 64)
    protected String role_id;
    @Column
    protected String role_name;
    @Column
    private Timestamp create_time;

    //角色 -- 权限关系：多对多关系;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "sys_role_permission", joinColumns = {@JoinColumn(name = "permission_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
//    private List<SysPermission> permissions;

    // 用户 - 角色关系定义;
//    @ManyToMany
//    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
//    private List<SysUser> sysUsers;// 一个角色对应多个用户

    public SysRole() {
    }

    public String getRole_id() {
        return this.role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return this.role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public Timestamp getCreate_time() {
        return this.create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

//    public List<SysPermission> getPermissions() {
//        return permissions;
//    }
//
//    public void setPermissions(List<SysPermission> permissions) {
//        this.permissions = permissions;
//    }
//
//    public List<SysUser> getSysUsers() {
//        return sysUsers;
//    }
//
//    public void setSysUsers(List<SysUser> sysUsers) {
//        this.sysUsers = sysUsers;
//    }
}
