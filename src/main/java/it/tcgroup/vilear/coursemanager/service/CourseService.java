package it.tcgroup.vilear.coursemanager.service;

import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.entity.enumerated.*;

import java.util.UUID;

public interface CourseService {

    IdResponseV1 insertCourse(CourseRequestV1 courseInsertRequest);
    CourseResponseV1 getCourse(UUID idCourse);
    CourseResponseV1 updateCourse(CourseRequestV1 courseUpdateRequest, UUID idCourse);
    CourseResponseV1 patchCourse(CourseRequestV1 courseUpdateRequest, UUID courseId);
    void deleteCourse(UUID courseId);
    PaginationResponseV1<CourseResponseV1> getCoursePagination(int page, int pageSize, String courseTitle, ContentsAreaCourseEnum contentsArea, LearnerTypeCourseEnum learnerType, SupplyModalityCourseEnum supplyModality,
                                                               PaymentModalityEnum paymentModality, FoundsTypeCourseEnum foundsType, String courseStartDate, PartFullTimeCourseEnum partFullTime, String courseCode, String businessName,
                                                               CourseTypeEnum courseType, SpecialInitiativesCourseEnum specialInitiatives);

}
