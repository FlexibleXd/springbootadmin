
package com.flexible.springbootadmin.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SysMenuVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer menuId;
    private Integer parentId;
    private String menuName;
    private String menuUri;
    private String menuDesc;
    private int menuSort;
    private String permissionId;
    private int level;
    private Timestamp createTime;
}
