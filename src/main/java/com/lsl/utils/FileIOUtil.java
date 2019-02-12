//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lsl.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.commons.lang3.StringUtils;

public class FileIOUtil {
    public FileIOUtil() {
    }

    public static Map<String, String> readFile2Map(String path, String regular) throws IOException {
        if(StringUtils.isBlank(regular)) {
            return null;
        } else if(StringUtils.isBlank(path)) {
            return null;
        } else {
            HashMap map = new HashMap();
            File file = new File(path);
            FileReader fReader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fReader);
            String temp = null;

            for(String[] tempArr = (String[])null; (temp = bReader.readLine()) != null; tempArr = (String[])null) {
                if(StringUtils.isNotBlank(temp)) {
                    tempArr = temp.split(regular);
                    if(tempArr.length > 1) {
                        String username = tempArr[0];
                        String password = tempArr[1];
                        if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
                            map.put(username.trim(), password.trim());
                        }
                    }
                }

                temp = null;
            }

            bReader.close();
            fReader.close();
            file = null;
            return map;
        }
    }

    public static String readFile2Str(String path) {
        if(StringUtils.isBlank(path)) {
            return null;
        } else {
            FileInputStream fis = null;
            InputStreamReader isr = null;
            StringBuilder str = null;

            try {
                fis = new FileInputStream(path);
                isr = new InputStreamReader(fis);
                BufferedReader e = new BufferedReader(isr);
                str = new StringBuilder();

                for(String temp = null; (temp = e.readLine()) != null; temp = null) {
                    str.append(temp);
                }
            } catch (FileNotFoundException var21) {
                var21.printStackTrace();
            } catch (IOException var22) {
                var22.printStackTrace();
            } finally {
                if(fis != null) {
                    try {
                        fis.close();
                    } catch (IOException var20) {
                        var20.printStackTrace();
                    }

                    fis = null;
                }

                if(isr != null) {
                    try {
                        isr.close();
                    } catch (IOException var19) {
                        var19.printStackTrace();
                    }

                    isr = null;
                }

            }

            return str.toString();
        }
    }

    public static String convertInputStream2Str(InputStream is, String encoding) {
        if(is == null) {
            return null;
        } else if(StringUtils.isBlank(encoding)) {
            return null;
        } else {
            StringBuffer sb = null;
            BufferedReader br = null;

            try {
                br = new BufferedReader(new InputStreamReader(is, encoding));
                sb = new StringBuffer(100);

                String e;
                while((e = br.readLine()) != null) {
                    sb.append(e);
                }

                String var5 = sb.toString();
                return var5;
            } catch (UnsupportedEncodingException var17) {
                var17.printStackTrace();
            } catch (IOException var18) {
                var18.printStackTrace();
            } finally {
                if(br != null) {
                    try {
                        br.close();
                    } catch (IOException var16) {
                        var16.printStackTrace();
                    }

                    br = null;
                }

            }

            return null;
        }
    }

    public static boolean writeString2File(String content, String path) throws Exception {
        if(StringUtils.isBlank(content)) {
            return false;
        } else if(StringUtils.isBlank(path)) {
            return false;
        } else {
            File file = new File(path);
            FileWriter fileWriter = null;

            try {
                fileWriter = new FileWriter(file);
                fileWriter.write(content);
                boolean e = true;
                return e;
            } catch (IOException var14) {
                var14.printStackTrace();
            } finally {
                if(fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (IOException var13) {
                        var13.printStackTrace();
                    }

                    fileWriter = null;
                }

                if(file != null) {
                    file = null;
                }

            }

            return false;
        }
    }

    public static boolean saveFileByPhysicalDir(String physicalPath, InputStream istream) {
        boolean flag = false;

        try {
            FileOutputStream e = new FileOutputStream(physicalPath);
            boolean readBytes = false;
            byte[] buffer = new byte[8192];

            int readBytes1;
            while((readBytes1 = istream.read(buffer, 0, 8192)) != -1) {
                e.write(buffer, 0, readBytes1);
            }

            e.close();
            flag = true;
        } catch (FileNotFoundException var6) {
            var6.printStackTrace();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return flag;
    }

    public static void saveAsFileOutputStream(String physicalPath, String content) {
        File file = new File(physicalPath);
        boolean b = file.getParentFile().isDirectory();
        if(!b) {
            File foutput = new File(file.getParent());
            foutput.mkdirs();
        }

        FileOutputStream foutput1 = null;

        try {
            foutput1 = new FileOutputStream(physicalPath);
            foutput1.write(content.getBytes("UTF-8"));
        } catch (IOException var14) {
            var14.printStackTrace();
        } finally {
            try {
                foutput1.flush();
                foutput1.close();
            } catch (IOException var13) {
                var13.printStackTrace();
            }

        }

    }

    public static boolean createDirectory(String dir) {
        File f = new File(dir);
        if(!f.exists()) {
            f.mkdirs();
        }

        return true;
    }

    public boolean copyToFile(String srcFile, String desFile) {
        File scrfile = new File(srcFile);
        if(scrfile.isFile()) {
            FileInputStream fis = null;

            try {
                fis = new FileInputStream(scrfile);
            } catch (FileNotFoundException var13) {
                var13.printStackTrace();
            }

            File desfile = new File(desFile);
            boolean isDirectory = desfile.getParentFile().isDirectory();
            if(!isDirectory) {
                File fos = new File(desfile.getParent());
                fos.mkdirs();
            }

            FileOutputStream fos1 = null;

            try {
                fos1 = new FileOutputStream(desfile, false);
            } catch (FileNotFoundException var12) {
                var12.printStackTrace();
            }

            desfile = null;
            int length = (int)scrfile.length();
            byte[] b = new byte[length];

            try {
                fis.read(b);
                fis.close();
                fos1.write(b);
                fos1.close();
            } catch (IOException var11) {
                var11.printStackTrace();
            }

            scrfile = null;
            return true;
        } else {
            scrfile = null;
            return false;
        }
    }

    public static void copyFile(String srcFile, String targetFile) {
        File file = new File(targetFile);
        boolean isDirectory = file.getParentFile().isDirectory();
        if(!isDirectory) {
            File reader = new File(file.getParent());
            reader.mkdirs();
        }

        FileInputStream reader1 = null;
        FileOutputStream writer = null;

        try {
            byte[] e = new byte[4096];
            reader1 = new FileInputStream(srcFile);
            writer = new FileOutputStream(targetFile);

            int len;
            while((len = reader1.read(e)) > 0) {
                writer.write(e, 0, len);
            }
        } catch (IOException var16) {
            var16.printStackTrace();
        } finally {
            try {
                if(writer != null) {
                    writer.close();
                }

                if(reader1 != null) {
                    reader1.close();
                }
            } catch (IOException var15) {
                var15.printStackTrace();
            }

        }

    }

    public static void copyFile(File srcFile, File targetFile) {
        boolean isDirectory = targetFile.getParentFile().isDirectory();
        if(!isDirectory) {
            File reader = new File(targetFile.getParent());
            reader.mkdirs();
        }

        FileInputStream reader1 = null;
        FileOutputStream writer = null;

        try {
            byte[] e = new byte[4096];
            reader1 = new FileInputStream(srcFile);
            writer = new FileOutputStream(targetFile);

            int len;
            while((len = reader1.read(e)) > 0) {
                writer.write(e, 0, len);
            }
        } catch (IOException var15) {
            var15.printStackTrace();
        } finally {
            try {
                if(writer != null) {
                    writer.close();
                }

                if(reader1 != null) {
                    reader1.close();
                }
            } catch (IOException var14) {
                var14.printStackTrace();
            }

        }

    }

    public boolean copyDir(String sourceDir, String destDir) {
        File sourceFile = new File(sourceDir);
        File[] files = sourceFile.listFiles();

        for(int i = 0; i < files.length; ++i) {
            String fileName = files[i].getName();
            String tempSource = sourceDir + "/" + fileName;
            String tempDest = destDir + "/" + fileName;
            if(files[i].isFile()) {
                this.copyToFile(tempSource, tempDest);
            } else {
                this.copyDir(tempSource, tempDest);
            }
        }

        sourceFile = null;
        return true;
    }

    public boolean deleteDirectory(File dir) {
        File[] entries = dir.listFiles();
        int sz = entries.length;

        for(int i = 0; i < sz; ++i) {
            if(entries[i].isDirectory()) {
                if(!this.deleteDirectory(entries[i])) {
                    return false;
                }
            } else if(!entries[i].delete()) {
                return false;
            }
        }

        if(!dir.delete()) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkFileExist(String sFileName) {
        boolean result = false;

        try {
            File e = new File(sFileName);
            if(e.exists() && e.isFile()) {
                result = true;
            } else {
                result = false;
            }
        } catch (Exception var3) {
            result = false;
        }

        return result;
    }

    public static boolean checkDirectoryExist(String sDirectory) {
        boolean result = false;

        try {
            File e = new File(sDirectory);
            if(e.exists() && e.isDirectory()) {
                result = true;
            } else {
                result = false;
            }
        } catch (Exception var3) {
            result = false;
        }

        return result;
    }

    public static long getSize(String sFileName) {
        long lSize = 0L;

        try {
            File e = new File(sFileName);
            if(e.exists()) {
                if(e.isFile() && e.canRead()) {
                    lSize = e.length();
                } else {
                    lSize = -1L;
                }
            } else {
                lSize = 0L;
            }
        } catch (Exception var4) {
            lSize = -1L;
        }

        return lSize;
    }

    public static boolean deleteFromName(String sFileName) {
        boolean bReturn = true;

        try {
            File e = new File(sFileName);
            if(e.exists()) {
                boolean bResult = e.delete();
                if(!bResult) {
                    bReturn = false;
                }
            }
        } catch (Exception var4) {
            bReturn = false;
        }

        return bReturn;
    }

    public static void releaseZip(String sToPath, String sZipFile) throws Exception {
        if(null == sToPath || "".equals(sToPath.trim())) {
            File zfile = new File(sZipFile);
            sToPath = zfile.getParent();
        }

        ZipFile zfile1 = new ZipFile(sZipFile);
        Enumeration zList = zfile1.entries();
        ZipEntry ze = null;
        byte[] buf = new byte[1024];

        while(true) {
            do {
                if(!zList.hasMoreElements()) {
                    zfile1.close();
                    return;
                }

                ze = (ZipEntry)zList.nextElement();
            } while(ze.isDirectory());

            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(getRealFileName(sToPath, ze.getName())));
            BufferedInputStream is = new BufferedInputStream(zfile1.getInputStream(ze));
            boolean readLen = false;

            int readLen1;
            while((readLen1 = is.read(buf, 0, 1024)) != -1) {
                os.write(buf, 0, readLen1);
            }

            is.close();
            os.close();
        }
    }

    private static File getRealFileName(String baseDir, String absFileName) throws Exception {
        File ret = null;
        ArrayList dirs = new ArrayList();
        StringTokenizer st = new StringTokenizer(absFileName, System.getProperty("file.separator"));

        while(st.hasMoreTokens()) {
            dirs.add(st.nextToken());
        }

        ret = new File(baseDir);
        if(dirs.size() > 1) {
            for(int i = 0; i < dirs.size() - 1; ++i) {
                ret = new File(ret, (String)dirs.get(i));
            }
        }

        if(!ret.exists()) {
            ret.mkdirs();
        }

        ret = new File(ret, (String)dirs.get(dirs.size() - 1));
        return ret;
    }

    public static void renameFile(String srcFile, String targetFile) {
        copyFile(srcFile, targetFile);
        deleteFromName(srcFile);
    }
}
