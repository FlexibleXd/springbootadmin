package com.flexible.springbootadmin.config;

import com.flexible.springbootadmin.entity.SysPermission;
import com.flexible.springbootadmin.entity.SysRole;
import com.flexible.springbootadmin.entity.SysUser;
import com.flexible.springbootadmin.service.SysService;
import com.flexible.springbootadmin.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.annotation.Resource;

public class UserRealm extends AuthorizingRealm {
    @Resource
    UserService userService;
    @Resource
    SysService sysService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->UserRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
        for (SysRole role : sysService.getRoleByUser(sysUser.getUser_id())) {
            authorizationInfo.addRole(role.getRole_name());
            for (SysPermission p : sysService.getPermissionByRole(role.getRole_id())) {
                authorizationInfo.addStringPermission(p.getPermission_name());
            }
        }
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("UserRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String auth = (String) token.getPrincipal();
        System.out.println(token.getCredentials());
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SysUser userInfo = userService.searchByName(auth);
        System.out.println("----->>userInfo=" + userInfo);
        if (userInfo == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getSalt()),//salt=username+salt
                getName()  //realm name
        );
//        SimpleAccount account = new SimpleAccount(userInfo, userInfo.getPassword(), this.getName());
//        if (Strings.isNotBlank(userInfo.getSalt())) {
//            account.setCredentialsSalt(ByteSource.Util.bytes(userInfo.getSalt()));
//        }
        return authenticationInfo;
    }

}