package com.mc.jfinal.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by leslie on 2018/5/23.
 */
public class QiniuUtil {
    static Zone zone = Zone.zone2(); //华南地区
    /**
     * 删除资源文件
     * @param accessKey 访问key
     * @param secretKey 访问秘钥
     * @param bucket 存储空间
     * @param fileKey 文件名
     * @return
     */
    public static boolean delFile(String accessKey , String secretKey , String bucket , String fileKey) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(zone);
        Auth auth = Auth.create( accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        boolean ret = false;
        try {
            bucketManager.delete(bucket, fileKey);
            ret = true;
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
        return ret;
    }

    public static boolean uploadFile(String accessKey , String secretKey , String bucket , File file , String targetFileKey ) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(zone);

        UploadManager uploadManager = new UploadManager(cfg);

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = targetFileKey;
        boolean ret = false;
        try {
            //byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
            //ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            FileInputStream fis = new FileInputStream(file);
            try {
                Response response = uploadManager.put( fis , key , upToken ,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                ret = true;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return ret;
    }
}
