package it.tcgroup.vilear.coursemanager.adapter;

import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.LearnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.UploadRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.LearnerResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.TeacherResponseV1.*;
import it.tcgroup.vilear.coursemanager.controller.payload.response.UploadResponseV1;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Address;
import it.tcgroup.vilear.coursemanager.entity.LearnerEntity;
import it.tcgroup.vilear.coursemanager.entity.Pagination;
import it.tcgroup.vilear.coursemanager.entity.dto.AddressDto;
import it.tcgroup.vilear.coursemanager.entity.dto.LearnerDto;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;
import it.tcgroup.vilear.coursemanager.service.FilemanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Component
public class LearnerAdapter {

    @Autowired
    private AddressAdapter addressAdapter;

    @Autowired
    private FilemanagerService filemanagerService;

    @Autowired
    private AttachmentAdapter attachmentAdapter;

    public LearnerEntity adptLearnerRequestToLearner(LearnerRequestV1 learnerInsertRequest){

        try {

            LearnerEntity learner = new LearnerEntity();

            Address residentialAddress = null;
            if (learnerInsertRequest.getResidentialAddress() != null)
                residentialAddress = addressAdapter.adptAddressRequestToAddress(learnerInsertRequest.getResidentialAddress());

            Address domicileAddress = null;
            if (learnerInsertRequest.getDomicileAddress() != null)
                domicileAddress = addressAdapter.adptAddressRequestToAddress(learnerInsertRequest.getDomicileAddress());

            List<Attachment> attachmentList = new LinkedList<>();
            if(learnerInsertRequest.getAttachments() != null && !learnerInsertRequest.getAttachments().isEmpty()){

                for (UploadRequestV1 att : learnerInsertRequest.getAttachments() ) {

                    att.setResourceId(learnerInsertRequest.getId());
                    UploadResponseV1 response = filemanagerService.uploadFile(att);
                    attachmentList.add(attachmentAdapter.adptUploadResponseToAttachment(response));
                }
            }

            learner.setAttachments(attachmentList);
            learner.setFiscalCode(learnerInsertRequest.getFiscalCode());
            learner.setSurname(learnerInsertRequest.getSurname());
            learner.setDateOfBirth(learnerInsertRequest.getDateOfBirth());
            learner.setEmail(learnerInsertRequest.getEmail());
            learner.setBirthPlace(learnerInsertRequest.getBirthPlace());
            learner.setName(learnerInsertRequest.getName());
            learner.setDegreeOfStudies(learnerInsertRequest.getDegreeOfStudies());
            learner.setCourseOfStudy(learnerInsertRequest.getCourseOfStudy());
            learner.setNote(learnerInsertRequest.getNote());
            learner.setPhone(learnerInsertRequest.getPhone());
            learner.setId(UUID.fromString(learnerInsertRequest.getId()));
            learner.setResidentialAddress(residentialAddress);

            if (learnerInsertRequest.getDomicileEqualsResidential() == null)
                learner.setDomicileEqualsResidential(false);

            if(learner.getDomicileEqualsResidential())
                learner.setDomicileAddress(residentialAddress);
            else
                learner.setDomicileAddress(domicileAddress);

            return learner;

        } catch (IOException e) {

            throw new RuntimeException(e.getMessage());
        }
    }

    public LearnerDto adptLearnerRequestToLearnerDto(LearnerRequestV1 learnerInsertRequest){

        LearnerDto learner = new LearnerDto();

        AddressDto residentialAddress = null;
        if (learnerInsertRequest.getResidentialAddress() != null)
            residentialAddress = addressAdapter.adptAddressRequestToAddressDto(learnerInsertRequest.getResidentialAddress());

        AddressDto domicileAddress = null;
        if (learnerInsertRequest.getDomicileAddress() != null)
            domicileAddress = addressAdapter.adptAddressRequestToAddressDto(learnerInsertRequest.getDomicileAddress());

        learner.setId(learnerInsertRequest.getId());
        learner.setFiscalCode(learnerInsertRequest.getFiscalCode());
        learner.setSurname(learnerInsertRequest.getSurname());
        learner.setDateOfBirth(learnerInsertRequest.getDateOfBirth());
        learner.setEmail(learnerInsertRequest.getEmail());
        learner.setBirthPlace(learnerInsertRequest.getBirthPlace());
        learner.setName(learnerInsertRequest.getName());
        learner.setDegreeOfStudies(learnerInsertRequest.getDegreeOfStudies());
        learner.setCourseOfStudy(learnerInsertRequest.getCourseOfStudy());
        learner.setNote(learnerInsertRequest.getNote());
        learner.setPhone(learnerInsertRequest.getPhone());
        learner.setResidentialAddress(residentialAddress);

        if (learnerInsertRequest.getDomicileEqualsResidential() == null)
            learner.setDomicileEqualsResidential(false);

        if(learner.getDomicileEqualsResidential())
            learner.setDomicileAddress(residentialAddress);
        else
            learner.setDomicileAddress(domicileAddress);

        return learner;
    }

