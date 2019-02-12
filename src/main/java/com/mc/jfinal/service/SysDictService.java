package com.mc.jfinal.service;


import com.jfinal.aop.Enhancer;
import com.mc.jfinal.model.SysDict;

/**
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 *
 * UserService
 * 所有 sql 与业务逻辑写在 Service 中，不要放在 Model 中，更不
 * 要放在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
public class SysDictService {
    private static final SysDict dao = new SysDict().dao();
    public static SysDictService me = Enhancer.enhance(SysDictService.class);

    public SysDict findById(String dictkey) {
        StringBuffer hql = new StringBuffer("select * from sys_dict sy where sy.dictkey ='" + dictkey + "'");
        return dao.findFirst(hql.toString());
    }

    public void deleteById(int id) {
        dao.deleteById(id);
    }

    public void save(SysDict store) {
        store.save();
    }

    public boolean update(SysDict store) {
        return  store.update();
    }
}
