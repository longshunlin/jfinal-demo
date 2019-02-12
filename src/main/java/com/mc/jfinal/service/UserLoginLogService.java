package com.mc.jfinal.service;


import com.jfinal.aop.Duang;
import com.jfinal.plugin.activerecord.Page;
import com.mc.jfinal.model.UserLoginLog;

import java.util.List;

/**
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 *
 * UserService
 * 所有 sql 与业务逻辑写在 Service 中，不要放在 Model 中，更不
 * 要放在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
public class UserLoginLogService {
    private static final UserLoginLog daoUser = new UserLoginLog().dao();

    public static UserLoginLogService me = Duang.duang(new UserLoginLogService());

    public Page<UserLoginLog> paginate(Integer userId, int pageNumber, int pageSize) {
        StringBuffer from = new StringBuffer("from user_login_log where 1 = 1");
        if (userId != null) {
            from.append(" and userId = ").append(userId);
        }
        String totalRowSql = "select count(*) " + from;
        String findSql = "select * " + from + " order by createDate desc";
        Page<UserLoginLog> page = new Page<>();
        page = daoUser.paginateByFullSql(pageNumber, pageSize, totalRowSql, findSql);
        return page;
    }

    public UserLoginLog findById(int id) {
        return daoUser.findById(id);
    }


    public void deleteById(int id) {
        daoUser.deleteById(id);
    }

    public void save(UserLoginLog userLoginLog) {
        userLoginLog.save();
    }

    public List<UserLoginLog> findAll( Integer userId) {
        return daoUser.find("select * from user_login_log where userId = ? order by loginTime desc",userId);
    }

    public boolean update(UserLoginLog userLoginLog) {
        return  userLoginLog.update();
    }
}
