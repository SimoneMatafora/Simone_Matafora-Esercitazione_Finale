package it.tcgroup.vilear.coursemanager.controller;

import com.itextpdf.text.DocumentException;
import io.swagger.annotations.Api;
import it.tcgroup.vilear.coursemanager.common.exception.NotFoundException;
import it.tcgroup.vilear.coursemanager.common.util.DateUtil;
import it.tcgroup.vilear.coursemanager.controller.payload.request.UploadRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.*;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.LearnerEntity;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.PlacementCourse;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.TeacherCourse;
import it.tcgroup.vilear.coursemanager.repository.CourseRepository;
import it.tcgroup.vilear.coursemanager.repository.LearnerRepository;
import it.tcgroup.vilear.coursemanager.service.DogeService;
import it.tcgroup.vilear.coursemanager.service.FilemanagerService;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1")
@Api("Doge")
public class DogeController {

    @Autowired
    private DogeService dogeService;

    @Autowired
    private FilemanagerService filemanagerService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LearnerRepository learnerRepository;

    @Autowired
    private DateUtil dateUtil;

    @GetMapping(value = "/pdf/certificate/{UUID}")
    public ResponseEntity<List<IdentifierResponseV1>> getCertificates(@PathVariable("UUID") UUID idCourse) throws Exception{
        List<IdentifierResponseV1> identifierResponseV1List = dogeService.generateCertificate(idCourse);
        return new ResponseEntity<>(identifierResponseV1List, HttpStatus.OK);
    }

    @GetMapping(value = "/pdf/inailcomunication/{UUID}")
    public ResponseEntity<IdentifierResponseV1> getInailComunication(@PathVariable("UUID") UUID idCourse) throws Exception{
        DogeResponseV1 dogeResponseV1 = dogeService.inailComunication(idCourse);
        return new ResponseEntity<>(new IdentifierResponseV1(dogeResponseV1.getDocumentId()), HttpStatus.OK);
    }

    @GetMapping(value = "/pdf/ticketdelivery/{UUID}")
    public ResponseEntity<IdentifierResponseV1> getTicketDelivery(@PathVariable("UUID") UUID idCourse) throws Exception{
        DogeResponseV1 dogeResponseV1 = dogeService.ticketDelivery(idCourse);
        return new ResponseEntity<>(new IdentifierResponseV1(dogeResponseV1.getDocumentId()), HttpStatus.OK);
    }

    @GetMapping(value = "/pdf/learnersevaluation/{UUID}")
    public ResponseEntity<List<IdentifierResponseV1>> getLearnersEvaluation(@PathVariable("UUID") UUID idCourse) throws Exception{
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(idCourse);
        if(!courseEntityOptional.isPresent()) throw new NotFoundException("Course with id "+idCourse+" not found");
        CourseEntity courseEntity = courseEntityOptional.get();
        List<IdentifierResponseV1> identifierResponseV1s = new ArrayList<>();
        if(courseEntity.getTeacherList() != null)
        {
            for(TeacherCourse teacherCourse : courseEntity.getTeacherList())
            {
                DogeResponseV1 dogeResponseV1 = dogeService.learnesEvaluation(courseEntity, teacherCourse);
                identifierResponseV1s.add(new IdentifierResponseV1(dogeResponseV1.getDocumentId()));
            }
        }

        return new ResponseEntity<>(identifierResponseV1s, HttpStatus.OK);
    }

    @GetMapping(value = "/pdf/teacheremployee/{UUID}")
    public ResponseEntity<List<IdentifierResponseV1>> getTeacherEmployee(@PathVariable("UUID") UUID idCourse) throws Exception{
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(idCourse);
        if(!courseEntityOptional.isPresent()) throw new NotFoundException("Course with id "+idCourse+" not found");
        CourseEntity courseEntity = courseEntityOptional.get();
        List<IdentifierResponseV1> identifierResponseV1s = new ArrayList<>();
        if(courseEntity.getTeacherList() != null)
        {
            for(TeacherCourse teacherCourse : courseEntity.getTeacherList())
            {
                DogeResponseV1 dogeResponseV1 = dogeService.teacherEmployee(courseEntity, teacherCourse);
                identifierResponseV1s.add(new IdentifierResponseV1(dogeResponseV1.getDocumentId()));
            }
        }

        return new ResponseEntity<>(identifierResponseV1s, HttpStatus.OK);
    }

    @GetMapping(value = "/pdf/assignment/withinvoice/{UUID}")
    public ResponseEntity<List<IdentifierResponseV1>> getAssignmentWithInvoice(@PathVariable("UUID") UUID idCourse) throws Exception{
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(idCourse);
        if(!courseEntityOptional.isPresent()) throw new NotFoundException("Course with id "+idCourse+" not found");
        CourseEntity courseEntity = courseEntityOptional.get();
        List<IdentifierResponseV1> identifierResponseV1s = new ArrayList<>();
        if(courseEntity.getTeacherList() != null)
        {
            for(TeacherCourse teacherCourse : courseEntity.getTeacherList())
            {
                DogeResponseV1 dogeResponseV1 = dogeService.assignmentWithInvoice(courseEntity, teacherCourse);
                identifierResponseV1s.add(new IdentifierResponseV1(dogeResponseV1.getDocumentId()));
            }
        }

        return new ResponseEntity<>(identifierResponseV1s, HttpStatus.OK);
    }

