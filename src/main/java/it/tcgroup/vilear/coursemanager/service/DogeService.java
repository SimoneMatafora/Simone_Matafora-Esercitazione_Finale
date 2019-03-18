package it.tcgroup.vilear.coursemanager.service;

import it.tcgroup.vilear.coursemanager.controller.payload.response.DogeResponseV1;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.dto.LearnerDto;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.TeacherCourse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface DogeService {
    DogeResponseV1 learnerCertificate(CourseEntity course, LearnerDto learner) throws Exception;

    DogeResponseV1 inailComunication(UUID idCourse) throws Exception;

    DogeResponseV1 ticketDelivery(UUID idCourse) throws Exception;

    DogeResponseV1 learnesEvaluation(CourseEntity courseEntity, TeacherCourse teacherCourse) throws Exception;

    DogeResponseV1 teacherEmployee(CourseEntity courseEntity, TeacherCourse teacherCourse) throws Exception;

    DogeResponseV1 assignmentWithInvoice(CourseEntity courseEntity, TeacherCourse teacherCourse) throws Exception;

    DogeResponseV1 assignmentWithWithholdingTax(CourseEntity courseEntity, TeacherCourse teacherCourse) throws Exception;

    DogeResponseV1 register(UUID idCourse) throws Exception;

}
