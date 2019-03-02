package it.tcgroup.vilear.coursemanager.adapter.course;

import it.tcgroup.vilear.coursemanager.adapter.TeacherAdapter;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1.*;
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
        teacherCourse.setTeacher(teacherAdapter.adptTeacherRequestToTeacher(teacherCourseRequest.getTeacher()));
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
}
