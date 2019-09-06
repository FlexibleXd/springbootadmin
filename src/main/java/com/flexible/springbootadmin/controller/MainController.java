package com.flexible.springbootadmin.controller;

import com.flexible.springbootadmin.base.Desc;
import com.flexible.springbootadmin.repository.SysMenuRepository;
import com.flexible.springbootadmin.repository.SysPermissionRepository;
import com.flexible.springbootadmin.service.SysService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController()
//@RequestMapping("/test")
public class MainController {
    @Autowired
    SysPermissionRepository sysPermissionRepository;

    @Autowired
    SysService sysService;


    @Autowired
    SysMenuRepository sysMenuRepository;


    @GetMapping("/permission")
    public Object getPermission() {
        return sysService.getRoleByUser(1);
    }


    @GetMapping("/add")
    @RequiresPermissions("user:view")//权限管理;
    @Desc("用户view")
    @RequiresAuthentication
    public Object add() {
        return sysService.getRoleByUser(1);
    }

//
//    @GetMapping("/login")
//    public Object login(HttpServletRequest request, Map<String, Object> map) {
//        System.out.println("HomeController.login()");
//        // 登录失败从request中获取shiro处理的异常信息。
//        // shiroLoginFailure:就是shiro异常类的全类名.
//        String exception = (String) request.getAttribute("shiroLoginFailure");
//        System.out.println("exception=" + exception);
//        String msg = "";
//        if (exception != null) {
//            if (UnknownAccountException.class.getName().equals(exception)) {
//                System.out.println("UnknownAccountException -- > 账号不存在：");
//                msg = "UnknownAccountException -- > 账号不存在：";
//            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
//                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
//                msg = "IncorrectCredentialsException -- > 密码不正确：";
//            } else if ("kaptchaValidateFailed".equals(exception)) {
//                System.out.println("kaptchaValidateFailed -- > 验证码错误");
//                msg = "kaptchaValidateFailed -- > 验证码错误";
//            } else {
//                msg = "else >> " + exception;
//                System.out.println("else -- >" + exception);
//            }
//        }
//        map.put("msg", msg);
//        // 此方法不处理登录成功,由shiro进行处理
//        return "/login";
//    }


    @GetMapping("/login")
    public Object login(@Param("account") String account, @Param("password") String password) {
        UsernamePasswordToken upt = new UsernamePasswordToken(account, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(upt);
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
//        Session session = subject.getSession();
//        int userId = user.getUser_id();
//        session.setAttribute("userId", userId);
        return "/login";
    }

//    @RequestMapping(path = "/401")
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public Object unauthorized() {
//        Map<String, Object> result = new HashMap<>();
//        result.put("code", 401);
//        return result;
//    }


}
