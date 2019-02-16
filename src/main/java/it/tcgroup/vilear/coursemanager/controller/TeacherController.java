package it.tcgroup.vilear.coursemanager.controller;

import io.swagger.annotations.*;
import it.tcgroup.vilear.coursemanager.controller.payload.request.TeacherRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.TeacherResponseV1;
import it.tcgroup.vilear.coursemanager.service.TeacherService;
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
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /*INSERIMENTO TEACHER*/
    @PostMapping(value = "/teacher",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="Insert Teacher", notes = "Insert teacher using info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = IdResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<IdResponseV1> postInsertTeacher(
            @ApiParam(value = "Body of the Teacher to be created", required = true)
            @RequestBody TeacherRequestV1 teacherInsertRequestV1) {

        return new ResponseEntity<>( teacherService.insertTeacher(teacherInsertRequestV1),HttpStatus.OK);
    }

    /*MODIFICA TEACHER*/
    @PutMapping(value = "/teacher/{UUID_TEACHER}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Upload Teacher", notes = "Upload teacher usin ifno passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = TeacherResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<TeacherResponseV1> putModifyTeacher(
            @ApiParam(value = "UUID that identifies the Teacher to be modified", required = true)
            @PathVariable(name = "UUID_TEACHER") String idTeacher,
            @ApiParam(value = "Updated body of the Teacher", required = true)
            @RequestBody TeacherRequestV1 teacherUpdateRequest) {

        return new ResponseEntity<>(teacherService.updateTeacher(teacherUpdateRequest, UUID.fromString(idTeacher)) ,HttpStatus.OK);
    }

    /*RECUPERO TEACHER TRAMITE ID*/
    @GetMapping(value = "/teacher/{UUID_TEACHER}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Recover Teacher", notes = "Returns a Teacher using the UUID passed in the path")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = TeacherResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<TeacherResponseV1> getTeacherById(
            @ApiParam(value = "UUID of the Teacher to be found", required = true)
            @PathVariable(name = "UUID_TEACHER") String idTeacher) {

        return new ResponseEntity<>(teacherService.getTeacher(UUID.fromString(idTeacher)), HttpStatus.OK);
    }

    /*MODIFICA PARZIALE TEACHER*/
    @PatchMapping(value = "/teacher/{UUID_TEACHER}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Upload a part of the Teacher", notes = "Update a part of the Teacher using the info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = TeacherResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<TeacherResponseV1> patchTeacher(
            @ApiParam(value = "UUID of the Teacher", required = true)
            @PathVariable(name = "UUID_TEACHER") String idTeacher,
            @ApiParam(value = "Some attributes of the body of the Teacher to be modified", required = true)
            @RequestBody TeacherRequestV1 teacherPatchRequestV1) throws Exception {


        return new ResponseEntity<>(teacherService.patchTeacher(teacherPatchRequestV1, UUID.fromString(idTeacher)), HttpStatus.OK);
    }

    /*PAGINAZIONE TEACHERS*/
    @GetMapping(value = "/teacher",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get all teachers", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = PaginationResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<PaginationResponseV1<TeacherResponseV1>> getTeachersPagination(
            @ApiParam(value = "Defines how many Teachers can contain the single page", required = false)
            @RequestParam(value = "page_size", defaultValue = "20") Integer pageSize,
            @ApiParam(value = "Defines the page number to be displayed", required = false)
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "username", required = false) String username,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "name", required = false) String name,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "surname", required = false) String surname,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "phone", required = false) String phone,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "fiscal_code", required = false) String fiscalCode,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "date_of_birth", required = false) String dateOfBirth,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "birth_place", required = false) String birthPlace,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "email", required = false) String email,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "professional_area", required = false) String professionalArea,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "public_employee", required = false) Boolean publicEmployee,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "accredited_ft", required = false) Boolean accreditedFt,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "accredited_ft_code", required = false) String accreditedFtCode,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "authorized", required = false) Boolean authorized,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "professional_order_registration", required = false) Boolean professionalOrderRegistration,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "register", required = false) String register,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "vat_holder", required = false) Boolean vatHolder,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "sector", required = false) String sector,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "city", required = false) String city,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "region", required = false) String region,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "province", required = false) String province ) {

        return new ResponseEntity<>(teacherService.getTeachersPagination(page, pageSize, username, name, surname, phone, fiscalCode, dateOfBirth,
                birthPlace, email, professionalArea, publicEmployee, accreditedFt, accreditedFtCode, authorized,
                professionalOrderRegistration, register, vatHolder, sector, city, region, province),HttpStatus.OK);
    }

    /*CANCELLAZIONE TEACHER*/
    @DeleteMapping( value = "/teacher/{UUID_TEACHER}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity deleteTeacher(
            @ApiParam(value = "UUID of the Teacher", required = true)
            @PathVariable(name = "UUID_TEACHER") String idTeacher) {

        teacherService.deleteTeacher(UUID.fromString(idTeacher));

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
