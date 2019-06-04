package it.tcgroup.vilear.coursemanager.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;
import it.tcgroup.vilear.coursemanager.common.exception.BadRequestException;
import it.tcgroup.vilear.coursemanager.common.exception.NotFoundException;
import it.tcgroup.vilear.coursemanager.common.util.DateUtil;
import it.tcgroup.vilear.coursemanager.common.util.HttpUtil;
import it.tcgroup.vilear.coursemanager.controller.payload.request.DogeRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.DogeResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdentifierResponseV1;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.LearnerEntity;
import it.tcgroup.vilear.coursemanager.entity.dto.LearnerDto;
import it.tcgroup.vilear.coursemanager.entity.dto.PartnerDto;
import it.tcgroup.vilear.coursemanager.entity.dto.TeacherDto;
import it.tcgroup.vilear.coursemanager.entity.enumerated.PaymentModalityTradeUnionEnum;
import it.tcgroup.vilear.coursemanager.entity.enumerated.SupplyServicePartnerCourseEnum;
import it.tcgroup.vilear.coursemanager.entity.enumerated.TypeAddressPartnerEnum;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.PartnerCourse;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.PlacementCourse;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.TeacherCourse;
import it.tcgroup.vilear.coursemanager.entity.jsonb.partner.AddressPartner;
import it.tcgroup.vilear.coursemanager.repository.CourseRepository;
import it.tcgroup.vilear.coursemanager.repository.LearnerRepository;
import it.tcgroup.vilear.coursemanager.service.DogeService;
import okhttp3.Response;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Service
public class DogeServiceImpl implements DogeService {

    @Autowired
    private HttpUtil httpUtil;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LearnerRepository learnerRepository;

    @Autowired
    private DateUtil dateUtil;

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
    public List<IdentifierResponseV1> generateCertificate(UUID idCourse) throws Exception {

        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(idCourse);
        if(!courseEntityOptional.isPresent()) throw new NotFoundException("Course with id "+idCourse+" not found");

        CourseEntity courseEntity = courseEntityOptional.get();
        List<IdentifierResponseV1> identifierResponseV1s = new ArrayList<>();

        if(courseEntity.getPlacementList() != null)
        {
            for(PlacementCourse placementCourse : courseEntity.getPlacementList())
            {
                if(placementCourse.getLearner() != null) {

                    DogeResponseV1 dogeResponseV1 = this.learnerCertificate(courseEntity, placementCourse.getLearner());
                    identifierResponseV1s.add(new IdentifierResponseV1(dogeResponseV1.getDocumentId()));

                    Attachment certificate = new Attachment();
                    certificate.setFileManagerId(dogeResponseV1.getDocumentId());
                    certificate.setCreatedAt(dateUtil.getNowDate());
                    certificate.setResourceType("attestato_di_frequenza");
                    certificate.setDescription("attestato_di_frequenza");
                    certificate.setMimeType("application/pdf");
                    certificate.setBlobName(dogeResponseV1.getDocumentId()+".pdf");
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                    certificate.setFileManagerName("attestato_di_frequenza_corso_"+ courseEntity.getCourseTitle()+"_del_"+format.format(courseEntity.getCourseStartDate())+".pdf");
                    Optional<LearnerEntity> optLearner = learnerRepository.findById(UUID.fromString(placementCourse.getLearner().getId()));
                    if(optLearner.isPresent()){
                        LearnerEntity learnerEntity = optLearner.get();
                        learnerEntity.getAttachments().add(certificate);
                        learnerRepository.save(learnerEntity);
                    }

                    certificate.setFileManagerName("attestato_di_frequenza_"+placementCourse.getLearner().getSurname()+"_"+placementCourse.getLearner().getName()+"_"+format.format(courseEntity.getCourseStartDate())+".pdf" );
                    if(courseEntity.getDocumentsAttachment() == null)
                        courseEntity.setDocumentsAttachment(new LinkedList<Attachment>());
                    courseEntity.getDocumentsAttachment().add(certificate);


                }
            }
        }

        courseRepository.save(courseEntity);

        return identifierResponseV1s;

    }

