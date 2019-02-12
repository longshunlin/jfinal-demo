package com.mc.jfinal.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.mc.jfinal.interceptor.LoginInterceptor;
import com.mc.jfinal.service.*;

import java.util.HashMap;
import java.util.Map;

@Before(LoginInterceptor.class)
public class AdminController extends Controller {
    UserService userService = UserService.me;
    UserLoginLogService userLoginLogService = UserLoginLogService.me;
    SysDictService sysDictService = SysDictService.me;

    @Clear
    public void logout() {
        removeSessionAttr("user");
        redirect("/login");
    }

    public void index() {

        render("index.html");
    }


    public void testJson() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("msg", "测试返回json");
        renderJson(result);
    }
    /*********************************/

    /**
     * 获取jqgrid传送的页码
     * @return
     */
    private Integer getPageNo() {
        String page = getPara("page");
        Integer pageNo = 1;
        if(StrKit.notBlank(page)) {
            try {
                pageNo = Integer.valueOf(page);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pageNo;
    }

    /**
     * 获取分页的每页数量
     * @return
     */
    private Integer getRows() {
        return getParaToInt("rows" , 10);
    }
}
