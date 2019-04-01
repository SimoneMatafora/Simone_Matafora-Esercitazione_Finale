package it.tcgroup.vilear.coursemanager.service.impl;

import it.tcgroup.vilear.coursemanager.common.util.DateUtil;
import it.tcgroup.vilear.coursemanager.common.util.HttpUtil;
import it.tcgroup.vilear.coursemanager.controller.payload.request.UploadRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.UploadResponseV1;
import it.tcgroup.vilear.coursemanager.service.FilemanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Transactional
@Service
public class FilemanagerServiceImpl implements FilemanagerService {

    @Autowired
    private HttpUtil httpUtil;

    @Autowired
    private DateUtil dateUtil;

    @Value("${filemanager.api.endpoint}")
    private String endpointUrl;

    @Value("${filemanager.api.upload}")
    private String uploadApi;

/*
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

    private <T, P> T call(String url, HttpMethod method, P value, HashMap<String, String> headersparams, ParameterizedTypeReference<T> typeReference) {
        try {

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            for (String k : headersparams.keySet()) {
                headers.add(k, headersparams.get(k));
            }

            HttpEntity<P> entity = new HttpEntity<>(value, headers);

            return restTemplate.exchange(url, method, entity, typeReference).getBody();
        } catch (Exception e) {

            throw e;
        }
    }

    private <T, P> T callWithoutCert(String url, HttpMethod method, P value, HashMap<String, String> headersparams, ParameterizedTypeReference<T> typeReference) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;

        if (value != null) {
            jsonInString = mapper.writeValueAsString(value);
        }

        Response responseToken = httpUtil.callURL(headersparams, url, jsonInString, method);

        if (responseToken.code() != HttpStatus.OK.value() && responseToken.code() != HttpStatus.CREATED.value()) {
            throw new BadRequestException(responseToken.message() + " with code " + responseToken.code());
        }

        String response = responseToken.body().string();
        TypeReference tr = new CustomTypeReference(typeReference);
        return mapper.readValue(response, tr);
    }
*/
    @Override
    public UploadResponseV1 uploadFile(UploadRequestV1 uploadRequest) throws IOException {

        return httpUtil.callWithoutCert(endpointUrl + uploadApi, HttpMethod.POST, uploadRequest, null, new ParameterizedTypeReference<UploadResponseV1>() {
        });
    }


}