    @Override
    public DogeResponseV1 learnerCertificate(CourseEntity course, LearnerDto learner) throws Exception
    {

        try{

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

        }catch (NullPointerException e){
            throw new BadParametersException("Non sono presenti tutti i parametri necessari per effettuare la generazione degli attestati");
        }


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
        requestMap.put("hour_3", courseEntity.getAfternoonStartHour().toString());
        requestMap.put("hour_4", courseEntity.getAfternoonEndHour().toString());

        //Indirizzo sede del corso
        if(courseEntity.getPartnerList() != null){
            for(PartnerCourse partnerCourse : courseEntity.getPartnerList())
            {
                if(partnerCourse.getSupplyServices() != null){
                    for (PartnerCourse.SupplierService supplierService : partnerCourse.getSupplyServices()){
                        SupplyServicePartnerCourseEnum supplyServicePartnerCourseEnum = supplierService.getSupplierService();
                        if(supplyServicePartnerCourseEnum.equals(SupplyServicePartnerCourseEnum.AULA)){
                            if(partnerCourse.getSupplier() != null) {
                                String addressPartner = partnerCourse.getSupplier().getBusinessName();
                                if(partnerCourse.getSupplier().getAddressList() != null)
                                {
                                    for(AddressPartner addressPartner1 : partnerCourse.getSupplier().getAddressList()){
                                        if(!addressPartner1.getLegalAddress()){
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

        Attachment certificate = new Attachment();
        certificate.setFileManagerId(dogeResponse.getDocumentId());
        certificate.setCreatedAt(dateUtil.getNowDate());
        certificate.setResourceType("comunicazione-inail");
        certificate.setDescription("comunicazione-inail");
        certificate.setMimeType("application/pdf");
        certificate.setBlobName(dogeResponse.getDocumentId()+".pdf");
        certificate.setFileManagerName("Comunicazione-Inail"+courseEntity.getCourseCode()+".pdf");

        if(courseEntity.getDocumentsAttachment() == null)
            courseEntity.setDocumentsAttachment(new LinkedList<Attachment>());
        courseEntity.getDocumentsAttachment().add(certificate);

        courseRepository.save(courseEntity);

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

        Attachment certificate = new Attachment();
        certificate.setFileManagerId(dogeResponse.getDocumentId());
        certificate.setCreatedAt(dateUtil.getNowDate());
        certificate.setResourceType("consegna_ticket");
        certificate.setDescription("consegna_ticket");
        certificate.setMimeType("application/pdf");
        certificate.setBlobName(dogeResponse.getDocumentId()+".pdf");
        certificate.setFileManagerName("consegna-ticket-2260-"+courseEntity.getCourseCode()+".pdf");

        if(courseEntity.getDocumentsAttachment() == null)
            courseEntity.setDocumentsAttachment(new LinkedList<Attachment>());
        courseEntity.getDocumentsAttachment().add(certificate);

        courseRepository.save(courseEntity);

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
        dogeRequestV1.setFilename("Valutazione-discenti-" + courseEntity.getCourseCode() + ".xlsx");
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

        Attachment certificate = new Attachment();
        certificate.setFileManagerId(dogeResponse.getDocumentId());
        certificate.setCreatedAt(dateUtil.getNowDate());
        certificate.setResourceType("valutazione_discenti");
        certificate.setDescription("valutazione_discenti");
        certificate.setMimeType("xlsx");
        certificate.setBlobName(dogeResponse.getDocumentId()+".xlsx");
        certificate.setFileManagerName("Valutazione-discenti-" + courseEntity.getCourseCode() + ".xlsx");

        if(courseEntity.getDocumentsAttachment() == null)
            courseEntity.setDocumentsAttachment(new LinkedList<Attachment>());
        courseEntity.getDocumentsAttachment().add(certificate);

        courseRepository.save(courseEntity);

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
                if(partnerCourse.getSupplyServices() != null){
                    for (PartnerCourse.SupplierService supplierService : partnerCourse.getSupplyServices()){
                        SupplyServicePartnerCourseEnum supplyServicePartnerCourseEnum = supplierService.getSupplierService();
                        if(supplyServicePartnerCourseEnum.equals(SupplyServicePartnerCourseEnum.AULA)){
                            if(partnerCourse.getSupplier() != null) {
                                String addressPartner = partnerCourse.getSupplier().getBusinessName();
                                if(partnerCourse.getSupplier().getAddressList() != null)
                                {
                                    for(AddressPartner addressPartner1 : partnerCourse.getSupplier().getAddressList()){
                                        if(!addressPartner1.getLegalAddress()){
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
                if(partnerCourse.getSupplyServices() != null){
                    for (PartnerCourse.SupplierService supplierService : partnerCourse.getSupplyServices()){
                        SupplyServicePartnerCourseEnum supplyServicePartnerCourseEnum = supplierService.getSupplierService();
                        if(supplyServicePartnerCourseEnum.equals(SupplyServicePartnerCourseEnum.AULA)){
                            if(partnerCourse.getSupplier() != null) {
                                String addressPartner = partnerCourse.getSupplier().getBusinessName();
                                if(partnerCourse.getSupplier().getAddressList() != null)
                                {
                                    for(AddressPartner addressPartner1 : partnerCourse.getSupplier().getAddressList()){
                                        if(!addressPartner1.getLegalAddress()){
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
                if(partnerCourse.getSupplyServices() != null){
                    for (PartnerCourse.SupplierService supplierService : partnerCourse.getSupplyServices()){
                        SupplyServicePartnerCourseEnum supplyServicePartnerCourseEnum = supplierService.getSupplierService();
                        if(supplyServicePartnerCourseEnum.equals(SupplyServicePartnerCourseEnum.AULA)){
                            if(partnerCourse.getSupplier() != null) {
                                String addressPartner = partnerCourse.getSupplier().getBusinessName();
                                if(partnerCourse.getSupplier().getAddressList() != null)
                                {
                                    for(AddressPartner addressPartner1 : partnerCourse.getSupplier().getAddressList()){
                                        if(!addressPartner1.getLegalAddress()){
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


    public DogeResponseV1 firstRegister(CourseEntity courseEntity, String numPages) throws Exception{
        DogeRequestV1 dogeRequestV1 = new DogeRequestV1();
        Map<String, Object> requestMap = new HashMap<>();
        if(courseEntity.getCourseTitle() != null)
            requestMap.put("project_title", courseEntity.getCourseTitle());
        if(courseEntity.getCourseType() != null)
            requestMap.put("project_type", courseEntity.getCourseType().getValue());
        if(courseEntity.getTotalHours() != null)
        requestMap.put("project_hour", courseEntity.getTotalHours().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        if(courseEntity.getCourseStartDate() != null && courseEntity.getCourseEndDate() != null) {
            String period = "dal " + simpleDateFormat.format(courseEntity.getCourseStartDate()) + " al " + simpleDateFormat.format(courseEntity.getCourseEndDate());
            requestMap.put("project_period", period);
        }
        //Indirizzo sede del corso
        if(courseEntity.getPartnerList() != null){
            for(PartnerCourse partnerCourse : courseEntity.getPartnerList())
            {
                if(partnerCourse.getSupplyServices() != null){
                    for (PartnerCourse.SupplierService supplierService : partnerCourse.getSupplyServices()){
                        SupplyServicePartnerCourseEnum supplyServicePartnerCourseEnum = supplierService.getSupplierService();
                        if(supplyServicePartnerCourseEnum.equals(SupplyServicePartnerCourseEnum.AULA)){
                            if(partnerCourse.getSupplier() != null) {
                                String addressPartner = partnerCourse.getSupplier().getBusinessName();
                                if(partnerCourse.getSupplier().getAddressList() != null)
                                {
                                    for(AddressPartner addressPartner1 : partnerCourse.getSupplier().getAddressList()){
                                        if(!addressPartner1.getLegalAddress()){
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
        if(courseEntity.getCourseCode() != null) {
            requestMap.put("project_code", courseEntity.getCourseCode());
            requestMap.put("project_code_2", courseEntity.getCourseCode());
            requestMap.put("project_code_3", courseEntity.getCourseCode());
        }
        if(courseEntity.getTeacherList() != null){
            int i = 1;
            for (TeacherCourse teacherCourse : courseEntity.getTeacherList()){
                String role = "role_"+i;
                String surname = "surname_"+i;
                String name = "name_"+i;
                if( teacherCourse.getRole() != null)
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
                    String surname = "s_surname_" + i;
                    requestMap.put(surname, learnerSurname);
                    String name = "s_name_" + i;
                    requestMap.put(name, learnerName);
                    String fiscal_code = "fiscal_code" + i;
                    requestMap.put(fiscal_code, learnerFiscalCode);
                    i++;
                }
            }
        }
        requestMap.put("num_pages", numPages);
        dogeRequestV1.setData(requestMap);
        dogeRequestV1.setFilename("Registro-Didattico-"+courseEntity.getCourseCode()+"-First.pdf");
        dogeRequestV1.setTemplate("RegistroDidatticoFirst");
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

    public DogeResponseV1 secondRegister(CourseEntity courseEntity, String startHour, String endHour, String day, String month, String year, Integer numPage) throws Exception{
        DogeRequestV1 dogeRequestV1 = new DogeRequestV1();
        Map<String, Object> requestMap = new HashMap<>();
        if(courseEntity.getCourseCode() != null)
            requestMap.put("project_code", courseEntity.getCourseCode());
        if(courseEntity.getPlacementList() != null){
            int i=1;
            for(PlacementCourse placementCourse : courseEntity.getPlacementList()){
                if(placementCourse.getLearner() != null) {
                    String learnerSurname = placementCourse.getLearner().getSurname();
                    String learnerName = placementCourse.getLearner().getName();
                    String surname = "s_surname_" + i;
                    requestMap.put(surname, learnerSurname);
                    String name = "s_name_" + i;
                    requestMap.put(name, learnerName);
                    i++;
                }
            }
        }
        requestMap.put("start_hour", startHour);
        requestMap.put("end_hour", endHour);
        requestMap.put("day", day);
        requestMap.put("month", month);
        requestMap.put("year", year);
        requestMap.put("num_page", numPage.toString());
        dogeRequestV1.setData(requestMap);
        dogeRequestV1.setFilename("Registro-Didattico-"+courseEntity.getCourseCode()+"-Second.pdf");
        dogeRequestV1.setTemplate("RegistroDidatticoSecond");
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

    public DogeResponseV1 thirdRegister(CourseEntity courseEntity) throws Exception{
        DogeRequestV1 dogeRequestV1 = new DogeRequestV1();
        Map<String, Object> requestMap = new HashMap<>();
        if(courseEntity.getCourseCode() != null)
            requestMap.put("project_code_23", courseEntity.getCourseCode());
        if(courseEntity.getPlacementList() != null){
            int i=1;
            for(PlacementCourse placementCourse : courseEntity.getPlacementList()){
                if(placementCourse.getLearner() != null) {
                    String learnerSurname = placementCourse.getLearner().getSurname();
                    String learnerName = placementCourse.getLearner().getName();
                    String surname = "s1_surname_" + i;
                    requestMap.put(surname, learnerSurname);
                    String name = "s1_name_" + i;
                    requestMap.put(name, learnerName);
                    i++;
                }
            }
        }
        dogeRequestV1.setData(requestMap);
        dogeRequestV1.setFilename("Registro-Didattico-"+courseEntity.getCourseCode()+"-Third.pdf");
        dogeRequestV1.setTemplate("RegistroDidatticoThird");
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

    public final static Date find(int year){
        if ( (year < 1573) || (year > 2499) ) {
            throw new BadRequestException("Year not valid");
        }

        int a = year % 19;
        int b = year % 4;
        int c = year % 7;

        int m = 0;
        int n = 0;

        if ( (year >= 1583) && (year <= 1699) ) { m = 22; n = 2; }
        if ( (year >= 1700) && (year <= 1799) ) { m = 23; n = 3; }
        if ( (year >= 1800) && (year <= 1899) ) { m = 23; n = 4; }
        if ( (year >= 1900) && (year <= 2099) ) { m = 24; n = 5; }
        if ( (year >= 2100) && (year <= 2199) ) { m = 24; n = 6; }
        if ( (year >= 2200) && (year <= 2299) ) { m = 25; n = 0; }
        if ( (year >= 2300) && (year <= 2399) ) { m = 26; n = 1; }
        if ( (year >= 2400) && (year <= 2499) ) { m = 25; n = 1; }

        int d = (19 * a + m) % 30;
        int e = (2 * b + 4 * c + 6 * d + n) % 7;

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR , year);

        if ( d+e < 10 ) {
            calendar.set(Calendar.MONTH , Calendar.MARCH);
            calendar.set(Calendar.DAY_OF_MONTH, d + e + 22);
        } else {
            calendar.set(Calendar.MONTH , Calendar.APRIL);
            int day = d+e-9;
            if ( 26 == day ) {day = 19;}
            if ( ( 25 == day ) && ( 28 == d) && ( e == 6 ) && ( a > 10 ) ) { day = 18; }
            calendar.set(Calendar.DAY_OF_MONTH, day);
        }

        return calendar.getTime();
    }

    public List<String> holidays(int year) throws ParseException {
        List<String> holidays = new ArrayList<>();
        String natale = year+"-12-25";
        holidays.add(natale);
        String capodanno = year+"-01-01";
        holidays.add(capodanno);
        String epifania = year+"-01-06";
        holidays.add(epifania);
        String liberazione = year+"-04-25";
        holidays.add(liberazione);
        String lavoro = year+"-05-01";
        holidays.add(lavoro);
        String repubblica = year+"-06-02";
        holidays.add(repubblica);
        String ferragosto = year+"-08-15";
        holidays.add(ferragosto);
        String ognissanti = year+"-11-01";
        holidays.add(ognissanti);
        String immacolata = year+"-12-08";
        holidays.add(immacolata);
        String stefano = year+"-12-26";
        holidays.add(stefano);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date easterDate = find(year);
        Calendar calendar2 = Calendar.getInstance();
        String easter = simpleDateFormat.format(easterDate);
        calendar2.setTime(simpleDateFormat.parse(easter));
        calendar2.add(Calendar.DAY_OF_MONTH, 1);
        easter = simpleDateFormat.format(calendar2.getTime());
        holidays.add(easter);

        return holidays;

    }

    @Override
    public List<DogeResponseV1> register(UUID idCourse) throws Exception{
        List<DogeResponseV1> dogeResponseV1List = new LinkedList<>();
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(idCourse);
        if(!courseEntityOptional.isPresent()) throw new NotFoundException("Course with id "+idCourse+" not found");
        CourseEntity courseEntity = courseEntityOptional.get();
        int numPages = 11;
        if(courseEntity.getCourseStartDate() != null && courseEntity.getCourseEndDate() != null) {
            boolean finish = false;
            String start = courseEntity.getCourseStartDate().toString();
            Date endDate = courseEntity.getCourseEndDate();
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String end = sdf.format(endDate);
            calendar.setTime(sdf.parse(start));
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("E");
            Integer numPage = 4;

            while(!finish) {
                List<String> holidays = holidays(calendar.get(Calendar.YEAR));
                    if(!simpleDateFormat2.format(calendar.getTime()).equalsIgnoreCase("sab") && !simpleDateFormat2.format(calendar.getTime()).equalsIgnoreCase("dom") && !holidays.contains(sdf.format(calendar.getTime()))) {
                    int giorno = calendar.get(Calendar.DAY_OF_MONTH);
                    String day = "";
                    if(giorno < 10){
                        day += "0"+giorno+"";
                    } else day = ""+giorno;
                    int mese = calendar.get(Calendar.MONTH)+1;
                    String month = "";
                    if(mese < 10){
                        month += "0"+mese;
                    } else {month += ""+mese;}
                    String year = ""+calendar.get(Calendar.YEAR);

                    SimpleDateFormat hour = new SimpleDateFormat("HH:mm");
                    if (courseEntity.getMorningStartHour() != null && courseEntity.getMorningEndHour() != null && courseEntity.getAfternoonStartHour() != null && courseEntity.getAfternoonEndHour() != null) {
                        DogeResponseV1 secondResponse1 = secondRegister(courseEntity, hour.format(courseEntity.getMorningStartHour()), hour.format(courseEntity.getMorningEndHour()), day, month, year, numPage);

                        numPage++;

                        DogeResponseV1 secondResponse2 = secondRegister(courseEntity, hour.format(courseEntity.getAfternoonStartHour()), hour.format(courseEntity.getAfternoonEndHour()), day, month, year, numPage);
                        ((LinkedList<DogeResponseV1>) dogeResponseV1List).addLast(secondResponse1);
                        ((LinkedList<DogeResponseV1>) dogeResponseV1List).addLast(secondResponse2);
                    } else if (courseEntity.getMorningStartHour() != null && courseEntity.getMorningEndHour() != null) {
                        DogeResponseV1 secondResponse1 = secondRegister(courseEntity, hour.format(courseEntity.getMorningStartHour()), hour.format(courseEntity.getMorningEndHour()), day, month, year, numPage);
                        ((LinkedList<DogeResponseV1>) dogeResponseV1List).addLast(secondResponse1);
                    } else if (courseEntity.getAfternoonStartHour() != null && courseEntity.getAfternoonEndHour() != null) {
                        DogeResponseV1 secondResponse2 = secondRegister(courseEntity, hour.format(courseEntity.getAfternoonStartHour()), hour.format(courseEntity.getAfternoonEndHour()), day, month, year, numPage);
                        ((LinkedList<DogeResponseV1>) dogeResponseV1List).addLast(secondResponse2);
                    }

                    numPage++;
                }
                if(start.compareTo(end) < 0){
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    start = sdf.format(calendar.getTime());

                } else finish = true;
            }

            numPage -= 4;
            numPages += numPage;
        }
        String pages = ""+numPages+"";

        DogeResponseV1 firstResponse = firstRegister(courseEntity, pages);
        ((LinkedList<DogeResponseV1>) dogeResponseV1List).addFirst(firstResponse);
        DogeResponseV1 thirdResponse = thirdRegister(courseEntity);
        ((LinkedList<DogeResponseV1>) dogeResponseV1List).addLast(thirdResponse);
        return dogeResponseV1List;
    }

    @Override
    public DogeResponseV1 tradeUnionTeaching(CourseEntity courseEntity, TeacherCourse teacherCourse) throws Exception{
        if(teacherCourse == null) throw new BadRequestException("Teacher not found");
        DogeRequestV1 dogeRequestV1 = new DogeRequestV1();
        Map<String, Object> requestMap = new HashMap<>();

        if(teacherCourse.getTeacher() != null) {
            TeacherDto teacherDto = teacherCourse.getTeacher();
            String teacher = teacherDto.getSurname()+" "+teacherDto.getName();
            requestMap.put("name_and_surname", teacher);
            requestMap.put("name_and_surname_2", teacher.toUpperCase());
            String birthday = teacherDto.getBirthPlace()+", "+teacherDto.getDateOfBirth();
            requestMap.put("birthday", birthday);
            requestMap.put("fiscal_code", teacherDto.getFiscalCode());
            if(teacherDto.getVatHolder()){
                requestMap.put("check_yes", true);
                requestMap.put("check_no", false);
                requestMap.put("vat_number", teacherDto.getVatNumber());
            } else {
                requestMap.put("check_yes", false);
                requestMap.put("check_no", true);
            }
            if(teacherDto.getProfessionalOrderRegistration()){
                requestMap.put("check_yes_2", true);
                requestMap.put("check_no_2", false);
                requestMap.put("albo", teacherDto.getSector());
                requestMap.put("code_albo", teacherDto.getRegister());
            } else {
                requestMap.put("check_yes_2", false);
                requestMap.put("check_no_2", true);
            }
            if(teacherDto.getPublicEmployee()){
                requestMap.put("check_yes_3", true);
                requestMap.put("check_no_3", false);
            } else {
                requestMap.put("check_yes_3", false);
                requestMap.put("check_no_3", true);
            }
        }
        requestMap.put("organization", teacherCourse.getAcronymTradeUnino().getValue());
        if(teacherCourse.getPaymentModalityTradeUnion().equals(PaymentModalityTradeUnionEnum.CON_DELEGA_DI_INCASSO)){
            requestMap.put("check_yes_4", true);
            requestMap.put("check_no_4", false);
        }else {
            requestMap.put("check_yes_4", false);
            requestMap.put("check_no_4", true);
        }
        requestMap.put("course_description", courseEntity.getCourseTitle());
        requestMap.put("project_code", courseEntity.getCourseCode());
        requestMap.put("doc_hours", teacherCourse.getHoursTeachingLetterAssignment().toString());
        requestMap.put("note", courseEntity.getNote());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        requestMap.put("date", simpleDateFormat.format(new Date()));

        dogeRequestV1.setData(requestMap);
        String teacherName = teacherCourse.getTeacher().getSurname()+"-"+teacherCourse.getTeacher().getName();
        dogeRequestV1.setFilename("Docenza-Sindacale-"+teacherName+".pdf");
        dogeRequestV1.setTemplate("DocenzaSindacale");
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
