package it.tcgroup.vilear.coursemanager.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.tcgroup.vilear.coursemanager.controller.payload.request.UploadRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.DownloadResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdentifierResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.UploadResponseV1;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;
import it.tcgroup.vilear.coursemanager.repository.CourseEMRepository;
import it.tcgroup.vilear.coursemanager.repository.CourseRepository;
import it.tcgroup.vilear.coursemanager.service.FilemanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/coursemanager")
public class FilemanagerController {

    @Autowired
    private FilemanagerService filemanagerService;

    @Autowired
    private DogeController dogeController;

    @Autowired
    private CourseRepository courseRepository;

    /*INSERIMENTO ATTACHMENT*/
    @PostMapping(value = "/attachment",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="Insert Attachment", notes = "Insert Attachment using info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = UploadResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<UploadResponseV1> postUploadAttachment(
            @ApiParam(value = "Body of the Attachment to be created", required = true)
            @RequestBody UploadRequestV1 learnerInsertRequestV1) throws IOException {

        return new ResponseEntity<>(filemanagerService.uploadFile(learnerInsertRequestV1),HttpStatus.OK);
    }

    @PostMapping(value = "/register/file/{UUID_COURSE}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = UploadResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Attachment> createFileRegistro (@PathVariable("UUID_COURSE") UUID idCorso ) throws Exception {

        // chiamo Doge per la generazione del servizio di registro
        ResponseEntity<IdentifierResponseV1> response =dogeController.getRegister(idCorso);

        IdentifierResponseV1 dogeParseResponse = response.getBody();
        String idFileManager = dogeParseResponse.getId();


        DownloadResponseV1 downloadFileManager = filemanagerService.getFileDoge(idFileManager +" 313");

        Attachment attachment = new Attachment();
        attachment.setBlobName(idFileManager + ".pdf");
        attachment.setBlobSize(downloadFileManager.getFileSize());
        attachment.setCreatedAt(new Date());
        attachment.setFileManagerId(idFileManager);
        attachment.setDescription(downloadFileManager.getDescription());
        attachment.setFileManagerName(downloadFileManager.getFileName());
        attachment.setResourceType("register");
        attachment.setMimeType("application/pdf");

        CourseEntity courseEntity = courseRepository.getOne(idCorso);

        List<Attachment> attachmentList = courseEntity.getDocumentsAttachment();
        if(attachmentList == null){
            attachmentList = new LinkedList<>();
        }
        attachmentList.add(attachment);
        courseEntity.setDocumentsAttachment(attachmentList);

        courseRepository.save(courseEntity);

        return new ResponseEntity<>(attachment,HttpStatus.OK);
    }
}