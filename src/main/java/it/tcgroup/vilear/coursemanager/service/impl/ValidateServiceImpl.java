package it.tcgroup.vilear.coursemanager.service.impl;

import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;
import it.tcgroup.vilear.coursemanager.common.validation.MessageCode;
import it.tcgroup.vilear.coursemanager.common.validation.RequestValidator;
import it.tcgroup.vilear.coursemanager.controller.payload.request.BranchRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.LearnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.PartnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.TeacherRequestV1;
import it.tcgroup.vilear.coursemanager.entity.enumerated.TypeAddressPartnerEnum;
import it.tcgroup.vilear.coursemanager.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void requestValidatorPatchTeacher(TeacherRequestV1 teacherRequest){

        if(teacherRequest.getAddress() != null)
            requestValidator.validateRequest(teacherRequest.getAddress(), MessageCode.E00X_1000);
    }

    @Override
    public void requestValidatorBranch(BranchRequestV1 branchRequest){

        requestValidator.validateRequest(branchRequest, MessageCode.E00X_1000);
        requestValidator.validateRequest(branchRequest.getAddress(), MessageCode.E00X_1000);
    }

    @Override
    public void requestValidatorPatchBranch(BranchRequestV1 branchRequest){

        if(branchRequest.getAddress() != null)
            requestValidator.validateRequest(branchRequest.getAddress(), MessageCode.E00X_1000);
    }

    @Override
    public void requestValidatorPartner(PartnerRequestV1 partnerRequest){

        requestValidator.validateRequest(partnerRequest, MessageCode.E00X_1000);

        Boolean mainAddress = false;
        for (PartnerRequestV1.AddressPartnerRequestV1 att : partnerRequest.getAddressList()){

            requestValidator.validateRequest(att, MessageCode.E00X_1000);
            if(att.getType().equals(TypeAddressPartnerEnum.FATTURAZIONE))
                mainAddress = true;
        }

        if(!mainAddress)
            throw new BadParametersException("There is no billing office address");
    }

    @Override
    public void requestValidatorPatchPartner(PartnerRequestV1 partnerRequest){

        Boolean mainAddress = false;
        for (PartnerRequestV1.AddressPartnerRequestV1 att : partnerRequest.getAddressList()){

            requestValidator.validateRequest(att, MessageCode.E00X_1000);
            if(att.getType().equals(TypeAddressPartnerEnum.FATTURAZIONE))
                mainAddress = true;
        }

        if(!mainAddress)
            throw new BadParametersException("There is no billing office address");

    }


}
