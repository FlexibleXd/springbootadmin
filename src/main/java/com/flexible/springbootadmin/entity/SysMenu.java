
package com.flexible.springbootadmin.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Table(name = "sys_menu")
@Entity
//@Document(indexName = "menu", type = "docs")
@Getter
@Setter
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menu_id;
    @Column
    private String menu_name;
    @Column
    private String menu_uri;
    @Column
    private String menu_desc;
    @Column(name = "menu_parent")
    private Integer menuParent;
    @Column
    private String menu_icon;
    @Column(name = "menu_sort")
    private int menuSort;
    @Column(name = "permission_id")
    private String permissionId;
    @Column
    private Timestamp create_time;

    public SysMenu() {
    }

}
