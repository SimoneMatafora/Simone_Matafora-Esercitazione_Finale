package it.tcgroup.vilear.coursemanager.controller;

import com.itextpdf.text.DocumentException;
import io.swagger.annotations.Api;
import it.tcgroup.vilear.coursemanager.common.exception.NotFoundException;
import it.tcgroup.vilear.coursemanager.controller.payload.request.UploadRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.DogeResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.DownloadResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdentifierResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.UploadResponseV1;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.PlacementCourse;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.TeacherCourse;
import it.tcgroup.vilear.coursemanager.repository.CourseRepository;
import it.tcgroup.vilear.coursemanager.service.DogeService;
import it.tcgroup.vilear.coursemanager.service.FilemanagerService;
import org.apache.commons.*;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
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

    @GetMapping(value = "/pdf/certificate/{UUID}")
    public ResponseEntity<List<IdentifierResponseV1>> getCertificates(@PathVariable("UUID") UUID idCourse) throws Exception{
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(idCourse);
        if(!courseEntityOptional.isPresent()) throw new NotFoundException("Course with id "+idCourse+" not found");
        CourseEntity courseEntity = courseEntityOptional.get();
        List<IdentifierResponseV1> identifierResponseV1s = new ArrayList<>();
        if(courseEntity.getPlacementList() != null)
        {
            for(PlacementCourse placementCourse : courseEntity.getPlacementList())
            {
                if(placementCourse.getLearner() != null) {
                    DogeResponseV1 dogeResponseV1 = dogeService.learnerCertificate(courseEntity, placementCourse.getLearner());
                    identifierResponseV1s.add(new IdentifierResponseV1(dogeResponseV1.getDocumentId()));
                }
            }
        }

        return new ResponseEntity<>(identifierResponseV1s, HttpStatus.OK);
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
    public ResponseEntity<UploadResponseV1> getRegister(@PathVariable("UUID") UUID idCourse) throws Exception{
        List<DogeResponseV1> dogeResponseV1 = dogeService.register(idCourse);
        List<IdentifierResponseV1> identifierResponseV1s = new ArrayList<>();
        String filecontent ="";
        List<File> fileList = new LinkedList<>();
        List<PDDocument> pdDocuments = new LinkedList<>();
        int i = 1;
        for(DogeResponseV1 dogeResponseV11 : dogeResponseV1){
            DownloadResponseV1 downloadResponseV1 = filemanagerService.downloadFile(dogeResponseV11.getDocumentId());
            identifierResponseV1s.add(new IdentifierResponseV1(dogeResponseV11.getDocumentId()));
            String filename = "register-part-"+i;
            String path = "temp/"+filename+".pdf";
            byte[] decodedFile = Base64.getDecoder().decode(downloadResponseV1.getFileContent());
            FileUtils.writeByteArrayToFile(new File(path), decodedFile);

            File file1 = new File(path);
            PDDocument pdDocument = PDDocument.load(new File(path));
            fileList.add(file1);
            ((LinkedList<PDDocument>) pdDocuments).addLast(pdDocument);
            i++;
        }

        mergePdf(fileList, idCourse);
        String path = "temp/Registro-Didattico-Completo-"+idCourse+".pdf";
        File file3 = new File(path);
        byte[] byteFile = FileUtils.readFileToByteArray(file3);

        filecontent = Base64.getEncoder().encodeToString(byteFile);

        UploadRequestV1 uploadRequestV1 = new UploadRequestV1();
        UUID uuid = UUID.randomUUID();
        uploadRequestV1.setId(uuid);
        uploadRequestV1.setResourceId("1");
        uploadRequestV1.setResourceType("doge");
        uploadRequestV1.setBlobType("pdf");
        uploadRequestV1.setDescription("Registro didattico completo");
        uploadRequestV1.setFileContent(filecontent);
        uploadRequestV1.setFileName("Registro-Didattico-Completo.pdf");
        UploadResponseV1 uploadResponseV1 = filemanagerService.uploadFile(uploadRequestV1);
        return new ResponseEntity<>(uploadResponseV1, HttpStatus.OK);
    }

    public void mergePdf(List<File> files, UUID idCourse) throws IOException {
        PDFMergerUtility PDFmerger = new PDFMergerUtility();
        String path = "temp/Registro-Didattico-Completo-"+idCourse+".pdf";
        PDFmerger.setDestinationFileName(path);
        List<PDDocument> pdDocuments = new LinkedList<>();
        for (File file : files){
            PDDocument pdDocument = PDDocument.load(new File(file.getPath()));
            ((LinkedList<PDDocument>) pdDocuments).addLast(pdDocument);
            PDFmerger.addSource(file);
        }
        PDFmerger.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
        for (PDDocument pdDocument : pdDocuments){
           pdDocument.close();
        }

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