    public LearnerDto adptLearnerRequestDtoToLearnerDto(CourseRequestV1.RecipientManagmentCourseRequestV1.LearnerRequestDtoV1 learnerInsertRequest){

        LearnerDto learner = new LearnerDto();

        AddressDto residentialAddress = null;
        if (learnerInsertRequest.getResidentialAddress() != null)
            residentialAddress = addressAdapter.adptAddressRequestToAddressDto(learnerInsertRequest.getResidentialAddress());

        AddressDto domicileAddress = null;
        if (learnerInsertRequest.getDomicileAddress() != null)
            domicileAddress = addressAdapter.adptAddressRequestToAddressDto(learnerInsertRequest.getDomicileAddress());

        learner.setId(learnerInsertRequest.getId());
        learner.setFiscalCode(learnerInsertRequest.getFiscalCode());
        learner.setSurname(learnerInsertRequest.getSurname());
        learner.setDateOfBirth(learnerInsertRequest.getDateOfBirth());
        learner.setEmail(learnerInsertRequest.getEmail());
        learner.setBirthPlace(learnerInsertRequest.getBirthPlace());
        learner.setName(learnerInsertRequest.getName());
        learner.setDegreeOfStudies(learnerInsertRequest.getDegreeOfStudies());
        learner.setCourseOfStudy(learnerInsertRequest.getCourseOfStudy());
        learner.setNote(learnerInsertRequest.getNote());
        learner.setPhone(learnerInsertRequest.getPhone());
        learner.setAttachemnts(learnerInsertRequest.getAttachments());
        learner.setResidentialAddress(residentialAddress);

        if (learnerInsertRequest.getDomicileEqualsResidential() == null)
            learner.setDomicileEqualsResidential(false);

        if(learner.getDomicileEqualsResidential())
            learner.setDomicileAddress(residentialAddress);
        else
            learner.setDomicileAddress(domicileAddress);

        return learner;
    }

    public LearnerDto adptLearnerToLearnerDto(LearnerEntity learner){

        LearnerDto learnerDto = new LearnerDto();

        AddressDto residentialAddress = null;
        if (learner.getResidentialAddress() != null)
            residentialAddress = addressAdapter.adptAddressToAddressDto(learner.getResidentialAddress());

        AddressDto domicileAddress = null;
        if (learner.getDomicileAddress() != null)
            domicileAddress = addressAdapter.adptAddressToAddressDto(learner.getDomicileAddress());

        learnerDto.setId(learner.getId().toString());
        learnerDto.setFiscalCode(learner.getFiscalCode());
        learnerDto.setSurname(learner.getSurname());
        learnerDto.setDateOfBirth(learner.getDateOfBirth());
        learnerDto.setEmail(learner.getEmail());
        learnerDto.setBirthPlace(learner.getBirthPlace());
        learnerDto.setName(learner.getName());
        learnerDto.setDegreeOfStudies(learner.getDegreeOfStudies());
        learnerDto.setCourseOfStudy(learner.getCourseOfStudy());
        learnerDto.setNote(learner.getNote());
        learnerDto.setPhone(learner.getPhone());
        learnerDto.setAttachemnts(learner.getAttachments());
        learnerDto.setResidentialAddress(residentialAddress);

        if (learner.getDomicileEqualsResidential() == null)
            learnerDto.setDomicileEqualsResidential(false);

        if(learner.getDomicileEqualsResidential())
            learnerDto.setDomicileAddress(residentialAddress);
        else
            learnerDto.setDomicileAddress(domicileAddress);

        return learnerDto;
    }

