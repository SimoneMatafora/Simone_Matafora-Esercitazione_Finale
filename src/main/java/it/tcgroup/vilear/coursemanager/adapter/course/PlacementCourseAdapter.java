package it.tcgroup.vilear.coursemanager.adapter.course;

import it.tcgroup.vilear.coursemanager.adapter.LearnerAdapter;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1.*;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1.*;
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
        placementCourse.setLearner(learnerAdapter.adptLearnerRequestDtoToLearnerDto(placementCourseRequest.getLearner()));
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

    public PlacementCourseResponseV1 adptPlacementCourseToPlacementCourseResponse(PlacementCourse placementCourse){

        if (placementCourse == null)
            return null;

        PlacementCourseResponseV1 placementCourseResponse = new PlacementCourseResponseV1();

        placementCourseResponse.setBonusAmount(placementCourse.getBonusAmount());
        placementCourseResponse.setCoherence(placementCourse.getCoherence());
        placementCourseResponse.setExpiredPlacementDate(placementCourse.getExpiredPlacementDate());
        placementCourseResponse.setHiringDate(placementCourse.getHiringDate());
        placementCourseResponse.setLearner(learnerAdapter.adptLearnerDtoToLearnerResponse(placementCourse.getLearner()));
        placementCourseResponse.setMissionHours(placementCourse.getMissionHours());
        placementCourseResponse.setNote(placementCourse.getNote());
        placementCourseResponse.setSendedEletronicPlacementDate(placementCourse.getSendedEletronicPlacementDate());

        return placementCourseResponse;
    }

    public List<PlacementCourseResponseV1> adptPlacementCourseToPlacementCourseResponse(List<PlacementCourse> placementCoursetList){

        if(placementCoursetList == null)
            return null;

        List<PlacementCourseResponseV1> placementCourseResponseList = new LinkedList<>();

        for (PlacementCourse att : placementCoursetList){
            placementCourseResponseList.add(this.adptPlacementCourseToPlacementCourseResponse(att));
        }

        return placementCourseResponseList;
    }
}
