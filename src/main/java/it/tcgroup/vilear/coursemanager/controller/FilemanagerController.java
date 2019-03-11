package it.tcgroup.vilear.coursemanager.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.tcgroup.vilear.coursemanager.controller.payload.request.UploadRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.UploadResponseV1;
import it.tcgroup.vilear.coursemanager.service.FilemanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/coursemanager")
public class FilemanagerController {

    @Autowired
    private FilemanagerService filemanagerService;

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
}
