package it.tcgroup.vilear.coursemanager.controller;

import io.swagger.annotations.*;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/coursemanager")
@Api("CourseManager")
public class CourseController {

    @Autowired
    private CourseService courseService;

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
            @ApiParam(value = "Body of the Course to be created", required = true)
            @RequestBody CourseRequestV1 courseInsertRequestV1) {

        return new ResponseEntity<>( courseService.insertCourse(courseInsertRequestV1), HttpStatus.OK);
    }
}
