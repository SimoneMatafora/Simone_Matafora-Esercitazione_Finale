package it.tcgroup.vilear.coursemanager.controller;

import io.swagger.annotations.Api;
import it.tcgroup.vilear.coursemanager.common.exception.NotFoundException;
import it.tcgroup.vilear.coursemanager.controller.payload.response.DogeResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdentifierResponseV1;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.PlacementCourse;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.TeacherCourse;
import it.tcgroup.vilear.coursemanager.repository.CourseRepository;
import it.tcgroup.vilear.coursemanager.service.DogeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1")
@Api("Doge")
public class DogeController {
    @Autowired
    private DogeService dogeService;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(value = "/pdf/certificate/{UUID}")
    public ResponseEntity<List<IdentifierResponseV1>> getCertificates(@PathVariable("UUID") UUID idCourse) throws Exception{
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(idCourse);
        if(!courseEntityOptional.isPresent()) throw new NotFoundException("Course with id "+idCourse+" not found");
        CourseEntity courseEntity = courseEntityOptional.get();
        List<IdentifierResponseV1> identifierResponseV1s = new ArrayList<>();
        if(courseEntity.getPlacementList() != null)
        {
            for(PlacementCourse placementCourse : courseEntity.getPlacementList())
            {
                if(placementCourse.getLearner() != null) {
                    DogeResponseV1 dogeResponseV1 = dogeService.learnerCertificate(courseEntity, placementCourse.getLearner());
                    identifierResponseV1s.add(new IdentifierResponseV1(dogeResponseV1.getDocumentId()));
                }
            }
        }

        return new ResponseEntity<>(identifierResponseV1s, HttpStatus.OK);
    }

    @GetMapping(value = "/pdf/inailcomunication/{UUID}")
    public ResponseEntity<IdentifierResponseV1> getInailComunication(@PathVariable("UUID") UUID idCourse) throws Exception{
        DogeResponseV1 dogeResponseV1 = dogeService.inailComunication(idCourse);
        return new ResponseEntity<>(new IdentifierResponseV1(dogeResponseV1.getDocumentId()), HttpStatus.OK);
    }

    @GetMapping(value = "/pdf/ticketdelivery/{UUID}")
    public ResponseEntity<IdentifierResponseV1> getTicketDelivery(@PathVariable("UUID") UUID idCourse) throws Exception{
        DogeResponseV1 dogeResponseV1 = dogeService.ticketDelivery(idCourse);
        return new ResponseEntity<>(new IdentifierResponseV1(dogeResponseV1.getDocumentId()), HttpStatus.OK);
    }

    @GetMapping(value = "/pdf/learnersevaluation/{UUID}")
    public ResponseEntity<List<IdentifierResponseV1>> getLearnersEvaluation(@PathVariable("UUID") UUID idCourse) throws Exception{
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(idCourse);
        if(!courseEntityOptional.isPresent()) throw new NotFoundException("Course with id "+idCourse+" not found");
        CourseEntity courseEntity = courseEntityOptional.get();
        List<IdentifierResponseV1> identifierResponseV1s = new ArrayList<>();
        if(courseEntity.getTeacherList() != null)
        {
            for(TeacherCourse teacherCourse : courseEntity.getTeacherList())
            {
                DogeResponseV1 dogeResponseV1 = dogeService.learnesEvaluation(courseEntity, teacherCourse);
                identifierResponseV1s.add(new IdentifierResponseV1(dogeResponseV1.getDocumentId()));
            }
        }

        return new ResponseEntity<>(identifierResponseV1s, HttpStatus.OK);
    }

    @GetMapping(value = "/pdf/teacheremployee/{UUID}")
    public ResponseEntity<List<IdentifierResponseV1>> getTeacherEmployee(@PathVariable("UUID") UUID idCourse) throws Exception{
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(idCourse);
        if(!courseEntityOptional.isPresent()) throw new NotFoundException("Course with id "+idCourse+" not found");
        CourseEntity courseEntity = courseEntityOptional.get();
        List<IdentifierResponseV1> identifierResponseV1s = new ArrayList<>();
        if(courseEntity.getTeacherList() != null)
        {
            for(TeacherCourse teacherCourse : courseEntity.getTeacherList())
            {
                DogeResponseV1 dogeResponseV1 = dogeService.teacherEmployee(courseEntity, teacherCourse);
                identifierResponseV1s.add(new IdentifierResponseV1(dogeResponseV1.getDocumentId()));
            }
        }

        return new ResponseEntity<>(identifierResponseV1s, HttpStatus.OK);
    }

    @GetMapping(value = "/pdf/assignment/withinvoice/{UUID}")
    public ResponseEntity<List<IdentifierResponseV1>> getAssignmentWithInvoice(@PathVariable("UUID") UUID idCourse) throws Exception{
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(idCourse);
        if(!courseEntityOptional.isPresent()) throw new NotFoundException("Course with id "+idCourse+" not found");
        CourseEntity courseEntity = courseEntityOptional.get();
        List<IdentifierResponseV1> identifierResponseV1s = new ArrayList<>();
        if(courseEntity.getTeacherList() != null)
        {
            for(TeacherCourse teacherCourse : courseEntity.getTeacherList())
            {
                DogeResponseV1 dogeResponseV1 = dogeService.assignmentWithInvoice(courseEntity, teacherCourse);
                identifierResponseV1s.add(new IdentifierResponseV1(dogeResponseV1.getDocumentId()));
            }
        }

        return new ResponseEntity<>(identifierResponseV1s, HttpStatus.OK);
    }

    @GetMapping(value = "/pdf/assignment/withholdingtax/{UUID}")
    public ResponseEntity<List<IdentifierResponseV1>> getAssignmentWithWithholdingTax(@PathVariable("UUID") UUID idCourse) throws Exception{
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(idCourse);
        if(!courseEntityOptional.isPresent()) throw new NotFoundException("Course with id "+idCourse+" not found");
        CourseEntity courseEntity = courseEntityOptional.get();
        List<IdentifierResponseV1> identifierResponseV1s = new ArrayList<>();
        if(courseEntity.getTeacherList() != null)
        {
            for(TeacherCourse teacherCourse : courseEntity.getTeacherList())
            {
                DogeResponseV1 dogeResponseV1 = dogeService.assignmentWithWithholdingTax(courseEntity, teacherCourse);
                identifierResponseV1s.add(new IdentifierResponseV1(dogeResponseV1.getDocumentId()));
            }
        }

        return new ResponseEntity<>(identifierResponseV1s, HttpStatus.OK);
    }

    @GetMapping(value = "/pdf/register/{UUID}")
    public ResponseEntity<IdentifierResponseV1> getRegister(@PathVariable("UUID") UUID idCourse) throws Exception{
        DogeResponseV1 dogeResponseV1 = dogeService.register(idCourse);
        return new ResponseEntity<>(new IdentifierResponseV1(dogeResponseV1.getDocumentId()), HttpStatus.OK);
    }

}
