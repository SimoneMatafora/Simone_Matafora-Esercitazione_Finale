package it.tcgroup.vilear.coursemanager.adapter;

import it.tcgroup.vilear.coursemanager.common.util.DateUtil;
import it.tcgroup.vilear.coursemanager.controller.payload.response.AlertResponseV1;
import it.tcgroup.vilear.coursemanager.entity.AlertEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlertAdapter {

    @Autowired
    private DateUtil dateUtil;

    public AlertResponseV1 adptAlertToAlertResponse(AlertEntity alert){

        if(alert == null)
            return null;

        AlertResponseV1 alertResponse = new AlertResponseV1();

        alertResponse.setId(alert.getId());
        alertResponse.setCourseCode(alert.getCourseCode());
        alertResponse.setCourseName(alert.getCourseName());
        alertResponse.setActive(alert.getActive());
        alertResponse.setAlertDescription(alert.getAlertDescription());
        alertResponse.setNumberOfDays(alert.getNumberOfDays());
        alertResponse.setPriority(alert.getPriority());
        alertResponse.setStartDate(dateUtil.convertUTCInstantToIS08601String(alert.getStartDate()));
        alertResponse.setStatus(alert.getStatus());
        alertResponse.setType(alert.getType());

        return alertResponse;
    }

}
