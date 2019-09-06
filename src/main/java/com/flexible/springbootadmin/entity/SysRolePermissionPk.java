package com.flexible.springbootadmin.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SysRolePermissionPk implements Serializable {
    @Column(name = "permission_id")
    private String permissionId;
    @Column(name = "role_id",length = 64)
    private String roleId;
}
