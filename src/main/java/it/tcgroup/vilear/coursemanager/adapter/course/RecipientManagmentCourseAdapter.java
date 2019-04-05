package it.tcgroup.vilear.coursemanager.adapter.course;

import it.tcgroup.vilear.coursemanager.adapter.LearnerAdapter;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1.*;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1.*;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.RecipientManagmentCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class RecipientManagmentCourseAdapter {

    @Autowired
    private LearnerAdapter learnerAdapter;

    public RecipientManagmentCourse adptRecipientManagmentCourseRequestToRecipientManagmentCourse(RecipientManagmentCourseRequestV1 recipientManagmentCourseRequest){

        if(recipientManagmentCourseRequest == null)
            return  null;

        RecipientManagmentCourse recipientManagmentCourse = new RecipientManagmentCourse();

        recipientManagmentCourse.setAccepted(recipientManagmentCourseRequest.getAccepted());
        recipientManagmentCourse.setExonerationGeneralSecurity(recipientManagmentCourseRequest.getExonerationGeneralSecurity());
        recipientManagmentCourse.setExonerationRightsAndDuties(recipientManagmentCourseRequest.getExonerationRightsAndDuties());
        recipientManagmentCourse.setLearner(learnerAdapter.adptLearnerRequestDtoToLearnerDto(recipientManagmentCourseRequest.getLearner()));
        recipientManagmentCourse.setNecessaryHours(recipientManagmentCourseRequest.getNecessaryHours());
        recipientManagmentCourse.setRecipientType(recipientManagmentCourseRequest.getRecipientType());
        recipientManagmentCourse.setRejected(recipientManagmentCourseRequest.getRejected());
        recipientManagmentCourse.setSpecificationSsecurityExonerate(recipientManagmentCourseRequest.getSpecificationSsecurityExonerate());
        recipientManagmentCourse.setWithdrawn(recipientManagmentCourseRequest.getWithdrawn());
        recipientManagmentCourse.setWithdrawnDate(recipientManagmentCourseRequest.getWithdrawnDate());
        recipientManagmentCourse.setWithdrawnForm(recipientManagmentCourseRequest.getWithdrawnForm());
        recipientManagmentCourse.setWithdrawnReason(recipientManagmentCourseRequest.getWithdrawnReason());

        return recipientManagmentCourse;
    }

    public List<RecipientManagmentCourse> adptRecipientManagmentCourseRequestToRecipientManagmentCourse(List<RecipientManagmentCourseRequestV1> recipientManagmentCourseRequest){

        if(recipientManagmentCourseRequest == null)
            return null;

        List<RecipientManagmentCourse> recipientManagmentCourseList = new LinkedList<>();

        for (RecipientManagmentCourseRequestV1 att : recipientManagmentCourseRequest){
            recipientManagmentCourseList.add(this.adptRecipientManagmentCourseRequestToRecipientManagmentCourse(att));
        }

        return recipientManagmentCourseList;
    }

    public RecipientManagmentCourseResponseV1 adptRecipientManagmentCourseToRecipientManagmentCourseResponse( RecipientManagmentCourse recipientManagmentCourse){

        if(recipientManagmentCourse == null)
            return  null;

        RecipientManagmentCourseResponseV1 recipientManagmentCourseResponse = new RecipientManagmentCourseResponseV1();

        recipientManagmentCourseResponse.setAccepted(recipientManagmentCourse.getAccepted());
        recipientManagmentCourseResponse.setExonerationGeneralSecurity(recipientManagmentCourse.getExonerationGeneralSecurity());
        recipientManagmentCourseResponse.setExonerationRightsAndDuties(recipientManagmentCourse.getExonerationRightsAndDuties());
        recipientManagmentCourseResponse.setLearner(learnerAdapter.adptLearnerDtoToLearnerResponse(recipientManagmentCourse.getLearner()));
        recipientManagmentCourseResponse.setNecessaryHours(recipientManagmentCourse.getNecessaryHours());
        recipientManagmentCourseResponse.setRecipientType(recipientManagmentCourse.getRecipientType());
        recipientManagmentCourseResponse.setRejected(recipientManagmentCourse.getRejected());
        recipientManagmentCourseResponse.setSpecificationSsecurityExonerate(recipientManagmentCourse.getSpecificationSsecurityExonerate());
        recipientManagmentCourseResponse.setWithdrawn(recipientManagmentCourse.getWithdrawn());
        recipientManagmentCourseResponse.setWithdrawnDate(recipientManagmentCourse.getWithdrawnDate());
        recipientManagmentCourseResponse.setWithdrawnForm(recipientManagmentCourse.getWithdrawnForm());
        recipientManagmentCourseResponse.setWithdrawnReason(recipientManagmentCourse.getWithdrawnReason());

        return recipientManagmentCourseResponse;


    }

    public List<RecipientManagmentCourseResponseV1> adptRecipientManagmentCourseToRecipientManagmentCourseResponse(List<RecipientManagmentCourse> recipientManagmentCourseList){

        if(recipientManagmentCourseList == null)
            return null;

        List<RecipientManagmentCourseResponseV1> recipientManagmentCourseResponseList = new LinkedList<>();

        for (RecipientManagmentCourse att : recipientManagmentCourseList){
            recipientManagmentCourseResponseList.add(this.adptRecipientManagmentCourseToRecipientManagmentCourseResponse(att));
        }

        return recipientManagmentCourseResponseList;
    }

}
