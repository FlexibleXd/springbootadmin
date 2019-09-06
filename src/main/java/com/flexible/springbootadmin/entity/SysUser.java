package com.flexible.springbootadmin.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "sys_user")
@Entity
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "sys_user";
    public static final String USER_ID = "user_id";
    public static final String ACCOUNT = "account";
    public static final String USER_NAME = "user_name";
    public static final String PASSWORD = "password";
    public static final String SALT = "salt";
    public static final String LOCKED = "locked";
    public static final String UPDATE_TIME = "update_time";
    public static final String CREATE_TIME = "create_time";
    @Id()
    private Integer user_id;
    @Column
    private String account;
    @Column(name = "user_name")
    private String userName;
    @Column
    private String password;
    @Column
    private String salt;
    @Column
    private Integer locked;
    @Column
    private Timestamp update_time;
    @Column
    private Timestamp create_time;


//    @ManyToMany(fetch = FetchType.EAGER)//立即从数据库中进行加载数据;
//    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
//    private List<SysRole> roleList;// 一个用户具有多个角色

    public SysUser() {
    }

    public Integer getUser_id() {
        return this.user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getLocked() {
        return this.locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Timestamp getUpdate_time() {
        return this.update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public Timestamp getCreate_time() {
        return this.create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

//    public List<SysRole> getRoleList() {
//        return roleList;
//    }
//
//    public void setRoleList(List<SysRole> roleList) {
//        this.roleList = roleList;
//    }
}
