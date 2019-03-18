package it.tcgroup.vilear.coursemanager.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.tcgroup.vilear.coursemanager.common.exception.BadRequestException;
import it.tcgroup.vilear.coursemanager.common.exception.NotFoundException;
import it.tcgroup.vilear.coursemanager.common.util.HttpUtil;
import it.tcgroup.vilear.coursemanager.controller.payload.request.DogeRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.DogeResponseV1;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.dto.LearnerDto;
import it.tcgroup.vilear.coursemanager.entity.dto.PartnerDto;
import it.tcgroup.vilear.coursemanager.entity.enumerated.SupplyServicePartnerCourseEnum;
import it.tcgroup.vilear.coursemanager.entity.enumerated.TypeAddressPartnerEnum;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.PartnerCourse;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.PlacementCourse;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.TeacherCourse;
import it.tcgroup.vilear.coursemanager.entity.jsonb.partner.AddressPartner;
import it.tcgroup.vilear.coursemanager.repository.CourseRepository;
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
import java.util.*;

@Service
public class DogeServiceImpl implements DogeService {
    @Autowired
    private HttpUtil httpUtil;

    @Value("${doge.api.endpoint}")
    private String dogeAPIEndpoint;

    @Value("${doge.api.enqueue}")
    private String dogeAPIEnqueue;

