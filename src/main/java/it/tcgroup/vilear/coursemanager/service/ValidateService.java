package it.tcgroup.vilear.coursemanager.service;

import it.tcgroup.vilear.coursemanager.controller.payload.request.BranchRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.LearnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.PartnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.TeacherRequestV1;

public interface ValidateService {

    void requestValidatorLearner(LearnerRequestV1 learnerRequest);
    void requestValidatorPatchLearner(LearnerRequestV1 learnerRequest);
    void requestValidatorTeacher(TeacherRequestV1 teacherRequest);
    void requestValidatorPatchTeacher(TeacherRequestV1 teacherRequest);
    void requestValidatorBranch(BranchRequestV1 branchRequestV1);
    void requestValidatorPatchBranch(BranchRequestV1 branchRequestV1);
    void requestValidatorPartner(PartnerRequestV1 partnerRequestV1);
    void requestValidatorPatchPartner(PartnerRequestV1 partnerRequestV1);
}
