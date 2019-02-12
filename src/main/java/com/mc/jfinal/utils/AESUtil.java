package com.mc.jfinal.utils;

import com.lsl.utils.Base64;
import com.lsl.utils.MD5Util;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

/**
 * Created by leslie on 2018/7/3.
 */
public class AESUtil {
    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";
    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS7Padding";
    /**
     * 生成key
     */
    private static final String key = "test";
    private static SecretKeySpec secretKey  = new SecretKeySpec(MD5Util.encode(key , "UTF-8").toLowerCase().getBytes(), ALGORITHM);

    /**
     * AES加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptData(String data) throws Exception {
        // 创建密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING, "BC");
        // 初始化
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.encode(cipher.doFinal(data.getBytes()));
    }

    /**
     * AES解密
     *
     */
    public static String decryptData(String base64Data) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING, "BC");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.decode(base64Data)));
    }

    public static void main(String[] args) throws Exception {
        String reqInfoA = "jjOVU6VqMZkGRPJbJHjUp06GPj5z1NU3dfPTfVjW9UPqRKcbU24GMZNO1z72H6I499aWi4HCkvklgyqcGTCf+T/7e8IMtTcgnFyH+m4ymFv7jhWbZOHlCTHRdTQEZVv7geRYu2edaUk6kNglDrpLxdhnN0fsHHpE7PxYHxKA8BstvifjpgaLjjRx/OQ5EWF92o2Rg/i6eQDn3nZOPGUvO3bKYTFD028aINerlnnb+lHTSnLyQns/JGwUYzVxUSlgZG0a1EHK12Z638LeoMsHmhcjhGf3WsaTtucGK5P8Jezgw1HKNOl6Fz/snBZhhs2cFvmxW6tuMqlkrY4CrrZMOn+y3Mems70PGHeY5q7Hu/zMrE72+/HZYVd5bcgvuK6ax1Jszts2/CWpA3UCKfQVFoahtto5qK46YdMexA0dM+dvFtF98bbUmwPsVO2qGEs7niS+MBwt7vc/6e3uzpYvPnhJ3ujfZyqzWiBMOw4gmIxBVOoj9C6ctXRAaaHH3nPRozKHRb6SSmxO2XB7BLUmhM5cuioKah1AMld5E2ffKtH7CQjqgyUflFIR1xTkUbK3YPkr6Pp5FScIhW2j2TlSp/O8NlYVjEht/Ensootkh4vdqEXbPqBE/c87/GW2uglI6xVf8bQS04okOiYA6Vhv9vNdv01D+lLd7AuHSZ9SHC6AkKsR8BM5ZvWi7XT2kfBG6EBFRxuU3t27sp/8qjB/IannUD8NCVjOLMnkEsuk//G5hkE/Lu4lOXuCyHYi2+U+o8o9QkQq5nXJb7kZwycRYL4+15YEXjcUQLAv5V9NexCxQDQIfuRg71HscYMCXX7uSLl6rGrfk/kUJavN6FmpD7+ay0kIz3zyZrhrcJGrADa/A55BgJyhus4R5Kq2lsfRl0tFpk6SMYPPv9TiBWuG+j/AShnQLcms1XmdKnv/psAQT7Wx1FG4ZuucfWpT8N4s/b85BVzmXP7h4nJJLc7jv5uOku7pohghgthXwDV6rbvylaFWw9aOXApie5Up+XY9pRoosOVosrT4CQl/JV9isg==";
        String B = AESUtil.decryptData(reqInfoA);
        System.out.println(B);
    }
}
