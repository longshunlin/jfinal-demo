package com.mc.jfinal.service;


import com.google.gson.Gson;
import com.jfinal.aop.Duang;
import com.mc.jfinal.model.User;

/**
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 *
 * UserService
 * 所有 sql 与业务逻辑写在 Service 中，不要放在 Model 中，更不
 * 要放在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
public class UserService {
    private static final User daoUser = new User().dao();

    public static UserService me = Duang.duang(new UserService());

    public User findById(String id) {
        return daoUser.findById(id);
    }
    public User findByUserName(String userName) {
        return daoUser.findFirst("select * from user where userName= ? or email= ?", userName, userName);
    }
}
