//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ning.web.jotato.common.utils.http;

import com.alibaba.fastjson.JSON;
import com.ning.web.jotato.common.utils.FileItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final int DEFAULT_TIMEOUT = 30000;

    public HttpClientUtil() {
    }

    public static String doGet(String url) throws Exception {
        return doGet(url, (Map)null);
    }

    public static String doGet(String url, Map<String, Object> params) throws Exception {
        return doGet(url, (Map)null, params);
    }

    public static String doGet(String url, Map<String, Object> params, int connectTimeout, int readTimeout) throws Exception {
        return doGet(url, (Map)null, params, connectTimeout, readTimeout);
    }

    public static String doGet(String url, Map<String, Object> reqHeaders, Map<String, Object> params) throws Exception {
        return doGet(url, reqHeaders, (Map)null, params);
    }

    public static String doGet(String url, Map<String, Object> reqHeaders, Map<String, Object> params, int connectTimeout, int readTimeout) throws Exception {
        return doGet(url, reqHeaders, (Map)null, params, connectTimeout, readTimeout);
    }

    public static String doGet(String url, Map<String, Object> reqHeaders, Map<String, Object> respHeaders, Map<String, Object> params) throws Exception {
        return doGet(url, reqHeaders, respHeaders, params, 30000, 30000);
    }

    public static String doGet(String url, Map<String, Object> reqHeaders, Map<String, Object> respHeaders, Map<String, Object> params, int connectTimeout, int readTimeout) throws Exception {
        return doGet(url, reqHeaders, respHeaders, params, connectTimeout, readTimeout, "UTF-8", (String)null, (Integer)null);
    }

    public static String doGet(String url, Map<String, Object> reqHeaders, Map<String, Object> respHeaders, Map<String, Object> params, int connectTimeout, int readTimeout, String charset, String proxyHost, Integer proxyPort) throws Exception {
        URIBuilder builder = new URIBuilder();
        builder.setPath(url);
        builder.setParameters(params2nvps(params));
        builder.setCharset(Charset.forName(charset));
        HttpGet httpGet = new HttpGet(builder.build());
        return doRequest(url, reqHeaders, respHeaders, httpGet, connectTimeout, readTimeout, charset, proxyHost, proxyPort);
    }

    public static String doPost(String url) throws Exception {
        return doPost(url, (Map)null);
    }

    public static String doPost(String url, Map<String, Object> params) throws Exception {
        return doPost(url, (Map)null, params);
    }

    public static String doPost(String url, Map<String, Object> params, int connectTimeout, int readTimeout) throws Exception {
        return doPost(url, (Map)null, params, connectTimeout, readTimeout);
    }

    public static String doPost(String url, Map<String, Object> reqHeaders, Map<String, Object> params) throws Exception {
        return doPost(url, reqHeaders, (Map)null, params);
    }

    public static String doPost(String url, Map<String, Object> reqHeaders, Map<String, Object> params, int connectTimeout, int readTimeout) throws Exception {
        return doPost(url, reqHeaders, (Map)null, params, connectTimeout, readTimeout);
    }

    public static String doPost(String url, Map<String, Object> reqHeaders, Map<String, Object> respHeaders, Map<String, Object> params) throws Exception {
        return doPost(url, reqHeaders, respHeaders, params, 30000, 30000);
    }

    public static String doPost(String url, Map<String, Object> reqHeaders, Map<String, Object> respHeaders, Map<String, Object> params, int connectTimeout, int readTimeout) throws Exception {
        return doPost(url, reqHeaders, respHeaders, params, connectTimeout, readTimeout, "UTF-8", (String)null, (Integer)null);
    }

    public static String doPost(String url, Map<String, Object> reqHeaders, Map<String, Object> respHeaders, Map<String, Object> params, int connectTimeout, int readTimeout, String charset, String proxyHost, Integer proxyPort) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> pairs = params2nvps(params);
        if (pairs != null && pairs.size() > 0) {
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, charset));
        }

        return doRequest(url, reqHeaders, respHeaders, httpPost, connectTimeout, readTimeout, charset, proxyHost, proxyPort);
    }

    public static String doPostStr(String url, String data) throws Exception {
        return doPostStr(url, (Map)null, data);
    }

    public static String doPostStr(String url, String data, int connectTimeout, int readTimeout) throws Exception {
        return doPostStr(url, (Map)null, data, connectTimeout, readTimeout);
    }

    public static String doPostStr(String url, Map<String, Object> reqHeaders, String data) throws Exception {
        return doPostStr(url, reqHeaders, (Map)null, data);
    }

    public static String doPostStr(String url, Map<String, Object> reqHeaders, String data, int connectTimeout, int readTimeout) throws Exception {
        return doPostStr(url, reqHeaders, (Map)null, data, connectTimeout, readTimeout);
    }

    public static String doPostStr(String url, Map<String, Object> reqHeaders, Map<String, Object> respHeaders, String data) throws Exception {
        return doPostStr(url, reqHeaders, respHeaders, data, 30000, 30000);
    }

    public static String doPostStr(String url, Map<String, Object> reqHeaders, Map<String, Object> respHeaders, String data, int connectTimeout, int readTimeout) throws Exception {
        return doPostStr(url, reqHeaders, respHeaders, data, connectTimeout, readTimeout, "UTF-8", (String)null, (Integer)null);
    }

    public static String doPostStr(String url, Map<String, Object> reqHeaders, Map<String, Object> respHeaders, String data, int connectTimeout, int readTimeout, String charset, String proxyHost, Integer proxyPort) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(data, charset));
        return doRequest(url, reqHeaders, respHeaders, httpPost, connectTimeout, readTimeout, charset, proxyHost, proxyPort);
    }

    public static String doPostJson(String url, Map<String, Object> params) throws Exception {
        return doPostJson(url, (Map)null, params);
    }

    public static String doPostJson(String url, Map<String, Object> params, int connectTimeout, int readTimeout) throws Exception {
        return doPostJson(url, (Map)null, params, connectTimeout, readTimeout);
    }

    public static String doPostJson(String url, Map<String, Object> reqHeaders, Map<String, Object> params) throws Exception {
        return doPostJson(url, reqHeaders, (Map)null, params);
    }

    public static String doPostJson(String url, Map<String, Object> reqHeaders, Map<String, Object> params, int connectTimeout, int readTimeout) throws Exception {
        return doPostJson(url, reqHeaders, (Map)null, params, connectTimeout, readTimeout);
    }

    public static String doPostJson(String url, Map<String, Object> reqHeaders, Map<String, Object> respHeaders, Map<String, Object> params) throws Exception {
        return doPostJson(url, reqHeaders, respHeaders, params, 30000, 30000);
    }

    public static String doPostJson(String url, Map<String, Object> reqHeaders, Map<String, Object> respHeaders, Map<String, Object> params, int connectTimeout, int readTimeout) throws Exception {
        return doPostJson(url, reqHeaders, respHeaders, params, connectTimeout, readTimeout, "UTF-8", (String)null, (Integer)null);
    }

    public static String doPostJson(String url, Map<String, Object> reqHeaders, Map<String, Object> respHeaders, Map<String, Object> params, int connectTimeout, int readTimeout, String charset, String proxyHost, Integer proxyPort) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json; charset=" + charset);
        httpPost.setEntity(new StringEntity(JSON.toJSONString(params), charset));
        return doRequest(url, reqHeaders, respHeaders, httpPost, connectTimeout, readTimeout, charset, proxyHost, proxyPort);
    }

    public static String doPostFile(String url, Map<String, FileItem> fileparams) throws Exception {
        return doPostFile(url, (Map)null, fileparams);
    }

    public static String doPostFile(String url, Map<String, Object> params, Map<String, FileItem> fileparams) throws Exception {
        return doPostFile(url, (Map)null, params, fileparams);
    }

    public static String doPostFile(String url, Map<String, Object> reqHeaders, Map<String, Object> params, Map<String, FileItem> fileparams) throws Exception {
        return doPostFile(url, reqHeaders, (Map)null, params, fileparams);
    }

    public static String doPostFile(String url, Map<String, Object> reqHeaders, Map<String, Object> params, Map<String, FileItem> fileparams, int connectTimeout, int readTimeout) throws Exception {
        return doPostFile(url, reqHeaders, (Map)null, params, fileparams, connectTimeout, readTimeout);
    }

    public static String doPostFile(String url, Map<String, Object> reqHeaders, Map<String, Object> respHeaders, Map<String, Object> params, Map<String, FileItem> fileparams) throws Exception {
        return doPostFile(url, reqHeaders, respHeaders, params, fileparams, 30000, 30000);
    }

    public static String doPostFile(String url, Map<String, Object> reqHeaders, Map<String, Object> respHeaders, Map<String, Object> params, Map<String, FileItem> fileparams, int connectTimeout, int readTimeout) throws Exception {
        return doPostFile(url, reqHeaders, respHeaders, params, fileparams, connectTimeout, readTimeout, "UTF-8", (String)null, (Integer)null);
    }

    public static String doPostFile(String url, Map<String, Object> reqHeaders, Map<String, Object> respHeaders, Map<String, Object> params, Map<String, FileItem> fileparams, int connectTimeout, int readTimeout, String charset, String proxyHost, Integer proxyPort) throws Exception {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(Charset.forName(charset));
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        Iterator var11;
        Map.Entry entry;
        if (params != null && !params.isEmpty()) {
            var11 = params.entrySet().iterator();

            while(var11.hasNext()) {
                entry = (Map.Entry)var11.next();
                builder.addTextBody((String)entry.getKey(), String.valueOf(entry.getValue()), ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), charset));
            }
        }

        if (fileparams != null && !fileparams.isEmpty()) {
            var11 = fileparams.entrySet().iterator();

            while(var11.hasNext()) {
                entry = (Map.Entry)var11.next();
                FileItem fileItem = (FileItem)entry.getValue();
                ContentType contentType = ContentType.create(fileItem.getMimeType());
                builder.addBinaryBody((String)entry.getKey(), fileItem.getContent(), contentType, fileItem.getFileName());
            }
        }

        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(builder.build());
        return doRequest(url, reqHeaders, respHeaders, httpPost, connectTimeout, readTimeout, charset, proxyHost, proxyPort);
    }

    private static String doRequest(String url, Map<String, Object> reqHeaders, Map<String, Object> respHeaders, HttpRequestBase request, int connectTimeout, int readTimeout, String charset, String proxyHost, Integer proxyPort) throws Exception {
        HttpHost proxy = null;
        if (StringUtils.isNotEmpty(proxyHost)) {
            proxy = new HttpHost(proxyHost, proxyPort);
        }

        return doRequest(url, reqHeaders, respHeaders, request, connectTimeout, readTimeout, charset, proxy);
    }

    private static String doRequest(String url, Map<String, Object> reqHeaders, Map<String, Object> respHeaders, HttpRequestBase request, int connectTimeout, int readTimeout, String charset, HttpHost proxy) throws Exception {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;

        String var22;
        try {
            RequestConfig.Builder configBuilder = RequestConfig.custom();
            configBuilder.setConnectionRequestTimeout(connectTimeout);
            configBuilder.setConnectTimeout(connectTimeout);
            configBuilder.setSocketTimeout(readTimeout);
            if (proxy != null) {
                configBuilder.setProxy(proxy);
            }

            request.setConfig(configBuilder.build());
            if (reqHeaders != null) {
                Iterator var11 = reqHeaders.entrySet().iterator();

                while(var11.hasNext()) {
                    Map.Entry<String, Object> header = (Map.Entry)var11.next();
                    Object v = header.getValue();
                    if (v != null) {
                        request.setHeader((String)header.getKey(), v.toString());
                    }
                }
            }

            httpClient = getHttpClient(url);
            httpResponse = httpClient.execute(request);
            if (respHeaders != null && httpResponse != null) {
                Header[] headers = httpResponse.getAllHeaders();
                Header[] var21 = headers;
                int var23 = headers.length;

                for(int var14 = 0; var14 < var23; ++var14) {
                    Header header = var21[var14];
                    respHeaders.put(header.getName(), header.getValue());
                }
            }

            HttpEntity entity = httpResponse.getEntity();
            var22 = EntityUtils.toString(entity, charset);
        } finally {
            if (httpResponse != null) {
                HttpClientUtils.closeQuietly(httpResponse);
            }

            if (httpClient != null) {
                HttpClientUtils.closeQuietly(httpClient);
            }

        }

        return var22;
    }

    private static List<NameValuePair> params2nvps(Map<String, Object> params) {
        List<NameValuePair> pairList = new ArrayList();
        if (params != null) {
            Iterator var2 = params.entrySet().iterator();

            while(var2.hasNext()) {
                Map.Entry<String, Object> param = (Map.Entry)var2.next();
                Object v = param.getValue();
                if (v != null) {
                    pairList.add(new BasicNameValuePair((String)param.getKey(), v.toString()));
                }
            }
        }

        return pairList;
    }

    private static CloseableHttpClient getHttpClient(String url) throws Exception {
        HttpClientBuilder httpClientBuilder = HttpClients.custom().disableAutomaticRetries();
        boolean isSSL = url.startsWith("https");
        if (isSSL) {
            SSLContextBuilder contextBuilder = (new SSLContextBuilder()).loadTrustMaterial((KeyStore)null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            });
            SSLConnectionSocketFactory sslf = new SSLConnectionSocketFactory(contextBuilder.build(), new HostnameVerifier() {
                @Override
                public boolean verify(String host, SSLSession sslSession) {
                    return true;
                }
            });
            httpClientBuilder.setSSLSocketFactory(sslf);
        }

        return httpClientBuilder.build();
    }
}
