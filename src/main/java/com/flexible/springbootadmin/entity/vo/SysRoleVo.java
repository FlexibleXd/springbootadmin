
package com.flexible.springbootadmin.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class SysRoleVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String roleId;
    private String roleName;
    private List<SysMenuVo> routes;
}
