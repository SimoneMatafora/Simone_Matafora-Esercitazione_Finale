package it.tcgroup.vilear.coursemanager.service;

import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;

public interface CourseService {

    IdResponseV1 insertCourse(CourseRequestV1 courseInsertRequest);
}
