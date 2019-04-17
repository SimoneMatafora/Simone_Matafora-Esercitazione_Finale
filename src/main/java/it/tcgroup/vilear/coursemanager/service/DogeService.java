package it.tcgroup.vilear.coursemanager.service;

import com.itextpdf.text.DocumentException;
import it.tcgroup.vilear.coursemanager.controller.payload.response.DogeResponseV1;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.dto.LearnerDto;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.TeacherCourse;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
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

    List<DogeResponseV1> register(UUID idCourse) throws Exception;

    DogeResponseV1 tradeUnionTeaching(CourseEntity courseEntity, TeacherCourse teacherCourse) throws Exception;

}