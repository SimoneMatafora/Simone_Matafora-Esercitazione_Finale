package it.tcgroup.vilear.coursemanager.adapter;

import it.tcgroup.vilear.coursemanager.controller.payload.request.LearnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.LearnerResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.entity.AddressEntity;
import it.tcgroup.vilear.coursemanager.entity.LearnerEntity;
import it.tcgroup.vilear.coursemanager.entity.Pagination;
import it.tcgroup.vilear.coursemanager.entity.dto.AddressDto;
import it.tcgroup.vilear.coursemanager.entity.dto.LearnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class LearnerAdapter {

    @Autowired
    private AddressAdapter addressAdapter;

    public LearnerEntity adptLearnerRequestToLearner(LearnerRequestV1 learnerInsertRequest){

        LearnerEntity learner = new LearnerEntity();

        AddressEntity address = null;
        if (learnerInsertRequest.getAddress() != null)
            address = addressAdapter.adptAddressRequestToAddress(learnerInsertRequest.getAddress());

        learner.setFiscalCode(learnerInsertRequest.getFiscalCode());
        learner.setSurname(learnerInsertRequest.getSurname());
        learner.setCurriculumVitae(learnerInsertRequest.getCurriculumVitae());
        learner.setDateOfBirth(learnerInsertRequest.getDateOfBirth());
        learner.setEmail(learnerInsertRequest.getEmail());
        learner.setBirthPlace(learnerInsertRequest.getBirthPlace());
        learner.setName(learnerInsertRequest.getName());
        learner.setDegreeOfStudies(learnerInsertRequest.getDegreeOfStudies());
        learner.setCourseOfStudy(learnerInsertRequest.getCourseOfStudy());
        learner.setNote(learnerInsertRequest.getNote());
        learner.setPhone(learnerInsertRequest.getPhone());
        learner.setUsername(learnerInsertRequest.getUsername());
        learner.setAddress(address);

        return learner;
    }

    public LearnerDto adptLearnerRequestToLearnerDto(LearnerRequestV1 learnerInsertRequest){

        LearnerDto learner = new LearnerDto();

        AddressDto address = null;
        if (learnerInsertRequest.getAddress() != null)
            address = addressAdapter.adptAddressRequestToAddressDto(learnerInsertRequest.getAddress());

        learner.setFiscalCode(learnerInsertRequest.getFiscalCode());
        learner.setSurname(learnerInsertRequest.getSurname());
        learner.setCurriculumVitae(learnerInsertRequest.getCurriculumVitae());
        learner.setDateOfBirth(learnerInsertRequest.getDateOfBirth());
        learner.setEmail(learnerInsertRequest.getEmail());
        learner.setBirthPlace(learnerInsertRequest.getBirthPlace());
        learner.setName(learnerInsertRequest.getName());
        learner.setDegreeOfStudies(learnerInsertRequest.getDegreeOfStudies());
        learner.setCourseOfStudy(learnerInsertRequest.getCourseOfStudy());
        learner.setNote(learnerInsertRequest.getNote());
        learner.setPhone(learnerInsertRequest.getPhone());
        learner.setUsername(learnerInsertRequest.getUsername());
        learner.setAddress(address);

        return learner;
    }

    public IdResponseV1 adptLearnerIdToLearnerIdResponse(LearnerEntity learner){

        IdResponseV1 learnerIdResponse = new IdResponseV1();
        learnerIdResponse.setId(learner.getId());

        return learnerIdResponse;
    }

    public LearnerResponseV1 adptLearnerToLearnerResponse(LearnerEntity learner){

        LearnerResponseV1 learnerResponse = new LearnerResponseV1();

        learnerResponse.setFiscalCode(learner.getFiscalCode());
        learnerResponse.setSurname(learner.getSurname());
        learnerResponse.setCurriculumVitae(learner.getCurriculumVitae());
        learnerResponse.setDateOfBirth(learner.getDateOfBirth());
        learnerResponse.setEmail(learner.getEmail());
        learnerResponse.setBirthPlace(learner.getBirthPlace());
        learnerResponse.setName(learner.getName());
        learnerResponse.setDegreeOfStudies(learner.getDegreeOfStudies());
        learnerResponse.setCourseOfStudy(learner.getCourseOfStudy());
        learnerResponse.setNote(learner.getNote());
        learnerResponse.setPhone(learner.getPhone());
        learnerResponse.setUsername(learner.getUsername());

        learnerResponse.setAddress(addressAdapter.adptAddressToAddressResponse(learner.getAddress()));

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
