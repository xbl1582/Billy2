package com.light.hexo.common.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: HttpClientUtil
 * @ProjectName hexo-boot
 * @Description: 客户端请求工具类
 */
public class HttpClientUtil { private HttpClientUtil() { }

    private static PoolingHttpClientConnectionManager cm;

    private static SSLConnectionSocketFactory sf;

    static {

        try {
            SSLContext sslc = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
            sf = new SSLConnectionSocketFactory(sslc);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Registry<ConnectionSocketFactory> sfr = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", null != sf ? sf : SSLConnectionSocketFactory.getSocketFactory())
                .build();

        cm = new PoolingHttpClientConnectionManager(sfr);
        cm.setMaxTotal(100);
        cm.setDefaultMaxPerRoute(20);

        // 启动该线程，负责清理无效连接
        new IdleConnectionEvictor(cm).start();
    }

    public static class IdleConnectionEvictor extends Thread {

        private final HttpClientConnectionManager connMgr;

        private volatile boolean shutdown;

        public IdleConnectionEvictor(HttpClientConnectionManager connMgr) {
            this.connMgr = connMgr;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    synchronized (this) {
                        wait(5000);
                        // 关闭失效的连接
                        System.out.println("===关闭失效的连接===");
                        connMgr.closeExpiredConnections();
                    }
                }
            } catch (InterruptedException ex) {
                // 结束
            }
        }
    }

    /**
     * 发送 GET 请求
     *
     * @param url
     * @return
     */
    public static String sendGet(String url) {
        // 创建 httpClient
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sf)
                .setConnectionManager(cm)
                .build();
        // 创建 http 请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");

        return sendRequest(httpclient,httpGet);
    }

    /**
     * 发送 POST 请求
     *
     * @param url
     * @return
     */
    public static String sendPost(String url, Map<String, Object> paramMap) {
        // 创建 httpClient
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sf)
                .setConnectionManager(cm)
                .build();
        // 创建 http 请求
        HttpPost httpPost = new HttpPost(url);

        // 设置参数
        if (paramMap != null && !paramMap.isEmpty()) {
            List<NameValuePair> formparams = new ArrayList<>();
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
            httpPost.setEntity(entity);
        }

        return sendRequest(httpclient,httpPost);
    }

    public static String sendPost(String url, String content) {
        // 创建 httpClient
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sf)
                .setConnectionManager(cm)
                .build();
        // 创建 http 请求
        HttpPost httpPost = new HttpPost(url);

        // 设置参数
        try {
            StringEntity entity = new StringEntity(content);
            httpPost.setEntity(entity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return sendRequest(httpclient,httpPost);
    }

    private static String sendRequest(CloseableHttpClient httpclient, HttpRequestBase request) {
        // 设置请求配置
        request.setConfig(getRequestConfig());

        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "UTF-8");
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private static RequestConfig getRequestConfig() {
        return RequestConfig.custom().setConnectTimeout(1000) // 创建连接的最长时间
                .setConnectionRequestTimeout(500) // 从连接池获取连接的最长时间
                .setSocketTimeout(10000) // 数据传输最长时间
                .build();
    }

}