package com.flexible.springbootadmin.base;

import com.alibaba.fastjson.JSON;
import com.flexible.springbootadmin.service.SysService;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
@Lazy(true)
public class PermissionBeanPostProcessor implements BeanPostProcessor {
    @Resource
    SysService sysService;

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        for (Method method : methods) {
//            System.out.println("-------------" + method.getName());
            RequiresPermissions annotation = method.getAnnotation(RequiresPermissions.class);

            Map<String, Object> permissionMap = new HashMap<>();
            if (null != annotation) {
                String[] value = annotation.value();
                for (String permission : value) {
//                    System.out.println("-------------" + s);
                    if (null != permission && !permission.endsWith("*")) {
                        Desc desc = method.getAnnotation(Desc.class);
                        String descText;
                        if (null != desc) {
                            descText = desc.value();
                        } else {
                            descText = "系统默认";
                        }
                        permissionMap.put(permission, descText);
                    }
                    System.out.println(JSON.toJSONString(permissionMap));
                    sysService.updatePermission(permissionMap);

//                for(var11 = 0; var11 < var10; ++var11) {
//                    String permission = var9[var11];
//                    if (null != permission && !permission.endsWith("*")) {
//                        Desc desc = (Desc)method.getAnnotation(Desc.class);
//                        String val = (String)ALL_PERMISSIONS.get(permission);
//                        if (null != desc) {
//                            val = desc.value();
//                        }
//
//                        if (null == val) {
//                            val = "系统添加";
//                        }
//
//                        ALL_PERMISSIONS.put(permission, val);
                }
            }
        }
        return bean;
    }
}
