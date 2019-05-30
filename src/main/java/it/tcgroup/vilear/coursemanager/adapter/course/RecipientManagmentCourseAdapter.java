package it.tcgroup.vilear.coursemanager.adapter.course;

import it.tcgroup.vilear.coursemanager.adapter.AttachmentAdapter;
import it.tcgroup.vilear.coursemanager.adapter.LearnerAdapter;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1.*;
import it.tcgroup.vilear.coursemanager.controller.payload.request.UploadRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1.*;
import it.tcgroup.vilear.coursemanager.controller.payload.response.UploadResponseV1;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.RecipientManagmentCourse;
import it.tcgroup.vilear.coursemanager.service.FilemanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Component
public class RecipientManagmentCourseAdapter {

    @Autowired
    private LearnerAdapter learnerAdapter;

    @Autowired
    private AttachmentAdapter attachmentAdapter;

    @Autowired
    private FilemanagerService filemanagerService;

    public RecipientManagmentCourse adptRecipientManagmentCourseRequestToRecipientManagmentCourse(RecipientManagmentCourseRequestV1 recipientManagmentCourseRequest) throws IOException {

        if(recipientManagmentCourseRequest == null)
            return  null;

        RecipientManagmentCourse recipientManagmentCourse = new RecipientManagmentCourse();

        Attachment withdrawnForm = null;
        if (recipientManagmentCourseRequest.getWithdrawnForm() != null) {

            UploadRequestV1 request = recipientManagmentCourseRequest.getWithdrawnForm();
            request.setResourceId(UUID.randomUUID().toString());
            UploadResponseV1 response = filemanagerService.uploadFile(request);
            withdrawnForm = attachmentAdapter.adptUploadResponseToAttachment(response);
        }

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
        recipientManagmentCourse.setWithdrawnForm(withdrawnForm);
        recipientManagmentCourse.setWithdrawnReason(recipientManagmentCourseRequest.getWithdrawnReason());
        recipientManagmentCourse.setGeneralSecurityModule(recipientManagmentCourseRequest.getGeneralSecurityModule());
        recipientManagmentCourse.setSpecificSecurityModule(recipientManagmentCourseRequest.getSpecificSecurityModule());

        return recipientManagmentCourse;
    }

    public List<RecipientManagmentCourse> adptRecipientManagmentCourseRequestToRecipientManagmentCourse(List<RecipientManagmentCourseRequestV1> recipientManagmentCourseRequest) throws IOException {

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
        recipientManagmentCourseResponse.setGeneralSecurityModule(recipientManagmentCourse.getGeneralSecurityModule());
        recipientManagmentCourseResponse.setSpecificSecurityModule(recipientManagmentCourse.getSpecificSecurityModule());


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
