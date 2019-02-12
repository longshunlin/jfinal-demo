//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lsl.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Proxy {
    private static final Logger LOG = LoggerFactory.getLogger(Proxy.class);
    public static final int DEFAULT_CONNECT_TIMEOUT = 15000;
    public static final int DEFAULT_SO_TIMEOUT = 30000;
    public static final int DEFAULT_BUFFER_SIZE = 256;
    public static final int DEFAULT_MAX_CONNECTIONS = 200;
    private static final String CS_PREFIX = "charset=";
    private static final int CS_PREFIX_LEN = "charset=".length();
    private final String encoding;
    private final HttpClient client;
    private final int bufferSize;

    public Proxy() {
        this("UTF-8", 15000, 30000, 256, 200);
    }

    public Proxy(String encoding) {
        this(encoding, 15000, 30000, 256, 200);
    }

    public Proxy(int bufferSize) {
        this("UTF-8", 15000, 30000, bufferSize, 200);
    }

    public Proxy(String encoding, int connectTimeout, int soTimeout) {
        this(encoding, connectTimeout, soTimeout, 256, 200);
    }

    public Proxy(String encoding, int connectTimeout, int soTimeout, int bufferSize) {
        this(encoding, connectTimeout, soTimeout, bufferSize, 200);
    }

    public Proxy(String encoding, int connectTimeout, int soTimeout, int bufferSize, int maxConnections) {
        this.encoding = encoding;
        HttpConnectionManagerParams mp = new HttpConnectionManagerParams();
        mp.setConnectionTimeout(connectTimeout);
        mp.setSoTimeout(soTimeout);
        mp.setStaleCheckingEnabled(true);
        mp.setTcpNoDelay(true);
        mp.setMaxTotalConnections(maxConnections);
        mp.setDefaultMaxConnectionsPerHost(10);
        MultiThreadedHttpConnectionManager mgr = new MultiThreadedHttpConnectionManager();
        mgr.setParams(mp);
        this.client = new HttpClient(mgr);
        HttpClientParams cparams = new HttpClientParams();
        cparams.setConnectionManagerTimeout((long)connectTimeout);
        this.client.setParams(cparams);
        this.bufferSize = bufferSize;
    }

    public HttpClient getClient() {
        return this.client;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public String post(String url, Map<String, String> headers, Map<String, String> data, String encoding) throws HttpException {
        PostMethod post = new PostMethod(url);
        this.setHeaders(post, headers);
        if(data != null) {
            Set postset = data.entrySet();
            NameValuePair[] params = new NameValuePair[postset.size()];
            int i = 0;

            Entry p;
            for(Iterator it = postset.iterator(); it.hasNext(); params[i++] = new NameValuePair((String)p.getKey(), (String)p.getValue())) {
                p = (Entry)it.next();
            }

            post.setRequestBody(params);
        }

        String var14;
        try {
            var14 = this.execute(post, encoding);
        } finally {
            post.releaseConnection();
        }

        return var14;
    }

    public String post(String url, Map<String, String> data, String encoding) throws HttpException {
        return this.post(url, (Map)null, (Map)data, encoding);
    }

    public String post(String url, Map<String, String> headers, String data, String encoding) throws HttpException {
        PostMethod post = new PostMethod(url);
        this.setHeaders(post, headers);
        if(data != null) {
            try {
                ByteArrayRequestEntity entity = new ByteArrayRequestEntity(data.getBytes(encoding));
                post.setRequestEntity(entity);
            } catch (UnsupportedEncodingException var11) {
                ;
            }
        }

        String entity1;
        try {
            entity1 = this.execute(post, encoding);
        } finally {
            post.releaseConnection();
        }

        return entity1;
    }

    public String post(String url, String data, String encoding) throws HttpException {
        return this.post(url, (Map)null, (String)data, encoding);
    }

    public String post(String url, String data) throws HttpException {
        return this.post(url, (Map)null, (String)data, this.encoding);
    }

    public String get(String url, Map<String, String> headers, Map<String, String> data) throws HttpException {
        StringBuffer sBuffer = new StringBuffer(url);
        String key;
        if(data != null && data.size() > 0) {
            if(url.indexOf("?") == -1) {
                sBuffer.append("?");
            }

            Iterator get = data.keySet().iterator();

            while(get.hasNext()) {
                key = (String)get.next();
                String vl = EncodeUtils.urlEncode((String)data.get(key));
                sBuffer.append(key).append("=").append(vl).append("&");
            }

            sBuffer.deleteCharAt(sBuffer.length() - 1);
        }

        GetMethod get1 = new GetMethod(sBuffer.toString());

        try {
            this.setHeaders(get1, headers);
            key = this.execute(get1, this.encoding);
        } finally {
            sBuffer.setLength(0);
            get1.releaseConnection();
        }

        return key;
    }

    public String get(String url, Map<String, String> headers) throws HttpException {
        return this.get(url, headers, (Map)null);
    }

    public String get(String url) throws HttpException {
        return this.get(url, (Map)null, (Map)null);
    }

    private final HttpMethod setHeaders(HttpMethod method, Map<String, String> headers) {
        if(headers != null) {
            Set headset = headers.entrySet();
            Iterator it = headset.iterator();

            while(it.hasNext()) {
                Entry header = (Entry)it.next();
                method.setRequestHeader((String)header.getKey(), (String)header.getValue());
            }
        }

        return method;
    }

    private String execute(HttpMethod method, String encoding) throws HttpException {
        InputStream in = null;
        BufferedReader reader = null;

        String sbuf;
        try {
            this.client.executeMethod(method);
            String e = encoding;
            Header ctypeh = method.getResponseHeader("Content-Type");
            String line;
            if(ctypeh != null) {
                sbuf = ctypeh.getValue();
                if(sbuf == null) {
                    line = null;
                } else {
                    line = sbuf.toLowerCase(Locale.ENGLISH);
                }

                int i;
                if(line != null && (i = line.indexOf("charset=")) != -1) {
                    e = line.substring(i + CS_PREFIX_LEN).trim();

                    try {
                        "a".getBytes(e);
                    } catch (UnsupportedEncodingException var24) {
                        e = encoding;
                    }
                }
            }

            if(e != null) {
                in = method.getResponseBodyAsStream();
                reader = new BufferedReader(new InputStreamReader(in, e), this.bufferSize);
                StringBuffer sbuf1 = new StringBuffer(this.bufferSize >>> 1);

                for(line = reader.readLine(); line != null; line = reader.readLine()) {
                    sbuf1.append(line).append("\r\n");
                }

                line = sbuf1.toString();
                return line;
            }

            sbuf = method.getResponseBodyAsString();
        } catch (IOException var25) {
            LOG.error("{}", var25);
            throw new HttpException(var25.getMessage());
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException var23) {
                    ;
                }
            }

            if(in != null) {
                try {
                    in.close();
                } catch (IOException var22) {
                    ;
                }
            }

        }

        return sbuf;
    }
}
