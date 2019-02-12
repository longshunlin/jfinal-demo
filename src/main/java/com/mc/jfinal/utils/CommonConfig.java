package com.mc.jfinal.utils;

import com.jfinal.kit.StrKit;
import com.lsl.utils.DateUtil;
import com.lsl.utils.HttpUtil;
import com.lsl.utils.StringUtil;
import com.mc.jfinal.model.SysDict;
import com.mc.jfinal.service.SysDictService;
import net.sf.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonConfig {
    /**
     * 微信的应用ID
     */
    private static String appId = "";
    /**
     * 微信的应用secret
     */
    private static String appSecret = "";
    /**
     * 商户ID
     */
    private static String mchId = "";
    /**
     * 商户secret
     */
    private static String mchSecret = "";

    /**
     * 微信回调地址
     */
    private static String notifyUrl = "";

    /**
     * 网站的title
     */
    private static String webTitle = "";
    /**
     * 网站的域名
     */
    private static String domain = "pf.mc510.com";
    /**
     * 支付域名
     */
    private static String payDomain = "pf.mc510.com";

    private static Integer pointRate = 100;
    private static Integer pointInvite = 200;
    private static Integer pointNewUser = 300;
    private static BigDecimal buyPointRate;

    private static String qiniuAccessKey = "";
    private static String qiniuSecretKey = "";
    private static String qiniuBucket = "";
    private static String qiniuDomain = "";

    /**
     * 应用访问秘钥
     */
    private static String access_token = "";
    private static Long access_token_expires = 0L;

    /**
     * 短信接口的API配置
     */
    private static Integer smsAppId = 1400047662;
    private static String smsAppKey = "";
    private static String smsSig = "";
    private static Integer smsTotal = 0;
    private static Integer smsUse = 0;

    private static boolean isTest = false;

    /**
     * VIP的等级配置
     */
    private static Map<Integer, Integer> vipLevelMap = new HashMap<>();

    /**
     * 广告位
     */
    private static String adOneTitle = "";
    private static String adOneDesc = "";
    private static String adOneLink = "";
    private static String adOnePic = "";
    private static String adTwoTitle = "";
    private static String adTwoDesc = "";
    private static String adTwoLink = "";
    private static String adTwoPic = "";
    private static String adThreeTitle = "";
    private static String adThreeDesc = "";
    private static String adThreeLink = "";
    private static String adThreePic = "";
    private static String inviteImg = "";
    private static String inviteTitle = "";
    private static String inviteDesc = "";
    private static String inviteCoupon = "";
    private static String beInviteCoupon = "";
    private static String beInviteCoupon1 = "";
    private static String beInviteCoupon2 = "";
    private static String evaluationCoupon = "";

    /************************************/
    private static String storeOrginal = "";
    private static String storePrice = "";
    private static String studentPercent = "";
    /************************************/

    /**
     * jsapi_ticket有效期7200秒，开发者必须在自己的服务全局缓存jsapi_ticket
     */
    private static String jsapi_ticket = "";
    private static Long jsapi_ticket_expires = 0L;

    public static void reloadConfig() {
        SysDict sysDict = SysDictService.me.findById("sys-web-config");
        if (sysDict != null && StrKit.notBlank(sysDict.getDictValue())) {
            String[] vls = sysDict.getDictValue().split(",");
            if (vls.length > 0) {
                webTitle = vls[0];
            }
            if (vls.length > 1) {
                domain = vls[1];
            }
            if (vls.length > 2) {
                pointRate = Integer.parseInt(vls[2]);
            }
            if (vls.length > 3) {
                pointInvite = Integer.parseInt(vls[3]);
            }
            if (vls.length > 4) {
                pointNewUser = Integer.parseInt(vls[4]);
            }
            if (vls.length > 5) {
                buyPointRate = new BigDecimal(vls[5]);
            }
        }

        sysDict = SysDictService.me.findById("sys-wx-config");
        if (sysDict != null && StringUtil.isNotBlank(sysDict.getDictValue())) {
            String[] vls = sysDict.getDictValue().split(",");
            if (vls.length > 0) {
                appId = vls[0];
            }
            if (vls.length > 1) {
                appSecret = vls[1];
            }
        }

        sysDict = SysDictService.me.findById("sys-sms-config");
        if (sysDict != null && StringUtil.isNotBlank(sysDict.getDictValue())) {
            String[] vls = sysDict.getDictValue().split(",");
            if (vls.length > 0) {
                smsAppId = Integer.parseInt(vls[0]);
            }
            if (vls.length > 1) {
                smsAppKey = vls[1];
            }
            if (vls.length > 2) {
                smsSig = vls[2];
            }
        }

        sysDict = SysDictService.me.findById("sms-status");
        if (sysDict != null && StringUtil.isNotBlank(sysDict.getDictValue())) {
            String[] vls = sysDict.getDictValue().split(",");
            if (vls.length > 0) {
                smsTotal = Integer.parseInt(vls[0]);
            }
            if (vls.length > 1) {
                smsUse = Integer.parseInt(vls[1]);
            }
        }

        sysDict = SysDictService.me.findById("sys_pay_domain");
        if (sysDict != null && StringUtil.isNotBlank(sysDict.getDictValue())) {
            payDomain = sysDict.getDictValue();
        }


        sysDict = SysDictService.me.findById("sys-vip-level");
        if (sysDict != null && StringUtil.isNotBlank(sysDict.getDictValue())) {
            String[] vls = sysDict.getDictValue().split(",");
            vipLevelMap.clear();
            for (int i = 0; i < vls.length; ++i) {
                vipLevelMap.put(i + 1, Integer.parseInt(vls[i]));
            }
        }
        sysDict = SysDictService.me.findById("sys-wx-adOne");
        if (sysDict != null && StringUtil.isNotBlank(sysDict.getDictValue())) {
            String[] vls = sysDict.getDictValue().split("###");
            if (vls.length > 0) {
                adOneTitle = vls[0];
            }
            if (vls.length > 1) {
                adOneDesc = vls[1];
            }
            if (vls.length > 2) {
                adOneLink = vls[2];
            }
            if (vls.length > 3) {
                adOnePic = vls[3];
            }
        }
        sysDict = SysDictService.me.findById("sys-wx-adTwo");
        if (sysDict != null && StringUtil.isNotBlank(sysDict.getDictValue())) {
            String[] vls = sysDict.getDictValue().split("###");
            if (vls.length > 0) {
                adTwoTitle = vls[0];
            }
            if (vls.length > 1) {
                adTwoDesc = vls[1];
            }
            if (vls.length > 2) {
                adTwoLink = vls[2];
            }
            if (vls.length > 3) {
                adTwoPic = vls[3];
            }
        }
        sysDict = SysDictService.me.findById("sys-wx-adThree");
        if (sysDict != null && StringUtil.isNotBlank(sysDict.getDictValue())) {
            String[] vls = sysDict.getDictValue().split("###");
            if (vls.length > 0) {
                adThreeTitle = vls[0];
            }
            if (vls.length > 1) {
                adThreeDesc = vls[1];
            }
            if (vls.length > 2) {
                adThreeLink = vls[2];
            }
            if (vls.length > 3) {
                adThreePic = vls[3];
            }
        }
        sysDict = SysDictService.me.findById("sys-qiniu-config");
        if (sysDict != null && StringUtil.isNotBlank(sysDict.getDictValue())) {
            String[] vls = sysDict.getDictValue().split(",");
            if (vls.length == 4) {
                qiniuAccessKey = vls[0];
                qiniuSecretKey = vls[1];
                qiniuBucket = vls[2];
                qiniuDomain = vls[3];
            }
        }
        sysDict = SysDictService.me.findById("inviteDesc");
        if (sysDict != null && StringUtil.isNotBlank(sysDict.getDictValue())) {
            inviteDesc = sysDict.getDictValue();
        }
        sysDict = SysDictService.me.findById("inviteTitle");
        if (sysDict != null && StringUtil.isNotBlank(sysDict.getDictValue())) {
            inviteTitle = sysDict.getDictValue();
        }
        sysDict = SysDictService.me.findById("inviteImg");
        if (sysDict != null && StringUtil.isNotBlank(sysDict.getDictValue())) {
            inviteImg = sysDict.getDictValue();
        }
        sysDict = SysDictService.me.findById("evaluationCoupon");
        if (sysDict != null) {
            evaluationCoupon = sysDict.getDictValue();
        }

        sysDict = SysDictService.me.findById("inviteCoupon");
        if (sysDict != null && StringUtil.isNotBlank(sysDict.getDictValue())) {
            inviteCoupon = sysDict.getDictValue();
        }
        sysDict = SysDictService.me.findById("beInviteCoupon");
        if (sysDict != null && StringUtil.isNotBlank(sysDict.getDictValue())) {
            String[] vls = sysDict.getDictValue().split(",");
            if (vls.length == 2) {
                beInviteCoupon1 = vls[0];
                beInviteCoupon2 = vls[1];
            }
        }
    }

    public static void useSMS(int count) {
        smsUse += count;
        SysDict sysDict = new SysDict();
        sysDict.setDictKey("sms-status");
        sysDict.setDictValue(smsTotal + "," + smsUse);
        SysDict oldSys = SysDictService.me.findById("sms-status");
        if (oldSys == null) {
            SysDictService.me.save(sysDict);
        } else {
            SysDictService.me.update(sysDict);
        }

    }

    /**
     * 计算会员当前等级
     *
     * @param totalIntegral 累计积分
     * @param nowVipLevel   当前等级
     * @return
     */
    public static Integer calVipLevel(Integer totalIntegral, Integer nowVipLevel) {
        Integer vipLevel = 1;
        for (int i = 1; i < 6; ++i) {
            if (vipLevelMap.containsKey(i)) {
                if (totalIntegral >= vipLevelMap.get(i)) {
                    vipLevel = i;
                }
            }
        }
        if (nowVipLevel < vipLevel) {
            return vipLevel;
        } else {
            return nowVipLevel;
        }
    }

    /**
     * 获取应用的访问令牌
     *
     * @return
     */
    public static String getAccessToken(boolean mustNew) {
        Long nowTick = DateUtil.getNow();
        if (nowTick >= access_token_expires || StringUtil.isBlank(access_token) || mustNew) {
            //https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
            String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
            String json = HttpUtil.invokeGET(accessTokenUrl);
            if (StringUtil.isNotBlank(json)) {
                JSONObject jsonObject = JSONObject.fromObject(json);
                if (jsonObject.containsKey("access_token")) {
                    access_token = jsonObject.get("access_token").toString();
                    //提前30秒失效，避免刚好失效的情况
                    access_token_expires = nowTick + (jsonObject.getLong("expires_in") - 120) * 1000;
                } else {
                    access_token = "";
                    access_token_expires = 0L;
                }
            }
        }
        return access_token;
    }


    public static String getVipName(Integer vipLevel) {
        String name = "";
        switch (vipLevel) {
            case 1:
                name = "1星客户";
                break;
            case 2:
                name = "2星客户";
                break;
            case 3:
                name = "3星客户";
                break;
            case 4:
                name = "4星客户";
                break;
            case 5:
                name = "5星客户";
                break;
        }
        return name;
    }

    public static List<String> getVIPNameList() {
        List<String> vips = new ArrayList<>();
        vips.add("1星客户");
        vips.add("2星客户");
        vips.add("3星客户");
        vips.add("4星客户");
        vips.add("5星客户");
        return vips;
    }

    public static Integer getPointRate() {
        return pointRate;
    }

    public static String getAppId() {
        return appId;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    public static String getWebTitle() {
        return webTitle;
    }

    public static String getDomain() {
        return domain;
    }

    public static Integer getSmsAppId() {
        return smsAppId;
    }

    public static String getSmsAppKey() {
        return smsAppKey;
    }

    public static String getSmsSig() {
        return smsSig;
    }

    public static Integer getSmsTotal() {
        return smsTotal;
    }

    public static Integer getSmsUse() {
        return smsUse;
    }

    public static Integer getSmsCanUse() {
        return smsTotal - smsUse;
    }

    public static String getAdOneTitle() {
        return adOneTitle;
    }

    public static String getAdOneDesc() {
        return adOneDesc;
    }

    public static String getAdOneLink() {
        return adOneLink;
    }

    public static String getAdOnePic() {
        return adOnePic;
    }

    public static String getAdTwoTitle() {
        return adTwoTitle;
    }

    public static String getAdTwoDesc() {
        return adTwoDesc;
    }

    public static String getAdTwoLink() {
        return adTwoLink;
    }

    public static String getAdTwoPic() {
        return adTwoPic;
    }

    public static String getAdThreeTitle() {
        return adThreeTitle;
    }

    public static String getAdThreeDesc() {
        return adThreeDesc;
    }

    public static String getAdThreeLink() {
        return adThreeLink;
    }

    public static String getAdThreePic() {
        return adThreePic;
    }

    public static Integer getPointInvite() {
        return pointInvite;
    }

    public static Integer getPointNewUser() {
        return pointNewUser;
    }

    public static boolean isTest() {
        SysDict sysDict = SysDictService.me.findById("is_test");
        if (sysDict == null || !sysDict.getDictValue().equals("1")) {
            isTest = false;
        } else {
            isTest = true;
        }
        return isTest;
    }

    public static String getPayDomain() {
        return payDomain;
    }

    public static BigDecimal getBuyPointRate() {
        return buyPointRate;
    }

    /**
     * 获取js接口需要的ticket
     *
     * @return
     */
    public static String getJsapiTicket() {
        Long nowTick = DateUtil.getNow();
        if (nowTick >= jsapi_ticket_expires || StringUtil.isBlank(jsapi_ticket)) {
            String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + getAccessToken(true) + "&type=jsapi";
            String json = HttpUtil.invokeGET(url);
            JSONObject jsonObject = JSONObject.fromObject(json);
            if (jsonObject.getString("errmsg").equals("ok")) {
                jsapi_ticket = jsonObject.get("ticket").toString();
                //提前30秒失效，避免刚好失效的情况
                jsapi_ticket_expires = nowTick + (jsonObject.getLong("expires_in") - 60) * 1000;
            }
        }
        return jsapi_ticket;
    }

    public static String getQiniuAccessKey() {
        return qiniuAccessKey;
    }

    public static String getQiniuSecretKey() {
        return qiniuSecretKey;
    }

    public static String getQiniuBucket() {
        return qiniuBucket;
    }

    public static String getQiniuDomain() {
        return qiniuDomain;
    }

    public static String getInviteImg() {
        return inviteImg;
    }

    public static String getInviteTitle() {
        return inviteTitle;
    }

    public static String getInviteDesc() {
        return inviteDesc;
    }

    public static String getStoreOrginal() {
        return storeOrginal;
    }

    public static String getStorePrice() {
        return storePrice;
    }

    public static String getStudentPercent() {
        return studentPercent;
    }

    public static String getMchId() {
        return mchId;
    }

    public static String getMchSecret() {
        return mchSecret;
    }

    public static String getNotifyUrl() {
        return notifyUrl;
    }

    public static String getInviteCoupon() {
        return inviteCoupon;
    }

    public static String getBeInviteCoupon() {
        return beInviteCoupon;
    }

    public static String getBeInviteCoupon1() {
        return beInviteCoupon1;
    }

    public static String getBeInviteCoupon2() {
        return beInviteCoupon2;
    }

    public static String getEvaluationCoupon() {
        return evaluationCoupon;
    }
}
