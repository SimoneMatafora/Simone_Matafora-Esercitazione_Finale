package it.tcgroup.vilear.coursemanager.adapter.course;

import it.tcgroup.vilear.coursemanager.adapter.LearnerAdapter;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1.*;
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
        recipientManagmentCourse.setLearner(learnerAdapter.adptLearnerRequestToLearnerDto(recipientManagmentCourseRequest.getLearner()));
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


}
