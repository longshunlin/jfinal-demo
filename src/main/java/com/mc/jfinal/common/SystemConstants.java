package com.mc.jfinal.common;

/**
 * Created by longshunlin on 2018/11/21.
 */
public final class SystemConstants {
    public final static String DEFAULT_PASSWORD = "123456";

    /** 用户状态 */
    public final static int USER_STATUS_OPEN = 0;
    public final static int USER_STATUS_FORB = 1;

    /** 门店状态 */
    public final static int STORE_STATUS_OPEN = 1;
    public final static int STORE_STATUS_FORB = 0;

    /** 用户类型 */
    public final static int USER_TYPE_ADMIN = 0;
    public final static int USER_TYPE_DZ = 1;
    public final static int USER_TYPE_MEMBER = 2;
    public final static int USER_TYPE_EMPLOYEE = 3;

    /** 店铺价格配置key */
    public final static String STORE_ORGINAL_KEY = "store_orginal";
    public final static String STORE_PRICE_KEY = "store_price";
    public final static String STUDENT_PERCENT_KEY = "student_percent";

    /** 订单状态 0待消费 1服务中 2待支付 3已完成 4已取消 5已过号 */
    public final static int ORDER_STATUS_WAIT = 0;
    public final static int ORDER_STATUS_SERVICE = 1;
    public final static int ORDER_STATUS_PAY = 2;
    public final static int ORDER_STATUS_COMPLETE = 3;
    public final static int ORDER_STATUS_CANCEL = 4;
    public final static int ORDER_STATUS_STEP = 5;

    /** 门店上下班状态切换 0-下班， 1-上班*/
    public final static int STORE_WORK_STATUS_REST = 0;
    public final static int STORE_WORK_STATUS_WORK = 1;

    /** 用户上下班状态切换 0-下班， 1-上班*/
    public final static int USER_WORK_STATUS_REST = 0;
    public final static int USER_WORK_STATUS_WORK = 1;

    /** 微信端登陆用户cookie保存的key **/
    public final static String WX_OPENID = "wx_openId";
    public final static String WX_INVITE_OPENID = "wx_invite_openId";
    public final static String WXEMP_OPENID = "wxEmp_openId";
    public final static String WXBOSS_OPENID = "wxBoss_openId";

    /** 文件关联类型 */
    // 评价
    public final static String FILE_RELATION_EVAL = "1";

    /** 微信配置key */
    public final static String SYS_DICT_WX_CONFIG_KEY = "sys-wx-config";
    /** 微信商户配置 (商户号,安全码) */
    public final static String SYS_DICT_MCH_CONFIG_KEY = "sys-mch-config";
    /** 支付回调地址配置 */
    public final static String SYS_DICT_NOTIFY_CONFIG_KEY = "sys-notify-config";

    /** 打卡异常最远距离 */
    public final static int ATTENDANCE_EXEC_MAX_DISTANCE = 300;
    /** 打卡异常类型 */
    public final static int ATTENDANCE_EXCE_TYPE_DISTANCE = 1;
    public final static int ATTENDANCE_EXCE_TYPE_TIME = 2;
}
