package it.tcgroup.vilear.coursemanager.common.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.tcgroup.vilear.coursemanager.common.exception.*;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.cert.CertificateException;
import java.util.HashMap;
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

    private static class ExceptionResponse{

        @JsonProperty("timestamp")
        String timestamp;

        @JsonProperty("status")
        Integer status;

        @JsonProperty("error")
        String error;

        @JsonProperty("message")
        String message;

        @JsonProperty("path")
        String path;

        public ExceptionResponse() {
        }

        public ExceptionResponse(String timestamp, Integer status, String error, String message, String path) {
            this.timestamp = timestamp;
            this.status = status;
            this.error = error;
            this.message = message;
            this.path = path;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        @Override
        public String toString() {
            return "ExceptionResponse{" +
                    "timestamp='" + timestamp + '\'' +
                    ", status=" + status +
                    ", error='" + error + '\'' +
                    ", message='" + message + '\'' +
                    ", path='" + path + '\'' +
                    '}';
        }
    }

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
            //LOGGER.info("Body Request: {}", jsonBody);
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

    public class CustomTypeReference extends TypeReference<Object> {
        private final Type type;

        public CustomTypeReference(ParameterizedTypeReference pt) {
            this.type = pt.getType();
        }

        @Override
        public Type getType() {
            return type;
        }
    }

    public  <T, P> T call(String url, HttpMethod method, P value, HashMap<String, String> headersparams, ParameterizedTypeReference<T> typeReference) {
        try {

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
            for (String k : headersparams.keySet()) {
                headers.add(k, headersparams.get(k));
            }

            HttpEntity<P> entity = new HttpEntity<>(value, headers);

            return restTemplate.exchange(url, method, entity, typeReference).getBody();
        } catch (Exception e) {

            throw e;
        }
    }

    public <T, P> T callWithoutCert(String url, HttpMethod method, P value, HashMap<String, String> headersparams, ParameterizedTypeReference<T> typeReference) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;

        if (value != null) {
            jsonInString = mapper.writeValueAsString(value);
        }

        Response responseToken = this.callURL(headersparams, url, jsonInString, method);
        ExceptionResponse responseException = null;

        if(!responseToken.isSuccessful() && responseToken.body() != null){

            responseException = mapper.readValue(responseToken.body().string(),ExceptionResponse.class);

            if(responseToken.code() == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                throw new InternalError(responseException.getMessage());
            }else if(responseToken.code() == HttpStatus.FORBIDDEN.value()){
                throw new ForbiddenExcemption( responseException.getMessage());
            }else if(responseToken.code() == HttpStatus.NOT_FOUND.value()){
                throw new NotFoundException(responseException.getMessage());
            }else if(responseToken.code() == HttpStatus.NOT_ACCEPTABLE.value()){
                throw new BadParametersException(responseException.getMessage());
            }else if(responseToken.code() == HttpStatus.BAD_REQUEST.value() || (responseToken.code() != HttpStatus.OK.value() && responseToken.code() != HttpStatus.CREATED.value())){
                throw new BadRequestException(responseException.getMessage());
            }
        }

        if(typeReference != null){

            String response = responseToken.body().string();
            TypeReference tr = new CustomTypeReference(typeReference);
            return mapper.readValue(response, tr);
        }
        return null;
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
