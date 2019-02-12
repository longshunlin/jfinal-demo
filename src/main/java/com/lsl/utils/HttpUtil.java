//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lsl.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.apache.commons.httpclient.HttpException;

public class HttpUtil {
    private static final String ENCODING = "UTF-8";
    private static Proxy httpProxy = new Proxy("UTF-8", '\uea60', 30000, 1024, 200);

    public HttpUtil() {
    }

    public static String invokePOST(String urlString, Map<String, String> kvs) {
        String result = "";

        try {
            result = httpProxy.post(urlString, kvs, "UTF-8");
        } catch (HttpException var4) {
            var4.printStackTrace();
        }

        return result;
    }

    public static String invokePOST(String urlString, String data) {
        String result = "";

        try {
            result = httpProxy.post(urlString, data, "UTF-8");
        } catch (HttpException var4) {
            var4.printStackTrace();
        }

        return result;
    }

    public static String invokePOSTWithHeads(String urlString, String data, Map<String, String> heads) {
        String result = "";

        try {
            result = httpProxy.post(urlString, heads, data, "UTF-8");
        } catch (HttpException var5) {
            var5.printStackTrace();
        }

        return result;
    }

    public static String invokePOSTWithHeads(String urlString, Map<String, String> data, Map<String, String> heads) {
        String result = "";

        try {
            result = httpProxy.post(urlString, heads, data, "UTF-8");
        } catch (HttpException var5) {
            var5.printStackTrace();
        }

        return result;
    }

    public static String invokePOST(String urlString, Map<String, String> kvs, String encoding) {
        String result = "";

        try {
            result = httpProxy.post(urlString, kvs, encoding);
        } catch (HttpException var5) {
            var5.printStackTrace();
        }

        return result;
    }

    public static String invokeGET(String urlString) {
        String result = "";

        try {
            result = httpProxy.get(urlString);
        } catch (HttpException var3) {
            var3.printStackTrace();
        }

        return result;
    }

    public static String invokeGET(String urlString, Map<String, String> kvs) {
        String result = "";

        try {
            result = httpProxy.get(urlString, (Map)null, kvs);
        } catch (HttpException var4) {
            var4.printStackTrace();
        }

        return result;
    }

    public static String invokeGETHead(String urlString, Map<String, String> heads) {
        String result = "";

        try {
            result = httpProxy.get(urlString, heads);
        } catch (HttpException var4) {
            var4.printStackTrace();
        }

        return result;
    }

    public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(3000);
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        InputStream inputStream = conn.getInputStream();
        byte[] getData = readInputStream(inputStream);
        File saveDir = new File(savePath);
        if(!saveDir.exists()) {
            saveDir.mkdir();
        }

        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos != null) {
            fos.close();
        }

        if(inputStream != null) {
            inputStream.close();
        }

    }

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        boolean len = false;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        int len1;
        while((len1 = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len1);
        }

        bos.close();
        return bos.toByteArray();
    }

    public static void main(String[] args) {
        String html = invokeGET("http://pms.lanren818.com/");
        System.out.println(html);
    }
}
