package com.mc.jfinal.common;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.mc.jfinal.controller.*;
import com.mc.jfinal.handler.FakeStaticHandler;
import com.mc.jfinal.interceptor.SqlInterceptor;
import com.mc.jfinal.model._MappingKit;
import com.mc.jfinal.utils.CommonConfig;

/**
 *
 * API引导式配置
 */
public class DemoConfig extends JFinalConfig {

    /**
     * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
     *
     * 使用本方法启动过第一次以后，会在开发工具的 debug、run config 中自动生成
     * 一条启动配置，可对该自动生成的配置再添加额外的配置项，例如 VM argument 可配置为：
     * -XX:PermSize=64M -XX:MaxPermSize=256M
     */
    public static void main(String[] args) {
        /**
         * 特别注意：Eclipse 之下建议的启动方式
         */
        //JFinal.start("src/main/webapp", 80, "/", 5);

        /**
         * 特别注意：IDEA 之下建议的启动方式，仅比 eclipse 之下少了最后一个参数
         */
        JFinal.start("src/main/webapp", 80, "/");
    }

    /**
     * 配置常量
     */
    public void configConstant(Constants me) {
        // 加载少量必要配置，随后可用PropKit.get(...)获取值
        PropKit.use("a_little_config.txt");
        me.setDevMode(PropKit.getBoolean("devMode", false));
        me.setError404View("/404.html");
        me.setErrorView(400 , "/404.html");
        me.setError500View("/500.html");
        me.setMaxPostSize(500*1024*1024);
    }

    /**
     * 配置路由
     */
    public void configRoute(Routes me) {
        me.add("/", IndexController.class);
        me.add("/admin", AdminController.class);
        me.add("/login", LoginController.class);
    }

    public void configEngine(Engine me) {
        me.setDevMode(true);
        me.addSharedFunction("/common/admin/_layout.html");
        me.addSharedFunction("/common/store/_layout.html");
        me.addSharedFunction("/common/_paginate.html");
        me.addSharedMethod(new CommonConfig());
    }

    /**
     * 配置插件
     */
    public void configPlugin(Plugins me) {
        // 配置 druid 数据库连接池插件
        DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
        me.add(druidPlugin);

        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        // 所有映射在 MappingKit 中自动化搞定
        _MappingKit.mapping(arp);
        me.add(arp);

        // 同步作业
        Cron4jPlugin cron4jPlugin = new Cron4jPlugin("c4j.txt");
        me.add(cron4jPlugin);
    }

    public static DruidPlugin createDruidPlugin() {
        return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
    }

    /**
     * 配置全局拦截器
     */
    public void configInterceptor(Interceptors me) {
        me.addGlobalServiceInterceptor(new SqlInterceptor());
    }

    /**
     * 配置处理器
     */
    public void configHandler(Handlers me) {
        me.add(new FakeStaticHandler());
    }

    // 系统启动完成后回调
    public void afterJFinalStart() {
        CommonConfig.reloadConfig();
    }
}
