package it.tcgroup.vilear.coursemanager.adapter.course;

import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1.*;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1.*;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.CandidateCourse;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class CandidateCourseAdapter {

    public CandidateCourse adptCandidateCourseRequestToCandidateCourse(CandidateCourseRequestV1 candidateCourseRequest){

        if(candidateCourseRequest == null)
            return null;

        CandidateCourse candidateCourse = new CandidateCourse();
        candidateCourse.setId(candidateCourseRequest.getId());
        candidateCourse.setName(candidateCourseRequest.getName());
        candidateCourse.setSurname(candidateCourseRequest.getSurname());
        candidateCourse.setAccepted(candidateCourseRequest.getAccepted());

        return candidateCourse;
    }

    public List<CandidateCourse> adptCandidateCourseRequestToCandidateCourse(List<CandidateCourseRequestV1> candidateCourseRequestList){

        if(candidateCourseRequestList == null)
            return null;

        List<CandidateCourse> candidateCourseList = new LinkedList<>();

        for (CandidateCourseRequestV1 att : candidateCourseRequestList){
            candidateCourseList.add(this.adptCandidateCourseRequestToCandidateCourse(att));
        }

        return candidateCourseList;

    }

    public CandidateCourseResponseV1 adptCandidateCourseToCandidateCourseResponse(CandidateCourse candidateCourse){

        if(candidateCourse == null)
            return null;

        CandidateCourseResponseV1 candidateCourseResponse = new CandidateCourseResponseV1();
        candidateCourseResponse.setId(candidateCourse.getId());
        candidateCourseResponse.setName(candidateCourse.getName());
        candidateCourseResponse.setSurname(candidateCourse.getSurname());
        candidateCourseResponse.setAccepted(candidateCourse.getAccepted());

        return candidateCourseResponse;
    }

    public List<CandidateCourseResponseV1> adptCandidateCourseToCandidateCourseResponse(List<CandidateCourse> candidateCourseList){

        if(candidateCourseList == null)
            return null;

        List<CandidateCourseResponseV1> candidateCourseResponseList = new LinkedList<>();

        for (CandidateCourse att : candidateCourseList){
            candidateCourseResponseList.add(this.adptCandidateCourseToCandidateCourseResponse(att));
        }

        return candidateCourseResponseList;

    }

}
