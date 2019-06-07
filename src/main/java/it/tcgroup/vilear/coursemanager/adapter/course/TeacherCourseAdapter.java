package it.tcgroup.vilear.coursemanager.adapter.course;

import it.tcgroup.vilear.coursemanager.adapter.AttachmentAdapter;
import it.tcgroup.vilear.coursemanager.adapter.TeacherAdapter;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1.*;
import it.tcgroup.vilear.coursemanager.controller.payload.request.UploadRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1.*;
import it.tcgroup.vilear.coursemanager.controller.payload.response.UploadResponseV1;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.TeacherCourse;
import it.tcgroup.vilear.coursemanager.service.FilemanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Component
public class TeacherCourseAdapter {

    @Autowired
    private TeacherAdapter teacherAdapter;

    @Autowired
    private AttachmentAdapter attachmentAdapter;

    @Autowired
    private FilemanagerService filemanagerService;

    public TeacherCourse adptTeacherCourseRequestToTeacherCourse(TeacherCourseRequestV1 teacherCourseRequest) throws IOException {

        if(teacherCourseRequest == null)
            return null;

        TeacherCourse teacherCourse = new TeacherCourse();

        Attachment learnerJudgementForm = null;
        if (teacherCourseRequest.getLearnerJudgementForm() != null) {

            UploadRequestV1 request = teacherCourseRequest.getLearnerJudgementForm();
            if(request.getFileContent() != null && !request.getFileContent().isEmpty()) {
                request.setResourceId(UUID.randomUUID().toString());
                UploadResponseV1 response = filemanagerService.uploadFile(request);
                learnerJudgementForm = attachmentAdapter.adptUploadResponseToAttachment(response);
            }

        }

        Attachment tradeUninoTeachingLetter = null;
        if (teacherCourseRequest.getTradeUninoTeachingLetter() != null) {

            UploadRequestV1 request = teacherCourseRequest.getTradeUninoTeachingLetter();
            if(request.getFileContent() != null && !request.getFileContent().isEmpty()) {
                request.setResourceId(UUID.randomUUID().toString());
                UploadResponseV1 response = filemanagerService.uploadFile(request);
                tradeUninoTeachingLetter = attachmentAdapter.adptUploadResponseToAttachment(response);
            }
        }


        teacherCourse.setAcronymTradeUnino(teacherCourseRequest.getAcronymTradeUnion());
        teacherCourse.setGrossHourlyCost(teacherCourseRequest.getGrossHourlyCost());
        teacherCourse.setHoursTeachingLetterAssignment(teacherCourseRequest.getHoursTeachingLetterAssignment());
        teacherCourse.setLearnerJudgementForm(learnerJudgementForm);
        teacherCourse.setMain(teacherCourseRequest.getMain());
        teacherCourse.setNetHourlyCost(teacherCourseRequest.getNetHourlyCost());
        teacherCourse.setPaymentModality(teacherCourseRequest.getPaymentModality());
        teacherCourse.setPaymentModalityTradeUnion(teacherCourseRequest.getPaymentModalityTradeUnion());
        teacherCourse.setRole(teacherCourseRequest.getRole());
        teacherCourse.setTeacher(teacherAdapter.adptTeacherRequestDtoToTeacherDto(teacherCourseRequest.getTeacher()));
        teacherCourse.setTotalHoursPerformed(teacherCourseRequest.getTotalHoursPerformed());
        teacherCourse.setTradeUninoTeachingLetter(tradeUninoTeachingLetter);
        teacherCourse.setWorkingPosition(teacherCourseRequest.getWorkingPosition());

        return teacherCourse;
    }

    public List<TeacherCourse> adptTeacherCourseRequestToTeacherCourse(List<TeacherCourseRequestV1> teacherCourseRequest) throws IOException {

        if(teacherCourseRequest == null)
            return null;

        List<TeacherCourse> teacherCourseList = new LinkedList<>();

        for (TeacherCourseRequestV1 att : teacherCourseRequest){
            teacherCourseList.add(this.adptTeacherCourseRequestToTeacherCourse(att));
        }

        return teacherCourseList;
    }


    public TeacherCourseResponseV1 adptTeacherCourseToTeacherCourseResponse(TeacherCourse teacherCourse){

        if(teacherCourse == null)
            return null;

        TeacherCourseResponseV1 teacherCourseResponse = new TeacherCourseResponseV1();

        teacherCourseResponse.setAcronymTradeUnino(teacherCourse.getAcronymTradeUnino());
        teacherCourseResponse.setGrossHourlyCost(teacherCourse.getGrossHourlyCost());
        teacherCourseResponse.setHoursTeachingLetterAssignment(teacherCourse.getHoursTeachingLetterAssignment());
        teacherCourseResponse.setLearnerJudgementForm(teacherCourse.getLearnerJudgementForm());
        teacherCourseResponse.setMain(teacherCourse.getMain());
        teacherCourseResponse.setNetHourlyCost(teacherCourse.getNetHourlyCost());
        teacherCourseResponse.setPaymentModality(teacherCourse.getPaymentModality());
        teacherCourseResponse.setPaymentModalityTradeUnion(teacherCourse.getPaymentModalityTradeUnion());
        teacherCourseResponse.setRole(teacherCourse.getRole());
        teacherCourseResponse.setTeacher(teacherAdapter.adptTeacherDtoToTeacherResponse(teacherCourse.getTeacher()));
        teacherCourseResponse.setTotalHoursPerformed(teacherCourse.getTotalHoursPerformed());
        teacherCourseResponse.setTradeUninoTeachingLetter(teacherCourse.getTradeUninoTeachingLetter());
        teacherCourseResponse.setWorkingPosition(teacherCourse.getWorkingPosition());

        return teacherCourseResponse;

    }

    public List<TeacherCourseResponseV1> adptTeacherCourseToTeacherCourseResponse(List<TeacherCourse> teacherCourseList){

        if(teacherCourseList == null)
            return null;

        List<TeacherCourseResponseV1> teacherCourseResponseList = new LinkedList<>();

        for (TeacherCourse att : teacherCourseList){
            teacherCourseResponseList.add(this.adptTeacherCourseToTeacherCourseResponse(att));
        }

        return teacherCourseResponseList;
    }

}