    @GetMapping(value = "/pdf/assignment/withholdingtax/{UUID}")
    public ResponseEntity<List<IdentifierResponseV1>> getAssignmentWithWithholdingTax(@PathVariable("UUID") UUID idCourse) throws Exception{
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(idCourse);
        if(!courseEntityOptional.isPresent()) throw new NotFoundException("Course with id "+idCourse+" not found");
        CourseEntity courseEntity = courseEntityOptional.get();
        List<IdentifierResponseV1> identifierResponseV1s = new ArrayList<>();
        if(courseEntity.getTeacherList() != null)
        {
            for(TeacherCourse teacherCourse : courseEntity.getTeacherList())
            {
                DogeResponseV1 dogeResponseV1 = dogeService.assignmentWithWithholdingTax(courseEntity, teacherCourse);
                identifierResponseV1s.add(new IdentifierResponseV1(dogeResponseV1.getDocumentId()));
            }
        }

        return new ResponseEntity<>(identifierResponseV1s, HttpStatus.OK);
    }

    @GetMapping(value = "/pdf/register/{UUID}")
    public ResponseEntity<IdentifierResponseV1> getRegister(@PathVariable("UUID") UUID idCourse) throws Exception{
        List<DogeResponseV1> dogeResponseV1 = dogeService.register(idCourse);
        List<IdentifierResponseV1> identifierResponseV1s = new ArrayList<>();
        String filecontent ="";

        List<byte[]> fileList = new ArrayList<>();
        for(DogeResponseV1 dogeResponseV11 : dogeResponseV1){

            DownloadResponseV1 downloadResponseV1 = filemanagerService.downloadFile(dogeResponseV11.getDocumentId());

            identifierResponseV1s.add(new IdentifierResponseV1(dogeResponseV11.getDocumentId()));
            byte[] decodedFile = Base64.getDecoder().decode(downloadResponseV1.getFileContent());
            fileList.add(decodedFile);

            filemanagerService.deleteFile(dogeResponseV11.getDocumentId());
        }

        ByteArrayOutputStream outputStream = mergePdf(fileList);

        filecontent = Base64.getEncoder().encodeToString(outputStream.toByteArray());

        outputStream.close();
        UploadRequestV1 uploadRequestV1 = new UploadRequestV1();
        UUID uuid = UUID.randomUUID();
        uploadRequestV1.setId(uuid);
        uploadRequestV1.setResourceId("1");
        uploadRequestV1.setResourceType("doge");
        uploadRequestV1.setBlobType("pdf");
        uploadRequestV1.setDescription("REGISTRO");
        uploadRequestV1.setFileContent(filecontent);
        uploadRequestV1.setFileName("Registro-Didattico-Completo.pdf");
        UploadResponseV1 uploadResponseV1 = filemanagerService.uploadFile(uploadRequestV1);

        System.out.println("sono dopo filemanager se ci arriva");

        return new ResponseEntity<>(new IdentifierResponseV1(uploadResponseV1.getId().toString()), HttpStatus.OK);
    }

    public ByteArrayOutputStream mergePdf(List<byte[]> files) throws IOException {

        PDFMergerUtility PDFmerger = new PDFMergerUtility();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PDFmerger.setDestinationStream(outputStream);
        List<PDDocument> pdDocuments = new LinkedList<>();

        for (byte[] file : files){

            PDDocument pdDocument = PDDocument.load(file);
            ((LinkedList<PDDocument>) pdDocuments).addLast(pdDocument);
            PDFmerger.addSource(new ByteArrayInputStream(file));
            pdDocument.close();
        }

        PDFmerger.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());

        for (PDDocument pdDocument : pdDocuments){
            System.out.println(pdDocument.getNumberOfPages());
           pdDocument.close();
        }

        return outputStream;
    }

    @GetMapping(value = "/pdf/tradeunionteaching/{UUID}")
    public ResponseEntity<List<IdentifierResponseV1>> getTradeUnionTeaching(@PathVariable("UUID") UUID idCourse) throws Exception{
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(idCourse);
        if(!courseEntityOptional.isPresent()) throw new NotFoundException("Course with id "+idCourse+" not found");
        CourseEntity courseEntity = courseEntityOptional.get();
        List<IdentifierResponseV1> identifierResponseV1s = new ArrayList<>();
        if(courseEntity.getTeacherList() != null)
        {
            for(TeacherCourse teacherCourse : courseEntity.getTeacherList())
            {
                DogeResponseV1 dogeResponseV1 = dogeService.tradeUnionTeaching(courseEntity, teacherCourse);
                identifierResponseV1s.add(new IdentifierResponseV1(dogeResponseV1.getDocumentId()));
            }
        }

        return new ResponseEntity<>(identifierResponseV1s, HttpStatus.OK);
    }

}
