package it.tcgroup.vilear.coursemanager.controller;

import io.swagger.annotations.*;
import it.tcgroup.vilear.coursemanager.controller.payload.request.PartnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PartnerResponseV1;
import it.tcgroup.vilear.coursemanager.service.AuthorizationService;
import it.tcgroup.vilear.coursemanager.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/coursemanager")
@Api("CourseManager")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private AuthorizationService authorizationService;

    /*INSERIMENTO PARTNER*/
    @PostMapping(value = "/partner",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="Insert Partner", notes = "Insert partner using info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = IdResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<IdResponseV1> postInsertPartner(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "Body of the Partner to be created", required = true)
            @RequestBody PartnerRequestV1 partnerInsertRequestV1) {

        authorizationService.checkAlive(userId);

        return new ResponseEntity<>( partnerService.insertPartner(partnerInsertRequestV1),HttpStatus.OK);
    }

    /*MODIFICA PARTNER*/
    @PutMapping(value = "/partner/{UUID_PARTNER}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Upload Partner", notes = "Upload partner using info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = PartnerResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<PartnerResponseV1> putModifyPartner(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "UUID that identifies the Partner to be modified", required = true)
            @PathVariable(name = "UUID_PARTNER") String idPartner,
            @ApiParam(value = "Updated body of the Partner", required = true)
            @RequestBody PartnerRequestV1 partnerUpdateRequest) {

        authorizationService.checkAlive(userId);

        return new ResponseEntity<>(partnerService.updatePartner(partnerUpdateRequest, UUID.fromString(idPartner)) ,HttpStatus.OK);
    }

    /*RECUPERO PARTNER TRAMITE ID*/
    @GetMapping(value = "/partner/{UUID_PARTNER}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Recover Partner", notes = "Returns a Partner using the UUID passed in the path")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = PartnerResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<PartnerResponseV1> getPartnerById(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "UUID of the Partner to be found", required = true)
            @PathVariable(name = "UUID_PARTNER") String idPartner) {

        authorizationService.checkAlive(userId);

        return new ResponseEntity<>(partnerService.getPartner(UUID.fromString(idPartner)), HttpStatus.OK);
    }

    /*MODIFICA PARZIALE PARTNER*/
    @PatchMapping(value = "/partner/{UUID_PARTNER}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Upload a part of the Partner", notes = "Update a part of the Partner using the info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = PartnerResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<PartnerResponseV1> patchPartner(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "UUID of the Partner", required = true)
            @PathVariable(name = "UUID_PARTNER") String idPartner,
            @ApiParam(value = "Some attributes of the body of the Partner to be modified", required = true)
            @RequestBody PartnerRequestV1 partnerPatchRequestV1) throws Exception {

        authorizationService.checkAlive(userId);

        return new ResponseEntity<>(partnerService.patchPartner(partnerPatchRequestV1, UUID.fromString(idPartner)), HttpStatus.OK);
    }

    /*PAGINAZIONE PARTNERS*/
    @GetMapping(value = "/partner",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get all partners", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = PaginationResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<PaginationResponseV1<PartnerResponseV1>> getPartnersPagination(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "Defines how many Partners can contain the single page", required = false)
            @RequestParam(value = "page_size", defaultValue = "20") Integer pageSize,
            @ApiParam(value = "Defines the page number to be displayed", required = false)
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "business_name", required = false) String businessName,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "company", required = false) Boolean company,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "manager_name", required = false) String managerName,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "accredited_ft", required = false) String accreditedFt,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "teacher_name", required = false) String teacherName,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "teacher_surname", required = false) String teacherSurname,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "teacher_professional_area", required = false) String teacherProfessionalArea,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "teacher_public_employee", required = false) String teacherPublicEmployee,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "city", required = false) String city,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "region", required = false) String region,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "province", required = false) String province ) {

        authorizationService.checkAlive(userId);

        return new ResponseEntity<>(partnerService.getPartnersPagination(page, pageSize, businessName, company, managerName, accreditedFt, teacherName, teacherSurname,
                teacherProfessionalArea, teacherPublicEmployee, city, region, province),HttpStatus.OK);
    }

    /*CANCELLAZIONE PARTNER*/
    @DeleteMapping( value = "/partner/{UUID_PARTNER}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity deletePartner(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "UUID of the Partner", required = true)
            @PathVariable(name = "UUID_PARTNER") String idPartner) {

        authorizationService.checkAlive(userId);

        partnerService.deletePartner(UUID.fromString(idPartner));

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
