package com.mc.jfinal.controller;

import com.jfinal.core.Controller;
import com.lsl.utils.DateUtil;
import com.lsl.utils.MD5Util;
import com.mc.jfinal.model.User;
import com.mc.jfinal.model.UserLoginLog;
import com.mc.jfinal.service.UserLoginLogService;
import com.mc.jfinal.service.UserService;
import com.mc.jfinal.utils.CommonUtils;

import java.util.Date;


public class LoginController extends Controller {
    UserService userService = UserService.me;
    UserLoginLogService userLoginLogService = UserLoginLogService.me;

    public void index() {
        render("login.html");
    }

    public void doLogin(){
        String userName = getPara("userName");
        String userPwd = getPara("userPwd");
        User user = userService.findByUserName(userName);
        if (user == null) {
            renderText("-1");
            return;
        }
        String userPsw = MD5Util.encode(userPwd);
        if (!user.getUserPsw().equals(userPsw)) {
            renderText("-2");
            return;
        }
        if (user.getStatus() == 1) {
            renderText("-3");
            return;
        }
        UserLoginLog userLoginLog = new UserLoginLog();
        userLoginLog.setUserId(user.getUserId());
        userLoginLog.setLoginIP(CommonUtils.getIPAddress(getRequest()));
        userLoginLog.setLoginTime(DateUtil.DateTimeToStr(new Date()));
        userLoginLogService.save(userLoginLog);

        setCookie("userId" , user.getUserId().toString() , -1 , true );
        renderText("0");
    }

}
