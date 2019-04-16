package it.tcgroup.vilear.coursemanager.controller;

import io.swagger.annotations.*;
import it.tcgroup.vilear.coursemanager.common.exception.BadRequestException;
import it.tcgroup.vilear.coursemanager.common.exception.ForbiddenExcemption;
import it.tcgroup.vilear.coursemanager.common.validation.MessageCode;
import it.tcgroup.vilear.coursemanager.common.validation.RequestValidator;
import it.tcgroup.vilear.coursemanager.controller.payload.request.LearnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.UploadRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.LearnerResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.UploadResponseV1;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;
import it.tcgroup.vilear.coursemanager.service.AuthorizationService;
import it.tcgroup.vilear.coursemanager.service.LearnerService;
import it.tcgroup.vilear.coursemanager.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/coursemanager")
@Api("CourseManager")
public class LearnerController {

    @Autowired
    private LearnerService learnerService;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private ValidateService validateService;

    @Autowired
    private RequestValidator requestValidator;


    /*INSERIMENTO LEARNER REGISTRAZIONE*/
    @PostMapping(value = "/learner/registration",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="Insert Learner", notes = "Insert Learner using info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = IdResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<IdResponseV1> postInsertLearnerRegistration(
            @ApiParam(value = "Body of the Learner to be created", required = true)
            @RequestBody LearnerRequestV1 learnerInsertRequestV1) {

        validateService.requestValidatorLearner(learnerInsertRequestV1);

        return new ResponseEntity<>( learnerService.insertLearner(learnerInsertRequestV1), HttpStatus.OK);
    }

    /*INSERIMENTO LEARNER*/
    @PostMapping(value = "/learner",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="Insert Learner", notes = "Insert Learner using info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = IdResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<IdResponseV1> postInsertLearner(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "Body of the Learner to be created", required = true)
            @RequestBody LearnerRequestV1 learnerInsertRequestV1) {

        authorizationService.checkAlive(userId);

        validateService.requestValidatorLearner(learnerInsertRequestV1);

        return new ResponseEntity<>( learnerService.insertLearner(learnerInsertRequestV1), HttpStatus.OK);
    }

    /*AGGIORNA ID LEARNER*/
    @PatchMapping(value = "/learner/update/id")
    @ApiOperation(value="Learner update id", notes = "After confirmed registration, update id learner")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity patchLearnerId(
            @ApiParam(value = "UUID logged user", required = true)
            @RequestHeader(name = "id-user") String userId,
            @ApiParam(value = "Email logged user", required = false)
            @RequestHeader(name = "email-user") String emailUser) {

        learnerService.updateIdLearner(emailUser, userId);

        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    /*MODIFICA LEARNER*/
    @PutMapping(value = "/learner/{UUID_LEARNER}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Upload Learner", notes = "Upload Learner using info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = LearnerResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<LearnerResponseV1> putModifyLearner(
            @ApiParam(value = "UUID that identifies the Learner to be modified", required = true)
            @PathVariable(name = "UUID_LEARNER") String idLearner,
            @ApiParam(value = "Updated body of the learner", required = true)
            @RequestBody LearnerRequestV1 learnerUpdateRequest,
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId) {


        authorizationService.checkAlive(userId);

        validateService.requestValidatorLearner(learnerUpdateRequest);

        return new ResponseEntity<>(learnerService.updateLearner(learnerUpdateRequest, UUID.fromString(idLearner)) ,HttpStatus.OK);
    }

    /*RECUPERO LEARNER TRAMITE ID*/
    @GetMapping(value = "/learner/{UUID_LEARNER}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Recover Learner", notes = "Returns a Learner using the UUID passed in the path")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = LearnerResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<LearnerResponseV1> getLearnerById(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "UUID of the Learner to be founfd", required = true)
            @PathVariable(name = "UUID_LEARNER") String idLearner) {

        authorizationService.checkAlive(userId);

        return new ResponseEntity<>(learnerService.getLearner(UUID.fromString(idLearner)), HttpStatus.OK);
    }

