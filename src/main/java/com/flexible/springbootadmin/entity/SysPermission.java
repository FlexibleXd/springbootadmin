
package com.flexible.springbootadmin.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "sys_permission")
@Getter
@Setter
public class SysPermission {
    @Id
    @Column(name = "permission_id", length = 64)
    protected String permissionId;
    @Column
    protected String permission_name;
    @Column
    private Date create_time;

    public SysPermission() {
    }

}
