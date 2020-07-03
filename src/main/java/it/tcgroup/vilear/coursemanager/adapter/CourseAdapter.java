package it.tcgroup.vilear.coursemanager.adapter;

import it.tcgroup.vilear.coursemanager.controller.payload.request.LearnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.LearnerResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.LearnerEntity;
import it.tcgroup.vilear.coursemanager.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
/*

@Component
public class CourseAdapter implements Serializable {

    /*@Autowired
    private AddressAdapter addressAdapter;

    public CourseEntity adptLearnerRequestToLearner(LearnerRequestV1 filialeRequest){

        if(filialeRequest == null)
            return null;

        LearnerEntity learner = new LearnerEntity();

        learner.setName(filialeRequest.getName());
        learner.setSurname(filialeRequest.getSurname());
        learner.setFiscalCode(filialeRequest.getFiscalCode());
        learner.setDateOfBirth(filialeRequest.getDateOfBirth());
        learner.setBirthPlace(filialeRequest.getBirthPlace());
        learner.setPhone(filialeRequest.getPhone());
        learner.setEmail(filialeRequest.getEmail());
        learner.setDegreeOfStudies(filialeRequest.getDegreeOfStudies());
        learner.setCourseOfStudy(filialeRequest.getCourseOfStudy());
        learner.setNote(filialeRequest.getNote());
        learner.setResidentialAddress(addressAdapter.adptAddressRequestToAddress(filialeRequest.getResidentialAddress()));
        learner.setDomicileAddress(addressAdapter.adptAddressRequestToAddress(filialeRequest.getDomicileAddress()));
        learner.setDomicileEqualsResidential(filialeRequest.getDomicileEqualsResidential());

        return learner;
    }

    public IdResponseV1 adptLearnerIdToLearnerIdResponse(LearnerEntity learner){

        if(learner == null)
            return null;

        IdResponseV1 filialeIdResponse = new IdResponseV1();
        filialeIdResponse.setId(learner.getId());

        return filialeIdResponse;
    }

    public LearnerResponseV1 adptLearnerToLearnerResponse(LearnerEntity learner){

        if(learner == null)
            return null;

        LearnerResponseV1 learnerResponse = new LearnerResponseV1();

        learnerResponse.setId(learner.getId().toString());
        learnerResponse.setName(learner.getName());
        learnerResponse.setSurname(learner.getSurname());
        learnerResponse.setFiscalCode(learner.getFiscalCode());
        learner.setDateOfBirth(learner.getDateOfBirth());
        learnerResponse.setBirthPlace(learner.getBirthPlace());
        learnerResponse.setPhone(learner.getPhone());
        learnerResponse.setEmail(learner.getEmail());
        learnerResponse.setDegreeOfStudies(learner.getDegreeOfStudies());
        learnerResponse.setCourseOfStudy(learner.getCourseOfStudy());
        learnerResponse.setNote(learner.getNote());
        learnerResponse.setDomicileAddress(addressAdapter.adptAddressToAddressResponse(learner.getDomicileAddress()));
        learnerResponse.setResidentialAddress(addressAdapter.adptAddressToAddressResponse(learner.getResidentialAddress()));

        return learnerResponse;
    }

    public List<LearnerResponseV1> adptLearnerToLearnerResponse(List<LearnerEntity> learnerList){

        if(learnerList == null)
            return null;

        List<LearnerResponseV1> learnerResponseList = new LinkedList<>();
        for (LearnerEntity att : learnerList){
            learnerResponseList.add(this.adptLearnerToLearnerResponse(att));
        }
        return learnerResponseList;
    }


    public PaginationResponseV1<LearnerResponseV1> adpLearnerPaginationToLearnerPaginationResposne(Pagination<LearnerEntity> learnerPagination){

        if(learnerPagination == null)
            return null;

        PaginationResponseV1<LearnerResponseV1> learnerPaginationResponse = new PaginationResponseV1<>();

        learnerPaginationResponse.setItems(this.adptLearnerToLearnerResponse(learnerPagination.getItems()));
        learnerPaginationResponse.setStats(learnerPagination.getStats());

        return learnerPaginationResponse;
    }



}*/
