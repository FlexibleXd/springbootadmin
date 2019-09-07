
package com.flexible.springbootadmin.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "sys_role")
@Entity
@Getter
@Setter
public class SysRole implements Serializable {

    @Id
    @Column(name = "role_id", length = 64)
    protected String roleId;
    @Column(name = "role_name")
    protected String roleName;
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
