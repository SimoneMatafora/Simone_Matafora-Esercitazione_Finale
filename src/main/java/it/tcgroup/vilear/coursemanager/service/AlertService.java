package it.tcgroup.vilear.coursemanager.service;

import it.tcgroup.vilear.coursemanager.controller.payload.response.AlertCourseResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.entity.AlertCourseEntity;
import it.tcgroup.vilear.coursemanager.entity.AlertSettingsEntity;

import java.util.UUID;

public interface AlertService {
    void postAlertCourse(UUID idCourse);
    void patchAlertCourseStatus(UUID alertId);
    void chroneActiveAlert();
    void chronePriorityAlert();
    PaginationResponseV1<AlertCourseResponseV1> getAlertsPagination(int page, int pageSize, String courseName, String courseCode, String startDate, Boolean status, Boolean active);
}
