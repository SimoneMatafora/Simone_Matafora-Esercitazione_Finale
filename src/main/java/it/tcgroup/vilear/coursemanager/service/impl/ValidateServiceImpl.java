package it.tcgroup.vilear.coursemanager.service.impl;

import it.tcgroup.vilear.coursemanager.common.validation.MessageCode;
import it.tcgroup.vilear.coursemanager.common.validation.RequestValidator;
import it.tcgroup.vilear.coursemanager.controller.payload.request.LearnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.TeacherRequestV1;
import it.tcgroup.vilear.coursemanager.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidateServiceImpl implements ValidateService {

    @Autowired
    private RequestValidator requestValidator;

    @Override
    public void requestValidatorLearner(LearnerRequestV1 learnerRequest){

        requestValidator.validateRequest(learnerRequest, MessageCode.E00X_1000);
        requestValidator.validateRequest(learnerRequest.getAddress(), MessageCode.E00X_1000);
    }

    @Override
    public void requestValidatorPatchLearner(LearnerRequestV1 learnerRequest){

        if(learnerRequest.getAddress() != null)
            requestValidator.validateRequest(learnerRequest.getAddress(), MessageCode.E00X_1000);
    }

    @Override
    public void requestValidatorTeacher(TeacherRequestV1 teacherRequest){

        requestValidator.validateRequest(teacherRequest, MessageCode.E00X_1000);
        requestValidator.validateRequest(teacherRequest.getAddress(), MessageCode.E00X_1000);
    }

    @Override
    public void requestValidatorPatchTeacher(LearnerRequestV1 learnerRequest){

        if(learnerRequest.getAddress() != null)
            requestValidator.validateRequest(learnerRequest.getAddress(), MessageCode.E00X_1000);
    }


}
