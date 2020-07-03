package it.tcgroup.vilear.coursemanager.controller;

import io.swagger.annotations.*;
import it.tcgroup.vilear.coursemanager.controller.payload.request.BranchRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.LearnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.BranchResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.LearnerResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.service.BranchService;
import it.tcgroup.vilear.coursemanager.service.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value="/api/v1/coursemanager")
@Api("CourseManager")
public class LearnerController {

    @Autowired
    private LearnerService learnerService;


    /*INSERIMENTO Learner REGISTRAZIONE*/
    @PostMapping(value = "/learner/registration",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="Insert Learner", notes = "Insert learner using info passed in the body")
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

        return new ResponseEntity<>( learnerService.insertLearner(learnerInsertRequestV1),HttpStatus.CREATED);
    }



    /*RECUPERO Learner TRAMITE ID*/
    @GetMapping(value = "/learner/{UUID_BRANCH}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Recover Learner", notes = "Returns a Learner using the UUID passed in the path")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = BranchResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<LearnerResponseV1> getLearnerById(
            @ApiParam(value = "UUID of the Learner to be found", required = true)
            @PathVariable(name = "UUID_BRANCH") String idLearner) {


        return new ResponseEntity<>(learnerService.getLearner(UUID.fromString(idLearner)), HttpStatus.OK);
    }


    /*Recupera tutti i learner dalla tabella learner*/
    @GetMapping(value = "/learner/getAll",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Recover All Learner", notes = "Returns all Learner")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = BranchResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<List<LearnerResponseV1>> getAllLearner() {

        return new ResponseEntity<>(learnerService.getAllLearner(), HttpStatus.OK);
    }

    /*Delete record with the passed id*/
    @DeleteMapping( value = "/learner/{UUID_BRANCH}",
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
            @ApiParam(value = "UUID of the Learner to be deleted", required = true)
            @PathVariable(name = "UUID_BRANCH") String idLearner
    ){
        learnerService.deleteLeraner(UUID.fromString(idLearner));
        return new ResponseEntity<>(HttpStatus.OK);
    }



    /*modifica e quindi in caso di richieste con campi null andiamo a sovrascrivere il valore nell'entity*/
    @PutMapping(value = "/learner/{UUID_LEARNER}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Upload Learner", notes = "Upload Learner using info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = BranchResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<LearnerResponseV1> putModifyLearner(
            @ApiParam(value = "UUID that identifies the Learner to be modified", required = true)
            @PathVariable(name = "UUID_LEARNER") String idLearner,
            @ApiParam(value = "Updated body of the Learner", required = true)
            @RequestBody LearnerRequestV1 learnerUpdateRequest) {


        return new ResponseEntity<>(learnerService.updateLearner(learnerUpdateRequest, UUID.fromString(idLearner)) ,HttpStatus.OK);
    }


    /*MODIFICA PARZIALE BRANCH*//*patchbyid*/
    @PatchMapping(value = "/learner/{UUID_LEARNER}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Upload a part of the Learner", notes = "Update a part of the learner using the info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = BranchResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<LearnerResponseV1> patchLearner(
            @ApiParam(value = "UUID of the Learner", required = true)
            @PathVariable(name = "UUID_LEARNER") String idLearner,
            @ApiParam(value = "Some attributes of the body of the Learner to be modified", required = true)
            @RequestBody LearnerRequestV1 learnerPatchRequestV1) throws Exception {

        return new ResponseEntity<>(learnerService.patchLearner(learnerPatchRequestV1, UUID.fromString(idLearner)), HttpStatus.OK);
    }


    /*PAGINATION*/

    @GetMapping(value = "/learner/pagination",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get all filiali", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = PaginationResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<PaginationResponseV1<LearnerResponseV1>> getLearnersPagination(
            @ApiParam(value = "Defines how many Learner can contain the single page", required = false)
            @RequestParam(value = "page_size", defaultValue = "3") Integer page_size,
            @ApiParam(value = "Defines the page number to be displayed", required = false)
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "name", required = false) String name,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "surname", required = false) String surname,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "fiscal_code", required = false) String fiscalCode,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "date_of_birth", required = false) String dateOfBirth,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "birth_place", required = false) String birthPlace,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "email", required = false) String email,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "phone", required = false) String phone,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "note", required = false) String note,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "course_of_study", required = false) String courseOfStudy,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "degree_of_studies", required = false) String degreeOfStudies,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "residential_city", required = false) String residentialCity,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "residential_region", required = false) String residentialRegion,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "residential_province", required = false) String residentialProvince,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "residential_number", required = false) String residentialNumber,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "residential_nation", required = false) String residentialNation,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "residential_street", required = false) String residentialStreet,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "domicile_city", required = false) String domicileCity,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "domicile_region", required = false) String domicileRegion,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "domicile_province", required = false) String domicileProvince,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "domicile_number", required = false) String domicileNumber,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "domicile_nation", required = false) String domicileNation,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "domicile_street", required = false) String domicileStreet,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "domicile_equals_residential", required = false) Boolean domicileEqualsResidential
            ) throws ParseException {


        return new ResponseEntity<>(learnerService.getLearnersPagination( page, page_size, name, surname,
                 fiscalCode,dateOfBirth, birthPlace, email, phone,
                 note, degreeOfStudies, courseOfStudy, residentialNation,
                 residentialRegion, residentialProvince, residentialCity,
                 residentialStreet, residentialNumber, domicileNation,
                 domicileRegion, domicileProvince, domicileCity,
                 domicileStreet, domicileNumber, domicileEqualsResidential),HttpStatus.OK);
    }


}
