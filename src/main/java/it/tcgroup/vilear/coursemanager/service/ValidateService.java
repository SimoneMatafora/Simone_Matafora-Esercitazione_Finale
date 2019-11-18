package it.tcgroup.vilear.coursemanager.service;

import it.tcgroup.vilear.coursemanager.controller.payload.request.*;

public interface ValidateService {

    void requestValidatorLearnerRegistration(LearnerRequestV1 learnerRequest);
    void requestValidatorLearner(LearnerRequestV1 learnerRequest);
    void requestValidatorPatchLearner(LearnerRequestV1 learnerRequest);
    void requestValidatorTeacherRegistration(TeacherRegistrationRequestV1 teacherRequest);
    void requestValidatorTeacher(TeacherRequestV1 teacherRequest);
    void requestValidatorPatchTeacher(TeacherRequestV1 teacherRequest);
    void requestValidatorBranch(BranchRequestV1 branchRequestV1);
    void requestValidatorPatchBranch(BranchRequestV1 branchRequestV1);
    void requestValidatorPartner(PartnerRequestV1 partnerRequestV1);
    void requestValidatorPatchPartner(PartnerRequestV1 partnerRequestV1);
}
