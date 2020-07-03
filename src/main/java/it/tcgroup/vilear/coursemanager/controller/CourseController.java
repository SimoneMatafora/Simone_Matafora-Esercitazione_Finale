package it.tcgroup.vilear.coursemanager.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import it.tcgroup.vilear.coursemanager.controller.payload.request.LearnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.service.CourseService;
import it.tcgroup.vilear.coursemanager.service.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.UUID;

/*
@RestController
@CrossOrigin
@RequestMapping(value="/api/v1/coursemanager")
@Api("CourseManager")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/course/registration",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

    public ResponseEntity<IdResponseV1> postInsertCourseRegistration(
            @ApiParam(value = "Id of the inserting user", required=true)
            @RequestHeader(name="id_creator") UUID idCreator
            @ApiParam(value = "Body of the course to be created", required = true)
            @RequestBody CourseRequestV1 courseRequest) {

        return new ResponseEntity<>( courseService.insertLearner(courseRequest), HttpStatus.OK);
    }



}
*/