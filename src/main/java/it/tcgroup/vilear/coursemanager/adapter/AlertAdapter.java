package it.tcgroup.vilear.coursemanager.adapter;

import it.tcgroup.vilear.coursemanager.common.util.DateUtil;
import it.tcgroup.vilear.coursemanager.controller.payload.response.AlertCourseResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.entity.AlertCourseEntity;
import it.tcgroup.vilear.coursemanager.entity.AlertSettingsEntity;
import it.tcgroup.vilear.coursemanager.entity.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class AlertAdapter {

    @Autowired
    private DateUtil dateUtil;

    public AlertCourseResponseV1 adptAlertCourseToAlertCourseResponse(AlertCourseEntity alertCourse){

        if(alertCourse == null)
            return null;

        AlertCourseResponseV1 alertCourseResponse = new AlertCourseResponseV1();
        alertCourseResponse.setActive(alertCourse.getActive());
        alertCourseResponse.setAlertDescription(alertCourse.getAlertDescription());
        alertCourseResponse.setCourseCode(alertCourse.getCourseCode());
        alertCourseResponse.setCourseName(alertCourse.getCourseName());
        alertCourseResponse.setDateEndAlert(alertCourse.getDateEndAlert());
        alertCourseResponse.setDateStartAlert(alertCourse.getDateStartAlert());
        alertCourseResponse.setId(alertCourse.getId());
        alertCourseResponse.setIdAlert(alertCourse.getIdAlert());
        alertCourseResponse.setIdCourse(alertCourse.getIdCourse());
        alertCourseResponse.setPriority(alertCourse.getPriority());
        alertCourseResponse.setStatus(alertCourse.getStatus());

        return alertCourseResponse;
    }

    public List<AlertCourseResponseV1> adptAlertCourseToAlertCourseResponse(List<AlertCourseEntity> alertCourseList){

        if(alertCourseList == null)
            return null;

        List<AlertCourseResponseV1> alertCourseResponseList = new LinkedList<>();

        for (AlertCourseEntity att : alertCourseList){
            alertCourseResponseList.add(this.adptAlertCourseToAlertCourseResponse(att));
        }
        return alertCourseResponseList;
    }

    public PaginationResponseV1<AlertCourseResponseV1> adpAlertCoursePaginationToAlertCoursePaginationResposne(Pagination<AlertCourseEntity> alertCoursePagination){

        if(alertCoursePagination == null)
            return null;

        PaginationResponseV1<AlertCourseResponseV1> alertCoursePaginationResponse = new PaginationResponseV1<>();

        alertCoursePaginationResponse.setItems(this.adptAlertCourseToAlertCourseResponse(alertCoursePagination.getItems()));
        alertCoursePaginationResponse.setStats(alertCoursePagination.getStats());

        return alertCoursePaginationResponse;
    }


}
