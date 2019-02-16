package it.tcgroup.vilear.coursemanager.controller;

import io.swagger.annotations.*;
import it.tcgroup.vilear.coursemanager.controller.payload.request.BranchRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.BranchResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.service.BranchService;
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
public class BranchController {

    @Autowired
    private BranchService branchService;

    /*INSERIMENTO BRANCH*/
    @PostMapping(value = "/branch",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="Insert Branch", notes = "Insert branch using info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = IdResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<IdResponseV1> postInsertBranch(
            @ApiParam(value = "Body of the Branch to be created", required = true)
            @RequestBody BranchRequestV1 branchInsertRequestV1) {

        return new ResponseEntity<>( branchService.insertBranch(branchInsertRequestV1), HttpStatus.OK);
    }

    /*MODIFICA BRANCH*/
    @PutMapping(value = "/branch/{UUID_BRANCH}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Upload Branch", notes = "Upload branch using info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = BranchResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<BranchResponseV1> putModifyBranch(
            @ApiParam(value = "UUID that identifies the Branch to be modified", required = true)
            @PathVariable(name = "UUID_BRANCH") String idDocente,
            @ApiParam(value = "Updated body of the Branch", required = true)
            @RequestBody BranchRequestV1 branchUpdateRequest) {

        return new ResponseEntity<>(branchService.updateBranch(branchUpdateRequest, UUID.fromString(idDocente)) ,HttpStatus.OK);
    }

    /*RECUPERO BRANCH TRAMITE ID*/
    @GetMapping(value = "/branch/{UUID_BRANCH}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Recover Branch", notes = "Returns a Branch using the UUID passed in the path")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = BranchResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<BranchResponseV1> getBranchById(
            @ApiParam(value = "UUID of the Branch to be founfd", required = true)
            @PathVariable(name = "UUID_BRANCH") String idBranch) {

        return new ResponseEntity<>(branchService.getBranch(UUID.fromString(idBranch)), HttpStatus.OK);
    }

    /*MODIFICA PARZIALE BRANCH*/
    @PatchMapping(value = "/branch/{UUID_BRANCH}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Upload a part of the Branch", notes = "Update a part of the branch using the info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = BranchResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<BranchResponseV1> patchBranch(
            @ApiParam(value = "UUID of the Branch", required = true)
            @PathVariable(name = "UUID_BRANCH") String idBranch,
            @ApiParam(value = "Some attributes of the body of the Branch to be modified", required = true)
            @RequestBody BranchRequestV1 branchPatchRequestV1) throws Exception {

        return new ResponseEntity<>(branchService.patchBranch(branchPatchRequestV1, UUID.fromString(idBranch)), HttpStatus.OK);
    }

    /*CANCELLAZIONE BRANCH*/
    @DeleteMapping( value = "/branch/{UUID_BRANCH}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity deleteBranch(
            @ApiParam(value = "UUID of the Branch", required = true)
            @PathVariable(name = "UUID_BRANCH") String idBranch) {

        branchService.deleteBranch(UUID.fromString(idBranch));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*PAGINAZIONE BRANCH*/
    @GetMapping(value = "/branch",
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
    public ResponseEntity<PaginationResponseV1<BranchResponseV1>> getBranchesPagination(
            @ApiParam(value = "Defines how many Discenti can contain the single page", required = false)
            @RequestParam(value = "page_size", defaultValue = "20") Integer page_size,
            @ApiParam(value = "Defines the page number to be displayed", required = false)
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "username", required = false) String username,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "name", required = false) String name,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "email", required = false) String email,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "right_of_access_to_the_courses", required = false) String rightOfAccessToTheCourses,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "super", required = false) Boolean superBranch,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "city", required = false) String city,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "region", required = false) String region,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "province", required = false) String province ) {

        return new ResponseEntity<>(branchService.getBranchesPagination(page, page_size, username, name, email, rightOfAccessToTheCourses, superBranch, city, region, province),HttpStatus.OK);
    }


}
