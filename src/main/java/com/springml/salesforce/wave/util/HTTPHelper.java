package com.springml.salesforce.wave.util;

import static com.springml.salesforce.wave.util.WaveAPIConstants.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

/**
 * Helper class for HTTP requests
 */
public class HTTPHelper {
    private static final Logger LOG = Logger.getLogger(HTTPHelper.class);

    public String post(URI uri, String sessionId, String request) throws Exception {
        return post(uri, sessionId, request, CONTENT_TYPE_APPLICATION_JSON, false);
    }

    public String post(URI uri, String sessionId, String request, boolean isBulk) throws Exception {
        return post(uri, sessionId, request, CONTENT_TYPE_APPLICATION_JSON, isBulk);
    }

    public String post(URI uri, String sessionId, String request, String contentType, boolean isBulk) throws Exception {
        LOG.info("Executing POST request on " + uri);
        LOG.debug("Sending request " + request);
        LOG.debug("Content-Type " + contentType);

        StringEntity entity = new StringEntity(request, STR_UTF_8);
        if (contentType != null) {
            entity.setContentType(contentType);
        } else {
            LOG.debug("As Content-Type is null " + CONTENT_TYPE_APPLICATION_JSON + " Content-Type is used");
            entity.setContentType(CONTENT_TYPE_APPLICATION_JSON);
        }

        HttpPost httpPost = new HttpPost(uri);
        httpPost.setEntity(entity);
        httpPost.setConfig(getRequestConfig());
        if (isBulk) {
            httpPost.addHeader(HEADER_X_SFDC_SESSION, sessionId);
        } else {
            httpPost.addHeader(HEADER_AUTH, HEADER_OAUTH + sessionId);
        }

        return execute(uri, httpPost);
    }

    public String get(boolean disableCompression, String urlString, String sessionId, Integer batchSize, boolean isBulk) throws Exception {
//        LOG.info("Executing GET request on " + urlString);
        HttpUrl httpUrl = HttpUrl.parse(urlString);
        assert httpUrl != null;
        Builder requestBuilder = new Builder()
            .method("GET", null)
            .url(httpUrl);
        if (!disableCompression) {
            // requestBuilder.addHeader("Accept-Encoding", "gzip");
        }
        if (isBulk) {
            requestBuilder.addHeader(HEADER_X_SFDC_SESSION, sessionId);
        } else {
            requestBuilder.addHeader(HEADER_AUTH, HEADER_OAUTH + sessionId);
            requestBuilder.addHeader(HEADER_ACCEPT, CONTENT_TYPE_APPLICATION_JSON);
        }
        if (batchSize != null && batchSize != 0) {
            requestBuilder.addHeader(HEADER_SF_QUERY_OPTIONS, HEADER_BATCH_SIZE + batchSize);
        }
        Request request = requestBuilder.build();
        LOG.info("request : " + request);
        return executeOkHttp3(request);
    }

    private String executeOkHttp3(Request request) throws IOException {
        LOG.info("Executing GET request on (executeOkHttp3) " + request.url());
        LOG.debug("Executing GET request on (executeOkHttp3) " + request.url());
        int timeout = Integer.parseInt(System.getProperty(SYS_PROPERTY_SOCKET_TIMEOUT, DEFAULT_CONNECTION_TIMEOUT));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        if (response.body() != null) {
            String responseBody = response.body().string();
            response.close();
            return responseBody;
        }
        LOG.debug("response : " + response);
        response.close();
        return "";
    }

    public String get(String url, String sessionId, Integer batchSize) throws Exception {
        return get(false, url, sessionId, batchSize, false);
    }

    public String get(boolean disableCompression, String url, String sessionId, Integer batchSize) throws Exception {
        return get(disableCompression, url, sessionId, batchSize, false);
    }

    public String get(String url, String sessionId, boolean isBulk) throws Exception {
        return get(false, url, sessionId, null, isBulk);
    }

    public String get(String url, String sessionId) throws Exception {
        return get(url, sessionId, null);
    }

    private String execute(URI uri, HttpUriRequest httpReq) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        InputStream eis = null;
        try {
            CloseableHttpResponse response = httpClient.execute(httpReq);

            int statusCode = response.getStatusLine().getStatusCode();
            if (!(statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_CREATED)) {
                String reasonPhrase = response.getStatusLine().getReasonPhrase();
                String errResponse = IOUtils.toString(response.getEntity().getContent(), STR_UTF_8);
                throw new Exception(
                        String.format("Accessing %s failed. Status %d. Reason %s \n Error from server %s", uri, statusCode, reasonPhrase, errResponse));
            }

            HttpEntity responseEntity = response.getEntity();
            LOG.info("entity content length: " + responseEntity.getContentLength());
            eis = responseEntity.getContent();
            return IOUtils.toString(eis, STR_UTF_8);
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                LOG.debug("Error while closing HTTP Client", e);
            }

            try {
                if (eis != null) {
                    eis.close();
                }
            } catch (Exception e) {
                LOG.debug("Error while closing InputStream", e);
            }
        }
    }

    public static RequestConfig getRequestConfig() {
        int timeout = Integer.parseInt(System.getProperty(SYS_PROPERTY_SOCKET_TIMEOUT, DEFAULT_CONNECTION_TIMEOUT));
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout)
                .setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).build();

        return requestConfig;
    }

    public static RequestConfig getRequestConfigCompressionDisabled() {
        int timeout = Integer.parseInt(System.getProperty(SYS_PROPERTY_SOCKET_TIMEOUT, DEFAULT_CONNECTION_TIMEOUT));
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout)
                .setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).setContentCompressionEnabled(false).build();

        return requestConfig;
    }
}
