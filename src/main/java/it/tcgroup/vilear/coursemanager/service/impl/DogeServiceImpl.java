package it.tcgroup.vilear.coursemanager.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.tcgroup.vilear.coursemanager.common.exception.BadRequestException;
import it.tcgroup.vilear.coursemanager.common.util.HttpUtil;
import it.tcgroup.vilear.coursemanager.controller.payload.request.DogeRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.DogeResponseV1;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.dto.LearnerDto;
import it.tcgroup.vilear.coursemanager.service.DogeService;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class DogeServiceImpl implements DogeService {
    @Autowired
    private HttpUtil httpUtil;

    @Value("${doge.api.endpoint}")
    private String dogeAPIEndpoint;

    @Value("${doge.api.enqueue}")
    private String dogeAPIEnqueue;


    private static Logger LOGGER = LoggerFactory.getLogger(DogeServiceImpl.class);


    private <T, P> T call(String url, HttpMethod method, P value, HashMap<String, String> headersparams, Class<T> outclass) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        for (String k : headersparams.keySet()) {
            headers.add(k, headersparams.get(k));
        }

        HttpEntity entity = new HttpEntity(value, headers);

        return restTemplate.exchange(url, method, entity, outclass).getBody();

    }


    private <T, P> T callWithoutCert(String url, HttpMethod method, P value, HashMap<String, String> headersparams, Class<T> outclass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;

        if (value != null) {
            jsonInString = mapper.writeValueAsString(value);
        }

        Response responseToken = httpUtil.callURL(headersparams, url, jsonInString, method);

        if (responseToken.code() != HttpStatus.OK.value() && responseToken.code() != HttpStatus.CREATED.value() ) {
            LOGGER.info(responseToken.message() + " with code " + responseToken.code());
            throw new BadRequestException(responseToken.message() + " with code " + responseToken.code());
        }
        String response = responseToken.body().string();

        return mapper.readValue(response, outclass);
    }



    @Override
    public DogeResponseV1 learnerCertificate(CourseEntity course, LearnerDto learner) throws Exception
    {
        DogeRequestV1 dogeRequestV1 = new DogeRequestV1();
        Map<String, Object> requestMap = new HashMap<>();

        requestMap.put("name_and_surname", learner.getSurname()+" "+learner.getName());
        requestMap.put("city", learner.getBirthPlace());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String birthday = simpleDateFormat.format(learner.getDateOfBirth());
        requestMap.put("birthday", birthday);
        requestMap.put("fiscal_code", learner.getFiscalCode());
        String courseName = "Corso "+course.getCourseType().getValue().toLowerCase();
        requestMap.put("project_name", courseName);
        requestMap.put("project_code", course.getCourseCode());
        requestMap.put("project_description", course.getCourseTitle());
        String start_date = simpleDateFormat.format(course.getCourseStartDate());
        requestMap.put("date", start_date);
        requestMap.put("hours", course.getTotalHours().toString());
        String date = simpleDateFormat.format(new Date());
        requestMap.put("date_done", date);


        dogeRequestV1.setData(requestMap);
        dogeRequestV1.setFilename("Attestato_"+learner.getSurname()+"_"+learner.getName()+".pdf");
        dogeRequestV1.setTemplate("Attestato");
        DogeRequestV1.FileManager fileManager = new DogeRequestV1.FileManager();
        String uuid = UUID.randomUUID().toString();
        fileManager.setUuid(uuid);
        fileManager.setResourceId("1");
        fileManager.setResourceType("doge");
        fileManager.setBlobType("pdf");
        dogeRequestV1.setFileManager(fileManager);
        DogeResponseV1.ActionResult response = callWithoutCert(dogeAPIEndpoint + dogeAPIEnqueue, HttpMethod.POST, dogeRequestV1, new HashMap<>(), DogeResponseV1.ActionResult.class);
        if(response.getCode() == 1){
            response = new DogeResponseV1.ActionResult(response.getCode(), response.getMessage(), response.getDetails());
        }
        DogeResponseV1 dogeResponse = new DogeResponseV1();
        dogeResponse.setDocumentId(uuid);
        dogeResponse.setActionResult(response);
        return dogeResponse;
    }

    @Override
    public DogeResponseV1 enqueueClaimsWithoutCai(UUID idCourse) throws Exception {
        return null;
    }

    @Override
    public DogeResponseV1 enqueueClaimsWithoutCounterpart(UUID idCourse) throws Exception {
        return null;
    }
}
