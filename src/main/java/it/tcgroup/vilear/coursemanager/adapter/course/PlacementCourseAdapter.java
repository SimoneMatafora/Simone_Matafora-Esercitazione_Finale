package it.tcgroup.vilear.coursemanager.adapter.course;

import it.tcgroup.vilear.coursemanager.adapter.LearnerAdapter;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1.*;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.PlacementCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class PlacementCourseAdapter {

    @Autowired
    private LearnerAdapter learnerAdapter;

    public PlacementCourse adptPlacementCourseRequestToPlacementCourse(PlacementCourseRequestV1 placementCourseRequest){

        if (placementCourseRequest == null)
            return null;

        PlacementCourse placementCourse = new PlacementCourse();

        placementCourse.setBonusAmount(placementCourseRequest.getBonusAmount());
        placementCourse.setCoherence(placementCourseRequest.getCoherence());
        placementCourse.setExpiredPlacementDate(placementCourseRequest.getExpiredPlacementDate());
        placementCourse.setHiringDate(placementCourseRequest.getHiringDate());
        placementCourse.setLearner(learnerAdapter.adptLearnerRequestToLearner(placementCourseRequest.getLearner()));
        placementCourse.setMissionHours(placementCourseRequest.getMissionHours());
        placementCourse.setNote(placementCourseRequest.getNote());
        placementCourse.setSendedEletronicPlacementDate(placementCourseRequest.getSendedEletronicPlacementDate());

        return placementCourse;
    }

    public List<PlacementCourse> adptPlacementCourseRequestToPlacementCourse(List<PlacementCourseRequestV1> placementCourseRequestList){

        if(placementCourseRequestList == null)
            return null;

        List<PlacementCourse> placementCourseList = new LinkedList<>();

        for (PlacementCourseRequestV1 att : placementCourseRequestList){
            placementCourseList.add(this.adptPlacementCourseRequestToPlacementCourse(att));
        }

        return placementCourseList;
    }
}
