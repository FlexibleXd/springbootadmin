
package com.flexible.springbootadmin.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class SysUserVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String account;
    private String userName;
    private List<SysRoleVo> roleList;
    private Timestamp createTime;
    private Integer locked;
}
