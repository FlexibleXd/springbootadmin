package com.flexible.springbootadmin.util;

import com.flexible.springbootadmin.entity.SysUser;
import org.apache.shiro.SecurityUtils;

public class ShiroUtils {

    public static SysUser getCurrentUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }
}
