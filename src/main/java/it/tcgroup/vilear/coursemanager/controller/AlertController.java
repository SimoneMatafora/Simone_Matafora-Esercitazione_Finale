package it.tcgroup.vilear.coursemanager.controller;

import io.swagger.annotations.*;
import it.tcgroup.vilear.coursemanager.adapter.AlertAdapter;
import it.tcgroup.vilear.coursemanager.controller.payload.request.BranchRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.AlertResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.BranchResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.service.AlertService;
import it.tcgroup.vilear.coursemanager.service.AuthorizationService;
import it.tcgroup.vilear.coursemanager.service.BranchService;
import it.tcgroup.vilear.coursemanager.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/coursemanager")
@Api("Alert")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private AlertAdapter alertAdapter;

    /*MODIFICA STATO DI UN ALERT*/
    @PatchMapping(value = "/alert/{UUID_ALERT}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Update status of an Alert", notes = "Update status of an Alert")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = AlertResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<AlertResponseV1> patchAlertStatus(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "UUID of the Branch", required = true)
            @PathVariable(name = "UUID_ALERT") String idAlert) throws Exception {

        authorizationService.checkAlive(userId);

        return new ResponseEntity<>(alertAdapter.adptAlertToAlertResponse(alertService.patchAlertStatus(UUID.fromString(idAlert))), HttpStatus.OK);
    }

    /*CANCELLAZIONE ALERT*/
    /*@DeleteMapping( value = "/alert/{UUID_ALERT}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity deleteAlert(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "UUID of the Branch", required = true)
            @PathVariable(name = "UUID_ALERT") String idAlert) {

        authorizationService.checkAlive(userId);

        alertService.deleteAlert(UUID.fromString(idAlert));

        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    /*PAGINAZIONE ALEFRT*/
   /* @GetMapping(value = "/alert",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get all alerts", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = PaginationResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<PaginationResponseV1<AlertResponseV1>> getAlertsPagination(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "Defines how many Discenti can contain the single page", required = false)
            @RequestParam(value = "page_size", defaultValue = "20") Integer page_size,
            @ApiParam(value = "Defines the page number to be displayed", required = false)
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "course_name", required = false) String courseName,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "course_code", required = false) String courseCode,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "start_date", required = false) String startDate,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "status", required = false) Boolean status,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "active", required = false) Boolean active) {

        authorizationService.checkAlive(userId);

        return new ResponseEntity<>(alertService.getAlertsPagination(page, page_size, courseName, courseCode, startDate, status, active),HttpStatus.OK);
    }*/


}
