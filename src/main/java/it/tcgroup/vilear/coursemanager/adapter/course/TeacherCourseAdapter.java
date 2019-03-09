package it.tcgroup.vilear.coursemanager.adapter.course;

import it.tcgroup.vilear.coursemanager.adapter.TeacherAdapter;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1.*;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1.*;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.TeacherCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class TeacherCourseAdapter {

    @Autowired
    private TeacherAdapter teacherAdapter;

    public TeacherCourse adptTeacherCourseRequestToTeacherCourse(TeacherCourseRequestV1 teacherCourseRequest){

        if(teacherCourseRequest == null)
            return null;

        TeacherCourse teacherCourse = new TeacherCourse();

        teacherCourse.setAcronymTradeUnino(teacherCourseRequest.getAcronymTradeUnino());
        teacherCourse.setGrossHourlyCost(teacherCourseRequest.getGrossHourlyCost());
        teacherCourse.setHoursTeachingLetterAssignment(teacherCourseRequest.getHoursTeachingLetterAssignment());
        teacherCourse.setLearnerJudgementForm(teacherCourseRequest.getLearnerJudgementForm());
        teacherCourse.setMain(teacherCourseRequest.getMain());
        teacherCourse.setNetHourlyCost(teacherCourseRequest.getNetHourlyCost());
        teacherCourse.setPaymentModality(teacherCourseRequest.getPaymentModality());
        teacherCourse.setPaymentModalityTradeUnion(teacherCourseRequest.getPaymentModalityTradeUnion());
        teacherCourse.setRole(teacherCourseRequest.getRole());
        teacherCourse.setTeacher(teacherAdapter.adptTeacherRequestToTeacherDto(teacherCourseRequest.getTeacher()));
        teacherCourse.setTotalHoursPerformed(teacherCourseRequest.getTotalHoursPerformed());
        teacherCourse.setTradeUninoTeachingLetter(teacherCourseRequest.getTradeUninoTeachingLetter());

        return teacherCourse;
    }

    public List<TeacherCourse> adptTeacherCourseRequestToTeacherCourse(List<TeacherCourseRequestV1> teacherCourseRequest){

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
