package it.tcgroup.vilear.coursemanager.common.util;

import it.tcgroup.vilear.coursemanager.common.exception.InternalException;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Component
public class HttpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    private static final MediaType JSON = MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE);

    @Value("${cert.validation.enable:false}")
    private boolean certificateValidationEnable;

    @Value("${http.timeout.connect:30}")
    private int connectionTimeout;

    @Value("${http.timeout.read:30}")
    private int readTimeout;

    @Value("${http.timeout.write:10}")
    private int writeTimeout;

    public Response callURL(Map<String, String> headers, String url, String jsonBody, HttpMethod method) throws IOException {

        if (StringUtils.isBlank(url)) {
            throw new InternalException("Empty URL input source. No URL to call");
        }
        if (method == null) {
            throw new InternalException("HTTP Method is required");
        }

        OkHttpClient.Builder clientBuilder = certificateValidationEnable
                ? new OkHttpClient.Builder()
                : getUnsafeOkHttpClientBuilder();

        OkHttpClient client = clientBuilder
                .connectTimeout(connectionTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .build();

        Request.Builder builder = new Request.Builder();

        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                if (header.getValue() != null) {
                    builder = builder.addHeader(header.getKey(), header.getValue());
                } else {
                    LOGGER.warn("SKIPPED Header '{}' because it has no value associated", header.getKey());
                }
            }
        }

        builder = builder.url(url);

        RequestBody body = RequestBody.create(JSON, "");
        if (StringUtils.isNotBlank(jsonBody)) {
            LOGGER.info("Body Request: {}", jsonBody);
            body = RequestBody.create(JSON, jsonBody);
        }

        switch (method) {
            case POST:
                builder = builder.post(body);
                break;
            case PUT:
                builder = builder.put(body);
                break;
            case DELETE:
                builder = builder.delete(body);
                break;
            default:
                break;
        }

        Request request = builder.build();

        try {
            LOGGER.info("Request URL: {}", url);
            Response response = client.newCall(request).execute();
            LOGGER.info("Response Code: {}", response.code());
            return response;
        } catch (Exception e) {
            LOGGER.error("Error request URL: '{}'. '{}'", url, e.getMessage(), e);
            throw e;
        }
    }

    public static OkHttpClient.Builder getUnsafeOkHttpClientBuilder() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            return builder;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
