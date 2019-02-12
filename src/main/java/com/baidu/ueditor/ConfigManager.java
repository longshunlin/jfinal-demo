package com.baidu.ueditor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public final class ConfigManager {
    private final String rootPath;
    private final String originalPath;
    private final String contextPath;
    private static final String configFileName = "config.json";
    private String parentPath = null;
    private JSONObject jsonConfig = null;
    private static final String SCRAWL_FILE_NAME = "scrawl";
    private static final String REMOTE_FILE_NAME = "remote";

    private ConfigManager(String rootPath, String contextPath, String uri)
            throws FileNotFoundException, IOException {
        rootPath = rootPath.replace("\\", "/");

        this.rootPath = rootPath;
        this.contextPath = contextPath;
        if (contextPath.length() > 0) {
            originalPath = (this.rootPath + uri.substring(contextPath.length()));
        } else {
            originalPath = (this.rootPath + uri);
        }
        initEnv();
    }

    public static ConfigManager getInstance(String rootPath, String contextPath, String uri) {
        try {
            return new ConfigManager(rootPath, contextPath, uri);
        } catch (Exception e) {
        }
        return null;
    }

    public boolean valid() {
        return jsonConfig != null;
    }

    public JSONObject getAllConfig() {
        return jsonConfig;
    }

    public Map<String, Object> getConfig(int type) {
        Map<String, Object> conf = new HashMap();
        String savePath = null;
        switch (type) {
            case 4:
                conf.put("isBase64", "false");
                conf.put("maxSize", Long.valueOf(jsonConfig.getLong("fileMaxSize")));
                conf.put("allowFiles", getArray("fileAllowFiles"));
                conf.put("fieldName", jsonConfig.getString("fileFieldName"));
                savePath = jsonConfig.getString("filePathFormat");
                break;
            case 1:
                conf.put("isBase64", "false");
                conf.put("maxSize", Long.valueOf(jsonConfig.getLong("imageMaxSize")));
                conf.put("allowFiles", getArray("imageAllowFiles"));
                conf.put("fieldName", jsonConfig.getString("imageFieldName"));
                savePath = jsonConfig.getString("imagePathFormat");
                break;
            case 3:
                conf.put("maxSize", Long.valueOf(jsonConfig.getLong("videoMaxSize")));
                conf.put("allowFiles", getArray("videoAllowFiles"));
                conf.put("fieldName", jsonConfig.getString("videoFieldName"));
                savePath = jsonConfig.getString("videoPathFormat");
                break;
            case 2:
                conf.put("filename", "scrawl");
                conf.put("maxSize", Long.valueOf(jsonConfig.getLong("scrawlMaxSize")));
                conf.put("fieldName", jsonConfig.getString("scrawlFieldName"));
                conf.put("isBase64", "true");
                savePath = jsonConfig.getString("scrawlPathFormat");
                break;
            case 5:
                conf.put("filename", "remote");
                conf.put("filter", getArray("catcherLocalDomain"));
                conf.put("maxSize", Long.valueOf(jsonConfig.getLong("catcherMaxSize")));
                conf.put("allowFiles", getArray("catcherAllowFiles"));
                conf.put("fieldName", jsonConfig.getString("catcherFieldName") + "[]");
                savePath = jsonConfig.getString("catcherPathFormat");
                break;
            case 7:
                conf.put("allowFiles", getArray("imageManagerAllowFiles"));
                conf.put("dir", jsonConfig.getString("imageManagerListPath"));
                conf.put("count", jsonConfig.getInteger("imageManagerListSize"));
                break;
            case 6:
                conf.put("allowFiles", getArray("fileManagerAllowFiles"));
                conf.put("dir", jsonConfig.getString("fileManagerListPath"));
                conf.put("count", jsonConfig.getInteger("fileManagerListSize"));
        }
        conf.put("savePath", savePath);
        conf.put("rootPath", rootPath);

        return conf;
    }

    private void initEnv() throws FileNotFoundException, IOException {
        File file = new File(originalPath);
        if (!file.isAbsolute()) {
            file = new File(file.getAbsolutePath());
        }
        parentPath = file.getParent();

        String configContent = readFile(getConfigPath());
        try {
            JSONObject jsonConfig = JSONObject.parseObject(configContent);
            this.jsonConfig = jsonConfig;
        } catch (Exception e) {
            this.jsonConfig = null;
        }
    }

    private String getConfigPath() {
        return parentPath + File.separator + "config.json";
    }

    private String[] getArray(String key) {
        JSONArray jsonArray = jsonConfig.getJSONArray(key);
        String[] result = new String[jsonArray.size()];

        int i = 0;
        for (int len = jsonArray.size(); i < len; i++) {
            result[i] = jsonArray.getString(i);
        }
        return result;
    }

    private String readFile(String path)
            throws IOException {
        StringBuilder builder = new StringBuilder();
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
            BufferedReader bfReader = new BufferedReader(reader);

            String tmpContent = null;
            while ((tmpContent = bfReader.readLine()) != null) {
                builder.append(tmpContent);
            }
            bfReader.close();
        } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
        }
        return filter(builder.toString());
    }

    private String filter(String input) {
        return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");
    }
}
