package it.tcgroup.vilear.coursemanager.controller;

import io.swagger.annotations.*;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.UploadRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.UploadResponseV1;
import it.tcgroup.vilear.coursemanager.entity.enumerated.*;
import it.tcgroup.vilear.coursemanager.service.AuthorizationService;
import it.tcgroup.vilear.coursemanager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/coursemanager")
@Api("CourseManager")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private AuthorizationService authorizationService;

    /*INSERIMENTO COURSE*/
    @PostMapping(value = "/course",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="Insert Course", notes = "Course branch using info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = IdResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<IdResponseV1> postInsertCourse(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "Body of the Course to be created", required = true)
            @RequestBody CourseRequestV1 courseInsertRequestV1) {

        authorizationService.checkAlive(userId);

        return new ResponseEntity<>( courseService.insertCourse(courseInsertRequestV1), HttpStatus.OK);
    }

    /*RECUPERO COURSE TRAMITE ID*/
    @GetMapping(value = "/course/{UUID_COURSE}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Recover Course", notes = "Returns a Course using the UUID passed in the path")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = CourseResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<CourseResponseV1> getCourseById(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "UUID of the Course to be found", required = true)
            @PathVariable(name = "UUID_COURSE") String idCourse) {

        authorizationService.checkAlive(userId);

        return new ResponseEntity<>(courseService.getCourse(UUID.fromString(idCourse)), HttpStatus.OK);
    }

    /*MODIFICA COURSE*/
    @PutMapping(value = "/course/{UUID_COURSE}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Upload Course", notes = "Upload Course using info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = CourseResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<CourseResponseV1> putModifyCourse(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "UUID that identifies the Course to be modified", required = true)
            @PathVariable(name = "UUID_COURSE") String idCourse,
            @ApiParam(value = "Updated body of the course", required = true)
            @RequestBody CourseRequestV1 courseUpdateRequest) {

        authorizationService.checkAlive(userId);

        return new ResponseEntity<>(courseService.updateCourse(courseUpdateRequest, UUID.fromString(idCourse)) ,HttpStatus.OK);
    }

    /*MODIFICA PARZIALE COURSE*/
    @PatchMapping(value = "/course/{UUID_COURSE}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Upload a part of the Course", notes = "Update a part of the Course using the info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = CourseResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<CourseResponseV1> patchCourse(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "UUID of the Course", required = true)
            @PathVariable(name = "UUID_COURSE") String idCourse,
            @ApiParam(value = "Some attributes of the body of the Course to be modified", required = true)
            @RequestBody CourseRequestV1 coursePatchRequestV1) throws Exception {

        authorizationService.checkAlive(userId);

        return new ResponseEntity<>(courseService.patchCourse(coursePatchRequestV1, UUID.fromString(idCourse)), HttpStatus.OK);
    }

    /*CANCELLAZIONE COURSE*/
    @DeleteMapping( value = "/course/{UUID_COURSE}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity deleteCourse(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "UUID of the Course", required = true)
            @PathVariable(name = "UUID_COURSE") String idCourse) {

        authorizationService.checkAlive(userId);

        courseService.deleteCourse(UUID.fromString(idCourse));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*PAGINAZIONE COURSES*/
    @GetMapping(value = "/course",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get all courses", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = PaginationResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<PaginationResponseV1<CourseResponseV1>> getLearnersPagination(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "Defines how many Courses can contain the single page", required = false)
            @RequestParam(value = "page_size", defaultValue = "20") Integer pageSize,
            @ApiParam(value = "Defines the page number to be displayed", required = false)
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "course_title", required = false) String courseTitle,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "contents_area", required = false) String contentsArea,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "learner_type", required = false) String learnerType,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "supply_modality", required = false) String supplyModality,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "payment_modality", required = false) String paymentModality,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "founds_type", required = false) String foundsType,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "course_start_date", required = false) String courseStartDate,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "part_full_time", required = false) String partFullTime,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "course_code", required = false) String courseCode,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "business_name", required = false) String businessName,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "course_type", required = false) String courseType,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "special_initiatives", required = false) String specialInitiatives) {

        authorizationService.checkAlive(userId);

        return new ResponseEntity<>(courseService.getCoursePagination(page, pageSize, courseTitle,
                contentsArea,
                learnerType,
                supplyModality,
                paymentModality,
                foundsType,
                courseStartDate,
                partFullTime,
                courseCode,
                businessName,
                courseType,
                specialInitiatives),HttpStatus.OK);
    }

    /*INSERIMENTO LOGO*/
    @PostMapping(value = "/course/logo/{UUID_COURSE}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="Insert Learner Curriculum", notes = "Insert Logo of the Course using info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = CourseResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<CourseResponseV1> postInsertCourseLogo(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "Body of the Attachment to be upload on filemanager", required = true)
            @RequestBody UploadRequestV1 uploadRequest,
            @ApiParam(value = "UUID of the Course", required = true)
            @PathVariable(name = "UUID_COURSE") String idCourse) throws IOException {

        authorizationService.checkAlive(userId);

        return new ResponseEntity<>( courseService.addCourseLogo(uploadRequest, UUID.fromString(idCourse)), HttpStatus.OK);
    }

    /*ELIMINAZIONE LOGO*/
    @DeleteMapping(value = "/course/logo/{UUID_COURSE}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="Delete Logo of the Course", notes = "Delete Logo of the Course")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UploadResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity deleteCourseLogo(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "UUID of the Course", required = true)
            @PathVariable(name = "UUID_COURSE") String idCourse) {

        authorizationService.checkAlive(userId);

        courseService.deleteCourseLogo(UUID.fromString(idCourse));
        return new ResponseEntity(HttpStatus.OK);
    }

    /*INSERIMENTO ALLEGATI*/
    @PostMapping(value = "/course/attachments/{UUID_COURSE}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="Insert Attachments", notes = "Insert Attachments of the Course using info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = CourseResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<CourseResponseV1> postInsertCourseAttachments(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "Body of the Attachments to be upload on filemanager", required = true)
            @RequestBody List<UploadRequestV1> uploadRequestList,
            @ApiParam(value = "UUID of the Course", required = true)
            @PathVariable(name = "UUID_COURSE") String idCourse) throws IOException {

        authorizationService.checkAlive(userId);

        return new ResponseEntity<>( courseService.addCourseAttachments(uploadRequestList, UUID.fromString(idCourse)), HttpStatus.OK);
    }

    /*ELIMINAZIONE ALLEGATI*/
    @DeleteMapping(value = "/course/attachments/{UUID_COURSE}/{LIST_OF_ID_ATTACHMENT}")
    @ApiOperation(value="Delete Attachments", notes = "Delete Attachments")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UploadResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity deleteCourseAttachments(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "UUID of the Course", required = true)
            @PathVariable(name = "UUID_COURSE") String idCourse,
            @ApiParam(value = "List of UUID Attacments", required = true)
            @PathVariable(name = "LIST_OF_ID_ATTACHMENT") String[] idStringAttachmentList) {

        authorizationService.checkAlive(userId);

        List<UUID> idAttachmentList = new LinkedList<>();
        for (String att : idStringAttachmentList){
            idAttachmentList.add(UUID.fromString(att));
        }

        courseService.deleteCourseAttachments(UUID.fromString(idCourse), idAttachmentList);
        return new ResponseEntity(HttpStatus.OK);
    }

    /*CAMBIO STATO CORSO*/
    @PatchMapping(value = "/course/public/{UUID_COURSE}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Upload Course Status", notes = "Update the course status using info passed in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = CourseResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<CourseResponseV1> patchCourseStatus(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "UUID of the Course", required = true)
            @PathVariable(name = "UUID_COURSE") String idCourse) throws IOException {

        authorizationService.checkAlive(userId);

        return new ResponseEntity<>(courseService.patchCourseStatus(UUID.fromString(idCourse)), HttpStatus.OK);
    }

}
