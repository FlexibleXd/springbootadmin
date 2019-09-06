
package com.flexible.springbootadmin.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "sys_permission")
public class SysPermission {
    @Id
    @Column(length = 64)
    protected String permission_id;
    @Column
    protected String permission_name;
    @Column
    private Date create_time;

    public SysPermission() {
    }

    public String getPermission_id() {
        return this.permission_id;
    }

    public void setPermission_id(String permission_id) {
        this.permission_id = permission_id;
    }

    public String getPermission_name() {
        return this.permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }

    public Date getCreate_time() {
        return this.create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
