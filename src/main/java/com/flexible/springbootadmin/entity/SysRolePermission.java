//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flexible.springbootadmin.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "sys_role_permission")
@Entity
@IdClass(SysRolePermissionPk.class)
public class SysRolePermission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "permission_id")
    private String permissionId;
    @Id
    @Column(name = "role_id", length = 64)
    private String roleId;

    @Column
    private Timestamp create_time;

    public SysRolePermission() {
    }


    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Timestamp getCreate_time() {
        return this.create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}