    public LearnerResponseV1 adptLearnerDtoToLearnerResponse(LearnerDto learnerDto){

        LearnerResponseV1 learnerResponse = new LearnerResponseV1();

        AddressResponse residentialAddressResponse = null;
        if (learnerDto.getResidentialAddress() != null)
            residentialAddressResponse = addressAdapter.adptAddressDtoToAddressResponse(learnerDto.getResidentialAddress());

        AddressResponse domicileAddressResponse = null;
        if (learnerDto.getDomicileAddress() != null)
            domicileAddressResponse = addressAdapter.adptAddressDtoToAddressResponse(learnerDto.getDomicileAddress());

        learnerResponse.setId(learnerDto.getId());
        learnerResponse.setFiscalCode(learnerDto.getFiscalCode());
        learnerResponse.setSurname(learnerDto.getSurname());
        learnerResponse.setAttachments(learnerDto.getAttachemnts());
        learnerResponse.setDateOfBirth(learnerDto.getDateOfBirth());
        learnerResponse.setEmail(learnerDto.getEmail());
        learnerResponse.setBirthPlace(learnerDto.getBirthPlace());
        learnerResponse.setName(learnerDto.getName());
        learnerResponse.setDegreeOfStudies(learnerDto.getDegreeOfStudies());
        learnerResponse.setCourseOfStudy(learnerDto.getCourseOfStudy());
        learnerResponse.setNote(learnerDto.getNote());
        learnerResponse.setPhone(learnerDto.getPhone());
        learnerResponse.setResidentialAddress(residentialAddressResponse);

        if (learnerDto.getDomicileEqualsResidential() == null)
            learnerResponse.setDomicileEqualsResidential(false);

        if(learnerResponse.getDomicileEqualsResidential())
            learnerResponse.setDomicileAddress(residentialAddressResponse);
        else
            learnerResponse.setDomicileAddress(domicileAddressResponse);

        return learnerResponse;
    }

    public IdResponseV1 adptLearnerIdToLearnerIdResponse(LearnerEntity learner){

        IdResponseV1 learnerIdResponse = new IdResponseV1();
        learnerIdResponse.setId(learner.getId());

        return learnerIdResponse;
    }

    public LearnerResponseV1 adptLearnerToLearnerResponse(LearnerEntity learner){

        LearnerResponseV1 learnerResponse = new LearnerResponseV1();

        learnerResponse.setId(learner.getId().toString());
        learnerResponse.setFiscalCode(learner.getFiscalCode());
        learnerResponse.setSurname(learner.getSurname());
        learnerResponse.setAttachments(learner.getAttachments());
        learnerResponse.setDateOfBirth(learner.getDateOfBirth());
        learnerResponse.setEmail(learner.getEmail());
        learnerResponse.setBirthPlace(learner.getBirthPlace());
        learnerResponse.setName(learner.getName());
        learnerResponse.setDegreeOfStudies(learner.getDegreeOfStudies());
        learnerResponse.setCourseOfStudy(learner.getCourseOfStudy());
        learnerResponse.setNote(learner.getNote());
        learnerResponse.setPhone(learner.getPhone());

        learnerResponse.setResidentialAddress(addressAdapter.adptAddressToAddressResponse(learner.getResidentialAddress()));
        learnerResponse.setDomicileAddress(addressAdapter.adptAddressToAddressResponse(learner.getDomicileAddress()));
        learnerResponse.setDomicileEqualsResidential(learner.getDomicileEqualsResidential());

        return learnerResponse;
    }

    public List<LearnerResponseV1> adptLearnerToLearnerResponse(List<LearnerEntity> learnerList){

        List<LearnerResponseV1> learnerResponseList = new LinkedList<>();
        for (LearnerEntity att : learnerList){
            learnerResponseList.add(this.adptLearnerToLearnerResponse(att));
        }

        return learnerResponseList;
    }

    public PaginationResponseV1<LearnerResponseV1> adpLearnerPaginationToLearnerPaginationResposne(Pagination learnerPagination){

        PaginationResponseV1<LearnerResponseV1> learnerPaginationResponse = new PaginationResponseV1<>();

        learnerPaginationResponse.setItems(this.adptLearnerToLearnerResponse(learnerPagination.getItems()));
        learnerPaginationResponse.setStats(learnerPagination.getStats());

        return learnerPaginationResponse;
    }

}
