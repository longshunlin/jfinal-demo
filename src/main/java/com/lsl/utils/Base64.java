//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lsl.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Base64 {
    private static char[] base64EncodeChars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static byte[] base64DecodeChars = new byte[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};

    public Base64() {
    }

    public static String getEncode(String str) {
        byte[] byteStr = str.getBytes();
        String strEncode = encode(byteStr);
        return strEncode;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.err.println(getEncode("1231321"));
        System.err.println(getDecode(getEncode("1231321")));
    }

    public static String getDecode(String encodeStr) throws UnsupportedEncodingException {
        if(encodeStr != null && !encodeStr.equals("")) {
            byte[] deCode = decode(encodeStr);
            String str = new String(deCode);
            return str;
        } else {
            return "";
        }
    }

    public static String encode(byte[] data) {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i = 0;

        while(i < len) {
            int b1 = data[i++] & 255;
            if(i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 3) << 4]);
                sb.append("==");
                break;
            }

            int b2 = data[i++] & 255;
            if(i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 3) << 4 | (b2 & 240) >>> 4]);
                sb.append(base64EncodeChars[(b2 & 15) << 2]);
                sb.append("=");
                break;
            }

            int b3 = data[i++] & 255;
            sb.append(base64EncodeChars[b1 >>> 2]);
            sb.append(base64EncodeChars[(b1 & 3) << 4 | (b2 & 240) >>> 4]);
            sb.append(base64EncodeChars[(b2 & 15) << 2 | (b3 & 192) >>> 6]);
            sb.append(base64EncodeChars[b3 & 63]);
        }

        return sb.toString();
    }

    public static byte[] decode(String str) throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        byte[] data = str.getBytes("US-ASCII");
        int len = data.length;
        int i = 0;

        while(i < len) {
            byte b1;
            do {
                b1 = base64DecodeChars[data[i++]];
            } while(i < len && b1 == -1);

            if(b1 == -1) {
                break;
            }

            byte b2;
            do {
                b2 = base64DecodeChars[data[i++]];
            } while(i < len && b2 == -1);

            if(b2 == -1) {
                break;
            }

            sb.append((char)(b1 << 2 | (b2 & 48) >>> 4));

            byte b3;
            do {
                b3 = data[i++];
                if(b3 == 61) {
                    return sb.toString().getBytes("ISO-8859-1");
                }

                b3 = base64DecodeChars[b3];
            } while(i < len && b3 == -1);

            if(b3 == -1) {
                break;
            }

            sb.append((char)((b2 & 15) << 4 | (b3 & 60) >>> 2));

            byte b4;
            do {
                b4 = data[i++];
                if(b4 == 61) {
                    return sb.toString().getBytes("ISO-8859-1");
                }

                b4 = base64DecodeChars[b4];
            } while(i < len && b4 == -1);

            if(b4 == -1) {
                break;
            }

            sb.append((char)((b3 & 3) << 6 | b4));
        }

        return sb.toString().getBytes("ISO-8859-1");
    }

    public static String encodeImage(String imgFilePath) {
        byte[] data = null;

        try {
            FileInputStream e = new FileInputStream(imgFilePath);
            data = new byte[e.available()];
            e.read(data);
            e.close();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return data != null?encode(data):"";
    }

    public static String encodeFile(File file) {
        byte[] data = null;

        try {
            FileInputStream e = new FileInputStream(file);
            data = new byte[e.available()];
            e.read(data);
            e.close();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return data != null?encode(data):"";
    }

    public static boolean generateImage(String imgStr, String imgFilePath) {
        if(StringUtil.isBlank(imgStr)) {
            return false;
        } else {
            try {
                byte[] e = decode(imgStr);

                for(int file = 0; file < e.length; ++file) {
                    if(e[file] < 0) {
                        e[file] = (byte)(e[file] + 256);
                    }
                }

                File var6 = new File(imgFilePath);
                if(!var6.getParentFile().isDirectory()) {
                    File out = new File(var6.getParent());
                    out.mkdirs();
                }

                FileOutputStream var7 = new FileOutputStream(imgFilePath);
                var7.write(e);
                var7.flush();
                var7.close();
                return true;
            } catch (Exception var5) {
                var5.printStackTrace();
                return false;
            }
        }
    }
}
