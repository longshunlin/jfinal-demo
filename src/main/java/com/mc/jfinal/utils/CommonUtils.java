package com.mc.jfinal.utils;

import com.jfinal.kit.StrKit;
import com.lsl.utils.StringUtil;
import com.lsl.utils.VerifyCodeUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/4/23.
 */
public class CommonUtils {

    // \b 是单词边界(连着的两个(字母字符 与 非字母字符) 之间的逻辑上的间隔),
    // 字符串在编译时会被转码一次,所以是 "\\b"
    // \B 是单词内部逻辑间隔(连着的两个字母字符之间的逻辑上的间隔)
    static String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i"
            +"|windows (phone|ce)|blackberry"
            +"|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp"
            +"|laystation portable)|nokia|fennec|htc[-_]"
            +"|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
    static String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser"
            +"|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";

    //移动设备正则匹配：手机端、平板
    static Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);
    static Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE);

    /**
     * 检测是否是移动设备访问
     *
     * @Title: check
     * @Date : 2014-7-7 下午01:29:07
     * @param userAgent 浏览器标识
     * @return true:移动设备接入，false:pc端接入
     */
    public static boolean check(String userAgent){
        if(null == userAgent){
            userAgent = "";
        }
        // 匹配
        Matcher matcherPhone = phonePat.matcher(userAgent);
        Matcher matcherTable = tablePat.matcher(userAgent);
        if(matcherPhone.find() || matcherTable.find()){
            return true;
        } else {
            return false;
        }
    }

    public static String getIPAddress(HttpServletRequest request) {
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static void authImage(int codeLength,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(codeLength);
        //存入会话session
        HttpSession session = request.getSession(true);
        //删除以前的
        session.removeAttribute("verifyCode");
        session.setAttribute("verifyCode", verifyCode.toLowerCase());
        //生成图片
        int w = 114, h = 42;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }

    public static int getCurrentAgeByBirthdate(String brithday)
            throws Exception {
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            String currentTime = formatDate.format(calendar.getTime());
            Date today = formatDate.parse(currentTime);
            Date brithDay = formatDate.parse(brithday);

            return today.getYear() - brithDay.getYear();
        } catch (Exception e) {
            return 0;
        }
    }

    public static String getDate(String date) {
        try {
            String[] vls = date.split(" ");
            String[] vl = vls[0].split("-");
            String[] ul = vls[1].split(":");
            String dateTime = Integer.parseInt(vl[1] )+ "月" + Integer.parseInt(vl[2] ) + "日" + ul[0] + ":" + ul[1];
            return dateTime;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getPhoneShow(String phone) {
        if(StrKit.isBlank(phone)) return "";
        String result = "";
        for (int i=0; i < phone.length(); ++i) {
            if(i > 2 && i < 7) {
                result += '*';
            } else {
                result += phone.charAt(i);
            }
        }
        return result;
    }

    public static boolean isNumber(String str) {
        if (StringUtil.isBlank(str)) {
            return false;
        }
        try {
            Double.parseDouble(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String getOutTradeNo() {
        return UUID.randomUUID().toString().toLowerCase().replace("-", "");
    }
}
