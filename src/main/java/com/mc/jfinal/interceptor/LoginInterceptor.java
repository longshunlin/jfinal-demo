package com.mc.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.mc.jfinal.model.User;
import com.mc.jfinal.service.UserService;

/**
 * Created by leslie on 2018/4/20.
 */
public class LoginInterceptor implements Interceptor {
    public void intercept(Invocation inv) {
        Controller ctl = inv.getController();
        User user = null;
        String userId = ctl.getCookie("userId");
        if(StrKit.notBlank(userId)) {
            user = UserService.me.findById(userId);
        }
        if (user == null) {
            ctl.redirect("/login");
        } else {
            ctl.setAttr("admin", user);
            inv.invoke();
        }
    }
}