    @Autowired
    private CourseRepository courseRepository;

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
    public DogeResponseV1 inailComunication(UUID idCourse) throws Exception {
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(idCourse);
        if(!courseEntityOptional.isPresent()) throw new NotFoundException("Course with id "+idCourse+" not found");
        CourseEntity courseEntity = courseEntityOptional.get();
        DogeRequestV1 dogeRequestV1 = new DogeRequestV1();
        Map<String, Object> requestMap = new HashMap<>();

        //Codice corso
        requestMap.put("project_number", courseEntity.getCourseCode());

        //Date del corso
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String startDate = simpleDateFormat.format(courseEntity.getCourseStartDate());
        requestMap.put("from", startDate);
        String endDate = simpleDateFormat.format(courseEntity.getCourseEndDate());
        requestMap.put("to", endDate);

        //Orari del corso
        requestMap.put("hour_1", courseEntity.getMorningStartHour().toString());
        requestMap.put("hour_2", courseEntity.getMorningEndHour().toString());
        requestMap.put("hour_3", courseEntity.getAfternoonStatrHour().toString());
        requestMap.put("hour_4", courseEntity.getAfternoonEndHour().toString());

        //Indirizzo sede del corso
        if(courseEntity.getPartnerList() != null){
            for(PartnerCourse partnerCourse : courseEntity.getPartnerList())
            {
                if(partnerCourse.getSupplierService() != null){
                    for (SupplyServicePartnerCourseEnum supplyServicePartnerCourseEnum : partnerCourse.getSupplierService()){
                        if(supplyServicePartnerCourseEnum.equals(SupplyServicePartnerCourseEnum.AULA)){
                            if(partnerCourse.getSupplier() != null) {
                                String addressPartner = partnerCourse.getSupplier().getBusinessName();
                                if(partnerCourse.getSupplier().getAddressList() != null)
                                {
                                    for(AddressPartner addressPartner1 : partnerCourse.getSupplier().getAddressList()){
                                        if(addressPartner1.getType().equals(TypeAddressPartnerEnum.OPERATIVO)){
                                            addressPartner+=", "+addressPartner1.getAddress().getFormattedAddress();
                                            requestMap.put("where", addressPartner);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        String date = simpleDateFormat.format(new Date());
        requestMap.put("date", date);

        String object = "Assicurazione per corso di formazione N°"+courseEntity.getCourseCode();
        requestMap.put("object", object);

        //Elenco dei discenti
        if(courseEntity.getPlacementList() != null){
            int i = 1;
            for(PlacementCourse placementCourse : courseEntity.getPlacementList())
            {
                LearnerDto learnerDto = placementCourse.getLearner();
                if(learnerDto != null)
                {
                    String surname = "surname_"+i;
                    String name = "name_"+i;
                    String fiscalCode = "fiscal_code_"+i;
                    requestMap.put(surname, learnerDto.getSurname());
                    requestMap.put(name, learnerDto.getName());
                    requestMap.put(fiscalCode, learnerDto.getFiscalCode());
                    i++;
                }
            }
        }

        dogeRequestV1.setData(requestMap);
        dogeRequestV1.setFilename("Comunicazione-Inail"+courseEntity.getCourseCode()+".pdf");
        dogeRequestV1.setTemplate("ComunicazioneINAIL");
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
    public DogeResponseV1 ticketDelivery(UUID idCourse) throws Exception {
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(idCourse);
        if(!courseEntityOptional.isPresent()) throw new NotFoundException("Course with id "+idCourse+" not found");
        CourseEntity courseEntity = courseEntityOptional.get();
        DogeRequestV1 dogeRequestV1 = new DogeRequestV1();
        Map<String, Object> requestMap = new HashMap<>();

        //Codice corso
        requestMap.put("project_code", courseEntity.getCourseCode());

        //Date del corso
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String startDate = simpleDateFormat.format(courseEntity.getCourseStartDate());
        requestMap.put("from", startDate);
        String endDate = simpleDateFormat.format(courseEntity.getCourseEndDate());
        requestMap.put("to", endDate);

        //Elenco dei discenti
        if(courseEntity.getPlacementList() != null){
            int i = 1;
            for(PlacementCourse placementCourse : courseEntity.getPlacementList())
            {
                LearnerDto learnerDto = placementCourse.getLearner();
                if(learnerDto != null)
                {
                    String surname = "surname_"+i;
                    String name = "name_"+i;
                    requestMap.put(surname, learnerDto.getSurname());
                    requestMap.put(name, learnerDto.getName());
                    i++;
                }
            }
        }
        dogeRequestV1.setData(requestMap);
        dogeRequestV1.setFilename("Consegna-ticket-2260-"+courseEntity.getCourseCode()+".pdf");
        dogeRequestV1.setTemplate("ConsegnaTicket");
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
    public DogeResponseV1 learnesEvaluation(CourseEntity courseEntity, TeacherCourse teacherCourse) throws Exception{
        if(teacherCourse == null) throw new BadRequestException("Teacher not found");
            DogeRequestV1 dogeRequestV1 = new DogeRequestV1();
            Map<String, Object> requestMap = new HashMap<>();
            List<Map<String, String>> request = new ArrayList<>();
            requestMap.put("project_code", courseEntity.getCourseCode());
            if (teacherCourse.getTeacher() != null) {
                String teacher = teacherCourse.getTeacher().getSurname() + " " + teacherCourse.getTeacher().getName();
                requestMap.put("docente", teacher);
            }
            if (courseEntity.getPlacementList() != null) {
                for (PlacementCourse placementCourse : courseEntity.getPlacementList()) {
                    LearnerDto learnerDto = placementCourse.getLearner();
                    if (learnerDto != null) {
                        Map<String, String> requestInnerMap = new HashMap<>();
                        requestInnerMap.put("surname", learnerDto.getSurname());
                        requestInnerMap.put("name", learnerDto.getName());
                        request.add(requestInnerMap);
                    }

                }
            }
            requestMap.put("partecipanti", request);

            dogeRequestV1.setData(requestMap);
            dogeRequestV1.setFilename("Valutazione-discenti-" + courseEntity.getCourseCode() + ".pdf");
            dogeRequestV1.setTemplate("ValutazioneDiscenti");
            DogeRequestV1.FileManager fileManager = new DogeRequestV1.FileManager();
            String uuid = UUID.randomUUID().toString();
            fileManager.setUuid(uuid);
            fileManager.setResourceId("1");
            fileManager.setResourceType("doge");
            fileManager.setBlobType("xlsx");
            dogeRequestV1.setFileManager(fileManager);
            DogeResponseV1.ActionResult response = callWithoutCert(dogeAPIEndpoint + dogeAPIEnqueue, HttpMethod.POST, dogeRequestV1, new HashMap<>(), DogeResponseV1.ActionResult.class);
            if (response.getCode() == 1) {
                response = new DogeResponseV1.ActionResult(response.getCode(), response.getMessage(), response.getDetails());
            }
            DogeResponseV1 dogeResponse = new DogeResponseV1();
            dogeResponse.setDocumentId(uuid);
            dogeResponse.setActionResult(response);

        return dogeResponse;
    }

    @Override
    public DogeResponseV1 teacherEmployee(CourseEntity courseEntity, TeacherCourse teacherCourse) throws Exception{
        if(teacherCourse == null) throw new BadRequestException("Teacher not found");
        DogeRequestV1 dogeRequestV1 = new DogeRequestV1();
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("project_code", courseEntity.getCourseCode());
        requestMap.put("course_title", courseEntity.getCourseTitle());
        if(courseEntity.getTotalHours() != null) {
            requestMap.put("tot_hours", courseEntity.getTotalHours().toString());
            requestMap.put("total_hours", courseEntity.getTotalHours().toString());
        }

        if (teacherCourse.getTeacher() != null) {
            String teacher = teacherCourse.getTeacher().getSurname() + " " + teacherCourse.getTeacher().getName();
            requestMap.put("name_and_surname", teacher);
            requestMap.put("name_and_surname_2", teacher);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        requestMap.put("city_and_date", simpleDateFormat.format(new Date()));

        requestMap.put("start_date", simpleDateFormat.format(courseEntity.getCourseStartDate()));
        requestMap.put("end_date", simpleDateFormat.format(courseEntity.getCourseEndDate()));

        //Indirizzo sede del corso
        if(courseEntity.getPartnerList() != null){
            for(PartnerCourse partnerCourse : courseEntity.getPartnerList())
            {
                if(partnerCourse.getSupplierService() != null){
                    for (SupplyServicePartnerCourseEnum supplyServicePartnerCourseEnum : partnerCourse.getSupplierService()){
                        if(supplyServicePartnerCourseEnum.equals(SupplyServicePartnerCourseEnum.AULA)){
                            if(partnerCourse.getSupplier() != null) {
                                String addressPartner = partnerCourse.getSupplier().getBusinessName();
                                if(partnerCourse.getSupplier().getAddressList() != null)
                                {
                                    for(AddressPartner addressPartner1 : partnerCourse.getSupplier().getAddressList()){
                                        if(addressPartner1.getType().equals(TypeAddressPartnerEnum.OPERATIVO)){
                                            addressPartner+=", "+addressPartner1.getAddress().getFormattedAddress();
                                            requestMap.put("address", addressPartner);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        dogeRequestV1.setData(requestMap);
        String teacher = teacherCourse.getTeacher().getSurname() + "-" + teacherCourse.getTeacher().getName();
        dogeRequestV1.setFilename("Distacco-Docente-Dipendente"+courseEntity.getCourseCode()+"-"+teacher+".pdf");
        dogeRequestV1.setTemplate("DIstaccoDocenteDipendente");
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
    public DogeResponseV1 assignmentWithInvoice(CourseEntity courseEntity, TeacherCourse teacherCourse) throws Exception{
        if(teacherCourse == null) throw new BadRequestException("Teacher not found");
        DogeRequestV1 dogeRequestV1 = new DogeRequestV1();
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("project_code", courseEntity.getCourseCode());
        requestMap.put("project_code_2", courseEntity.getCourseCode());
        requestMap.put("course_description", courseEntity.getCourseTitle());
        if(courseEntity.getTotalHours() != null) {
            requestMap.put("tot_hours", courseEntity.getTotalHours().toString());

        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (teacherCourse.getTeacher() != null) {
            String teacher = teacherCourse.getTeacher().getSurname() + " " + teacherCourse.getTeacher().getName();
            requestMap.put("name_and_surname", teacher);
            requestMap.put("name_and_surname_2", teacher);
            requestMap.put("name_and_surname_3", teacher);
            requestMap.put("birthday_city", teacherCourse.getTeacher().getBirthPlace());
            requestMap.put("birthday_date", simpleDateFormat.format(teacherCourse.getTeacher().getDateOfBirth()));
            requestMap.put("fiscal_code", teacherCourse.getTeacher().getFiscalCode());
            requestMap.put("email", teacherCourse.getTeacher().getEmail());
            requestMap.put("total_hours", teacherCourse.getHoursTeachingLetterAssignment().toString());
        }

        requestMap.put("date", simpleDateFormat.format(new Date()));
        requestMap.put("project_day", courseEntity.getCourseStartDate());
        requestMap.put("start_hour", courseEntity.getMorningStartHour());
        requestMap.put("end_hour", courseEntity.getAfternoonEndHour());
        //Indirizzo sede del corso
        if(courseEntity.getPartnerList() != null){
            for(PartnerCourse partnerCourse : courseEntity.getPartnerList())
            {
                if(partnerCourse.getSupplierService() != null){
                    for (SupplyServicePartnerCourseEnum supplyServicePartnerCourseEnum : partnerCourse.getSupplierService()){
                        if(supplyServicePartnerCourseEnum.equals(SupplyServicePartnerCourseEnum.AULA)){
                            if(partnerCourse.getSupplier() != null) {
                                String addressPartner = partnerCourse.getSupplier().getBusinessName();
                                if(partnerCourse.getSupplier().getAddressList() != null)
                                {
                                    for(AddressPartner addressPartner1 : partnerCourse.getSupplier().getAddressList()){
                                        if(addressPartner1.getType().equals(TypeAddressPartnerEnum.OPERATIVO)){
                                            addressPartner+=", "+addressPartner1.getAddress().getFormattedAddress();
                                            requestMap.put("address", addressPartner);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        dogeRequestV1.setData(requestMap);
        String teacher = teacherCourse.getTeacher().getSurname() + "-" + teacherCourse.getTeacher().getName();
        dogeRequestV1.setFilename("Lettera-Incarico-Con-Fattura-"+teacher+".pdf");
        dogeRequestV1.setTemplate("LetteraIncaricoConFattura");
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
    public DogeResponseV1 assignmentWithWithholdingTax(CourseEntity courseEntity, TeacherCourse teacherCourse) throws Exception{
        if(teacherCourse == null) throw new BadRequestException("Teacher not found");
        DogeRequestV1 dogeRequestV1 = new DogeRequestV1();
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("project_code", courseEntity.getCourseCode());
        requestMap.put("project_code_2", courseEntity.getCourseCode());
        requestMap.put("course_description", courseEntity.getCourseTitle());
        if(courseEntity.getTotalHours() != null) {
            requestMap.put("tot_hours", courseEntity.getTotalHours().toString());

        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (teacherCourse.getTeacher() != null) {
            String teacher = teacherCourse.getTeacher().getSurname() + " " + teacherCourse.getTeacher().getName();
            requestMap.put("name_and_surname", teacher);
            requestMap.put("name_and_surname_2", teacher);
            requestMap.put("name_and_surname_3", teacher);
            requestMap.put("birthday_city", teacherCourse.getTeacher().getBirthPlace());
            requestMap.put("birthday_date", simpleDateFormat.format(teacherCourse.getTeacher().getDateOfBirth()));
            requestMap.put("fiscal_code", teacherCourse.getTeacher().getFiscalCode());
            requestMap.put("email", teacherCourse.getTeacher().getEmail());
            requestMap.put("total_hours", teacherCourse.getHoursTeachingLetterAssignment().toString());
        }

        requestMap.put("date", simpleDateFormat.format(new Date()));
        requestMap.put("project_day", courseEntity.getCourseStartDate());
        requestMap.put("start_hour", courseEntity.getMorningStartHour());
        requestMap.put("end_hour", courseEntity.getAfternoonEndHour());
        //Indirizzo sede del corso
        if(courseEntity.getPartnerList() != null){
            for(PartnerCourse partnerCourse : courseEntity.getPartnerList())
            {
                if(partnerCourse.getSupplierService() != null){
                    for (SupplyServicePartnerCourseEnum supplyServicePartnerCourseEnum : partnerCourse.getSupplierService()){
                        if(supplyServicePartnerCourseEnum.equals(SupplyServicePartnerCourseEnum.AULA)){
                            if(partnerCourse.getSupplier() != null) {
                                String addressPartner = partnerCourse.getSupplier().getBusinessName();
                                if(partnerCourse.getSupplier().getAddressList() != null)
                                {
                                    for(AddressPartner addressPartner1 : partnerCourse.getSupplier().getAddressList()){
                                        if(addressPartner1.getType().equals(TypeAddressPartnerEnum.OPERATIVO)){
                                            addressPartner+=", "+addressPartner1.getAddress().getFormattedAddress();
                                            requestMap.put("address", addressPartner);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        dogeRequestV1.setData(requestMap);
        String teacher = teacherCourse.getTeacher().getSurname() + "-" + teacherCourse.getTeacher().getName();
        dogeRequestV1.setFilename("Lettera-Incarico-Con-Ritenuta-"+teacher+".pdf");
        dogeRequestV1.setTemplate("LetteraIncaricoConRitenuta");
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
    public DogeResponseV1 register(UUID idCourse) throws Exception{
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(idCourse);
        if(!courseEntityOptional.isPresent()) throw new NotFoundException("Course with id "+idCourse+" not found");
        CourseEntity courseEntity = courseEntityOptional.get();
        DogeRequestV1 dogeRequestV1 = new DogeRequestV1();
        Map<String, Object> requestMap = new HashMap<>();

        requestMap.put("project_title", courseEntity.getCourseTitle());
        requestMap.put("project_type", courseEntity.getCourseType().getValue());
        requestMap.put("project_hour", courseEntity.getTotalHours().toString());

        String period="dal "+courseEntity.getCourseStartDate()+" al "+courseEntity.getCourseEndDate();
        requestMap.put("project_period", period);

        //Indirizzo sede del corso
        if(courseEntity.getPartnerList() != null){
            for(PartnerCourse partnerCourse : courseEntity.getPartnerList())
            {
                if(partnerCourse.getSupplierService() != null){
                    for (SupplyServicePartnerCourseEnum supplyServicePartnerCourseEnum : partnerCourse.getSupplierService()){
                        if(supplyServicePartnerCourseEnum.equals(SupplyServicePartnerCourseEnum.AULA)){
                            if(partnerCourse.getSupplier() != null) {
                                String addressPartner = partnerCourse.getSupplier().getBusinessName();
                                if(partnerCourse.getSupplier().getAddressList() != null)
                                {
                                    for(AddressPartner addressPartner1 : partnerCourse.getSupplier().getAddressList()){
                                        if(addressPartner1.getType().equals(TypeAddressPartnerEnum.OPERATIVO)){
                                            addressPartner+=", "+addressPartner1.getAddress().getFormattedAddress();
                                            requestMap.put("project_location", addressPartner);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        requestMap.put("project_code", courseEntity.getCourseCode());
        requestMap.put("project_code_2", courseEntity.getCourseCode());
        requestMap.put("project_code_3", courseEntity.getCourseCode());
        requestMap.put("project_code_4", courseEntity.getCourseCode());
        requestMap.put("project_code_5", courseEntity.getCourseCode());
        requestMap.put("project_code_6", courseEntity.getCourseCode());
        requestMap.put("project_code_7", courseEntity.getCourseCode());
        requestMap.put("project_code_8", courseEntity.getCourseCode());
        requestMap.put("project_code_9", courseEntity.getCourseCode());
        requestMap.put("project_code_10", courseEntity.getCourseCode());
        requestMap.put("project_code_11", courseEntity.getCourseCode());
        requestMap.put("project_code_12", courseEntity.getCourseCode());
        requestMap.put("project_code_13", courseEntity.getCourseCode());
        requestMap.put("project_code_14", courseEntity.getCourseCode());
        requestMap.put("project_code_15", courseEntity.getCourseCode());
        requestMap.put("project_code_16", courseEntity.getCourseCode());
        requestMap.put("project_code_17", courseEntity.getCourseCode());
        requestMap.put("project_code_18", courseEntity.getCourseCode());
        requestMap.put("project_code_19", courseEntity.getCourseCode());
        requestMap.put("project_code_20", courseEntity.getCourseCode());
        requestMap.put("project_code_21", courseEntity.getCourseCode());
        requestMap.put("project_code_22", courseEntity.getCourseCode());
        requestMap.put("project_code_23", courseEntity.getCourseCode());
        requestMap.put("project_code_24", courseEntity.getCourseCode());
        requestMap.put("project_code_25", courseEntity.getCourseCode());
        requestMap.put("project_code_26", courseEntity.getCourseCode());
        requestMap.put("project_code_27", courseEntity.getCourseCode());
        requestMap.put("project_code_28", courseEntity.getCourseCode());
        requestMap.put("project_code_29", courseEntity.getCourseCode());
        requestMap.put("project_code_30", courseEntity.getCourseCode());
        requestMap.put("project_code_31", courseEntity.getCourseCode());
        requestMap.put("project_code_32", courseEntity.getCourseCode());
        requestMap.put("project_code_33", courseEntity.getCourseCode());
        requestMap.put("project_code_34", courseEntity.getCourseCode());
        requestMap.put("project_code_35", courseEntity.getCourseCode());
        requestMap.put("project_code_36", courseEntity.getCourseCode());
        requestMap.put("project_code_37", courseEntity.getCourseCode());
        requestMap.put("project_code_38", courseEntity.getCourseCode());

        if(courseEntity.getTeacherList() != null){
            int i = 1;
            for (TeacherCourse teacherCourse : courseEntity.getTeacherList()){
                String role = "role_"+i;
                String surname = "surname_"+i;
                String name = "name_"+i;
                requestMap.put(role, teacherCourse.getRole().getValue());
                if(teacherCourse.getTeacher() != null) {
                    requestMap.put(surname, teacherCourse.getTeacher().getSurname());
                    requestMap.put(name, teacherCourse.getTeacher().getName());

                }
                i++;
            }
        }
        if(courseEntity.getPlacementList() != null){
            int i=1;
            for(PlacementCourse placementCourse : courseEntity.getPlacementList()){
                if(placementCourse.getLearner() != null) {
                    String learnerSurname = placementCourse.getLearner().getSurname();
                    String learnerName = placementCourse.getLearner().getName();
                    String learnerFiscalCode = placementCourse.getLearner().getFiscalCode();
                    String surname = "s_surname" + i;
                    requestMap.put(surname, learnerSurname);
                    String f1_surname = "f1_surname" + i;
                    requestMap.put(f1_surname, learnerSurname);
                    String f2_surname = "f2_surname" + i;
                    requestMap.put(f2_surname, learnerSurname);
                    String f3_surname = "f3_surname" + i;
                    requestMap.put(f3_surname, learnerSurname);
                    String f4_surname = "f4_surname" + i;
                    requestMap.put(f4_surname, learnerSurname);
                    String f5_surname = "f5_surname" + i;
                    requestMap.put(f5_surname, learnerSurname);
                    String f6_surname = "f6_surname" + i;
                    requestMap.put(f6_surname, learnerSurname);
                    String f7_surname = "f7_surname" + i;
                    requestMap.put(f7_surname, learnerSurname);
                    String f8_surname = "f8_surname" + i;
                    requestMap.put(f8_surname, learnerSurname);
                    String f9_surname = "f9_surname" + i;
                    requestMap.put(f9_surname, learnerSurname);
                    String f10_surname = "f10_surname" + i;
                    requestMap.put(f10_surname, learnerSurname);
                    String f11_surname = "f11_surname" + i;
                    requestMap.put(f11_surname, learnerSurname);
                    String f12_surname = "f12_surname" + i;
                    requestMap.put(f12_surname, learnerSurname);
                    String f13_surname = "f13_surname" + i;
                    requestMap.put(f13_surname, learnerSurname);
                    String f14_surname = "f14_surname" + i;
                    requestMap.put(f14_surname, learnerSurname);
                    String f15_surname = "f15_surname" + i;
                    requestMap.put(f15_surname, learnerSurname);
                    String f16_surname = "f16_surname" + i;
                    requestMap.put(f16_surname, learnerSurname);
                    String f17_surname = "f17_surname" + i;
                    requestMap.put(f17_surname, learnerSurname);
                    String f18_surname = "f18_surname" + i;
                    requestMap.put(f18_surname, learnerSurname);
                    String f19_surname = "f19_surname" + i;
                    requestMap.put(f19_surname, learnerSurname);
                    String f20_surname = "f20_surname" + i;
                    requestMap.put(f20_surname, learnerSurname);
                    String f21_surname = "f21_surname" + i;
                    requestMap.put(f21_surname, learnerSurname);
                    String f22_surname = "f22_surname" + i;
                    requestMap.put(f22_surname, learnerSurname);
                    String f23_surname = "f23_surname" + i;
                    requestMap.put(f23_surname, learnerSurname);
                    String f24_surname = "f24_surname" + i;
                    requestMap.put(f24_surname, learnerSurname);
                    String f25_surname = "f25_surname" + i;
                    requestMap.put(f25_surname, learnerSurname);
                    String f26_surname = "f26_surname" + i;
                    requestMap.put(f26_surname, learnerSurname);
                    String f27_surname = "f27_surname" + i;
                    requestMap.put(f27_surname, learnerSurname);
                    String f28_surname = "f28_surname" + i;
                    requestMap.put(f28_surname, learnerSurname);
                    String f29_surname = "f29_surname" + i;
                    requestMap.put(f29_surname, learnerSurname);
                    String f30_surname = "f30_surname" + i;
                    requestMap.put(f30_surname, learnerSurname);
                    String f31_surname = "f31_surname" + i;
                    requestMap.put(f31_surname, learnerSurname);
                    String f32_surname = "f32_surname" + i;
                    requestMap.put(f32_surname, learnerSurname);
                    String f33_surname = "f33_surname" + i;
                    requestMap.put(f33_surname, learnerSurname);
                    String f34_surname = "f34_surname" + i;
                    requestMap.put(f34_surname, learnerSurname);
                    String f35_surname = "f35_surname" + i;
                    requestMap.put(f35_surname, learnerSurname);

                    String name = "s_name" + i;
                    String f1_name = "f1_name" + i;
                    String f2_name = "f2_name" + i;
                    String f3_name = "f3_name" + i;
                    String f4_name = "f4_name" + i;
                    String f5_name = "f5_name" + i;
                    String f6_name = "f6_name" + i;
                    String f7_name = "f7_name" + i;
                    String f8_name = "f8_name" + i;
                    String f9_name = "f9_name" + i;
                    String f10_name = "f10_name" + i;
                    String f11_name = "f11_name" + i;
                    String f12_name = "f12_name" + i;
                    String f13_name = "f13_name" + i;
                    String f14_name = "f14_name" + i;
                    String f15_name = "f15_name" + i;
                    String f16_name = "f16_name" + i;
                    String f17_name = "f17_name" + i;
                    String f18_name = "f18_name" + i;
                    String f19_name = "f19_name" + i;
                    String f20_name = "f20_name" + i;
                    String f21_name = "f21_name" + i;
                    String f22_name = "f22_name" + i;
                    String f23_name = "f23_name" + i;
                    String f24_name = "f24_name" + i;
                    String f25_name = "f25_name" + i;
                    String f26_name = "f26_name" + i;
                    String f27_name = "f27_name" + i;
                    String f28_name = "f28_name" + i;
                    String f29_name = "f29_name" + i;
                    String f30_name = "f30_name" + i;
                    String f31_name = "f31_name" + i;
                    String f32_name = "f32_name" + i;
                    String f33_name = "f33_name" + i;
                    String f34_name = "f34_name" + i;
                    String f35_name = "f35_name" + i;
                    requestMap.put(name, learnerName);
                    requestMap.put(f1_name, learnerName);
                    requestMap.put(f2_name, learnerName);
                    requestMap.put(f3_name, learnerName);
                    requestMap.put(f4_name, learnerName);
                    requestMap.put(f5_name, learnerName);
                    requestMap.put(f6_name, learnerName);
                    requestMap.put(f7_name, learnerName);
                    requestMap.put(f8_name, learnerName);
                    requestMap.put(f9_name, learnerName);
                    requestMap.put(f10_name, learnerName);
                    requestMap.put(f11_name, learnerName);
                    requestMap.put(f12_name, learnerName);
                    requestMap.put(f13_name, learnerName);
                    requestMap.put(f14_name, learnerName);
                    requestMap.put(f15_name, learnerName);
                    requestMap.put(f16_name, learnerName);
                    requestMap.put(f17_name, learnerName);
                    requestMap.put(f18_name, learnerName);
                    requestMap.put(f19_name, learnerName);
                    requestMap.put(f20_name, learnerName);
                    requestMap.put(f21_name, learnerName);
                    requestMap.put(f22_name, learnerName);
                    requestMap.put(f23_name, learnerName);
                    requestMap.put(f24_name, learnerName);
                    requestMap.put(f25_name, learnerName);
                    requestMap.put(f26_name, learnerName);
                    requestMap.put(f27_name, learnerName);
                    requestMap.put(f28_name, learnerName);
                    requestMap.put(f29_name, learnerName);
                    requestMap.put(f30_name, learnerName);
                    requestMap.put(f31_name, learnerName);
                    requestMap.put(f32_name, learnerName);
                    requestMap.put(f33_name, learnerName);
                    requestMap.put(f34_name, learnerName);
                    requestMap.put(f35_name, learnerName);

                    String fiscal_code = "fiscal_code" + i;
                    requestMap.put(fiscal_code, learnerFiscalCode);
                    i++;
                }
            }
        }
        if(courseEntity.getMorningStartHour() != null && courseEntity.getMorningEndHour() != null && courseEntity.getAfternoonStatrHour() != null && courseEntity.getAfternoonEndHour() != null)
        {
            requestMap.put("f1_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f3_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f5_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f7_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f9_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f11_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f13_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f15_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f17_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f19_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f21_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f23_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f25_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f27_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f29_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f31_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f33_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f35_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f2_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f4_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f6_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f8_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f10_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f12_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f14_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f16_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f18_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f20_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f22_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f24_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f25_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f26_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f28_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f30_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f32_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f34_start_hour", courseEntity.getAfternoonStatrHour());

            requestMap.put("f1_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f3_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f5_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f7_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f9_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f11_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f13_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f15_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f17_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f19_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f21_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f23_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f25_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f27_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f29_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f31_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f33_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f35_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f2_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f4_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f6_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f8_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f10_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f12_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f14_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f16_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f18_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f20_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f22_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f24_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f25_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f26_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f28_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f30_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f32_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f34_end_hour", courseEntity.getAfternoonEndHour());
        }
        else if(courseEntity.getMorningStartHour() != null && courseEntity.getMorningEndHour() != null)
        {
            requestMap.put("f1_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f3_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f5_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f7_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f9_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f11_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f13_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f15_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f17_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f19_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f21_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f23_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f25_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f27_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f29_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f31_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f33_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f35_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f2_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f4_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f6_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f8_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f10_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f12_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f14_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f16_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f18_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f20_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f22_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f24_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f25_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f26_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f28_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f30_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f32_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f34_start_hour", courseEntity.getMorningStartHour());
            requestMap.put("f1_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f3_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f5_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f7_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f9_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f11_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f13_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f15_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f17_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f19_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f21_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f23_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f25_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f27_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f29_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f31_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f33_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f35_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f2_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f4_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f6_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f8_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f10_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f12_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f14_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f16_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f18_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f20_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f22_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f24_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f25_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f26_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f28_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f30_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f32_end_hour", courseEntity.getMorningEndHour());
            requestMap.put("f34_end_hour", courseEntity.getMorningEndHour());
        }
        else if (courseEntity.getAfternoonStatrHour() != null && courseEntity.getAfternoonEndHour() != null){
            requestMap.put("f1_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f3_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f5_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f7_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f9_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f11_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f13_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f15_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f17_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f19_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f21_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f23_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f25_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f27_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f29_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f31_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f33_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f35_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f2_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f4_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f6_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f8_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f10_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f12_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f14_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f16_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f18_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f20_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f22_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f24_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f25_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f26_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f28_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f30_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f32_start_hour", courseEntity.getAfternoonStatrHour());
            requestMap.put("f34_start_hour", courseEntity.getAfternoonStatrHour());

            requestMap.put("f1_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f3_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f5_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f7_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f9_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f11_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f13_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f15_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f17_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f19_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f21_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f23_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f25_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f27_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f29_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f31_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f33_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f35_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f2_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f4_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f6_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f8_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f10_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f12_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f14_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f16_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f18_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f20_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f22_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f24_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f25_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f26_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f28_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f30_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f32_end_hour", courseEntity.getAfternoonEndHour());
            requestMap.put("f34_end_hour", courseEntity.getAfternoonEndHour());
        }


        dogeRequestV1.setData(requestMap);
        dogeRequestV1.setFilename("Registro-Didattico-"+courseEntity.getCourseCode()+".pdf");
        dogeRequestV1.setTemplate("RegistroDidattico");
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
}