    /*MODIFICA PARZIALE LEARNER*/
    @PatchMapping(value = "/learner/{UUID_LEARNER}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Upload a part of the Learner", notes = "Update a part of the Learner using the info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = LearnerResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<LearnerResponseV1> patchLearner(
            @ApiParam(value = "UUID of the Learner", required = true)
            @PathVariable(name = "UUID_LEARNER") String idLearner,
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "Some attributes of the body of the Learner to be modified", required = true)
            @RequestBody LearnerRequestV1 learnerPatchRequestV1) throws Exception {

        authorizationService.checkAlive(userId);

        validateService.requestValidatorPatchLearner(learnerPatchRequestV1);

        return new ResponseEntity<>(learnerService.patchLearner(learnerPatchRequestV1, UUID.fromString(idLearner)), HttpStatus.OK);
    }

    /*CANCELLAZIONE LEARNER*/
    @DeleteMapping( value = "/learner/{UUID_LEARNER}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity deleteLearner(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "UUID of the Learner", required = true)
            @PathVariable(name = "UUID_LEARNER") String idLearner) {

        authorizationService.checkAlive(userId);

        learnerService.deleteLearner(UUID.fromString(idLearner));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*PAGINAZIONE LEARNERS*/
    @GetMapping(value = "/learner",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get all learners", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = PaginationResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<PaginationResponseV1<LearnerResponseV1>> getLearnersPagination(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "Defines how many Learners can contain the single page", required = false)
            @RequestParam(value = "page_size", defaultValue = "20") Integer pageSize,
            @ApiParam(value = "Defines the page number to be displayed", required = false)
            @RequestParam(value = "page", defaultValue = "1") Integer page,
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
            @RequestParam(value = "degree_of_studies", required = false) String degreeOfStudies,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "course_of_study", required = false) String courseOfStudy,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "city", required = false) String city,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "region", required = false) String region,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "province", required = false) String province ) {

        authorizationService.checkAlive(userId);

        return new ResponseEntity<>(learnerService.getLearnersPagination(page, pageSize, name, surname, phone, fiscalCode, dateOfBirth,
                birthPlace, email, degreeOfStudies, courseOfStudy, city, region, province),HttpStatus.OK);
    }

    /*INSERIMENTO CURRICULUM*/
    @PostMapping(value = "/learner/curriculum/{UUID_LEARNER}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="Insert Learner Curriculum", notes = "Insert Learner Curriculum using info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = UploadResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Attachment> postInsertLearnerCurriculum(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "Body of the Learner Curriculum to be upload on filemanager", required = true)
            @RequestBody UploadRequestV1 uploadRequest,
            @ApiParam(value = "UUID of the Learner", required = true)
            @PathVariable(name = "UUID_LEARNER") String idLearner) throws IOException {

        authorizationService.checkAlive(userId);

        return new ResponseEntity<>( learnerService.addLearnerCurriculum(uploadRequest, UUID.fromString(idLearner)), HttpStatus.OK);
    }

    /*ELIMINAZIONE CURRICULUM*/
    @DeleteMapping(value = "/learner/attachment/{UUID_LEARNER}/{UUID_ATTACHMENT}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="Delete Learner Attachment with specific UUID", notes = "Delete Learner Attachment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UploadResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity deleteLearnerCurriculum(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "UUID of the Learner", required = true)
            @PathVariable(name = "UUID_LEARNER") String idLearner,
            @ApiParam(value = "UUID of the Attachment to delete", required = true)
            @PathVariable(name = "UUID_ATTACHMENT") String idAttachment) {

        authorizationService.checkAlive(userId);

        learnerService.deleteLearnerCurriculum(UUID.fromString(idLearner), UUID.fromString(idAttachment));

        return new ResponseEntity(HttpStatus.OK);
    }

    /*CANDIDATURA LEARNER A CORSO*/
    @PostMapping(value = "/learner/candidate/{UUID_LEARNER}/{UUID_COURSE}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="Candidate Learner", notes = "Candidate Learner using info passed in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity postCandidateLearner(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") UUID userId,
            @ApiParam(value = "UUID of the Learner", required = true)
            @PathVariable(name = "UUID_LEARNER") String idLearner,
            @ApiParam(value = "UUID of the Course", required = true)
            @PathVariable(name = "UUID_COURSE") String idCourse) {

        authorizationService.checkAlive(userId);

        if(learnerService.candidateLearnerToCourse(UUID.fromString(idLearner),UUID.fromString(idCourse)))
            return new ResponseEntity(HttpStatus.OK);

        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

}
