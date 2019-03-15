package it.tcgroup.vilear.coursemanager.service;

import it.tcgroup.vilear.coursemanager.controller.payload.response.DogeResponseV1;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.dto.LearnerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface DogeService {
    DogeResponseV1 learnerCertificate(CourseEntity course, LearnerDto learner) throws Exception;

    DogeResponseV1 enqueueClaimsWithoutCai(UUID idCourse) throws Exception;

    DogeResponseV1 enqueueClaimsWithoutCounterpart(UUID idCourse) throws Exception;
}
