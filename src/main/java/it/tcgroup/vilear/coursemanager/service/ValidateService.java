package it.tcgroup.vilear.coursemanager.service;

import it.tcgroup.vilear.coursemanager.controller.payload.request.LearnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.TeacherRequestV1;

public interface ValidateService {

    void requestValidatorLearner(LearnerRequestV1 learnerRequest);
    void requestValidatorPatchLearner(LearnerRequestV1 learnerRequest);
    void requestValidatorTeacher(TeacherRequestV1 teacherRequest);
    void requestValidatorPatchTeacher(LearnerRequestV1 learnerRequest);
}
