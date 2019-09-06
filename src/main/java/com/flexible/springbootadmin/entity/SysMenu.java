
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
    public static final String TABLE_NAME = "sys_menu";
    public static final String MENU_ID = "menu_id";
    public static final String MENU_NAME = "menu_name";
    public static final String MENU_URI = "menu_uri";
    public static final String MENU_DESC = "menu_desc";
    public static final String MENU_PARENT = "menu_parent";
    public static final String MENU_ICON = "menu_icon";
    public static final String MENU_SORT = "menu_sort";
    public static final String PERMISSION_ID = "permission_id";
    public static final String CREATE_TIME = "create_time";
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
    @Column
    private String permission_id;
    @Column
    private Timestamp create_time;

    public SysMenu() {
    }

}
