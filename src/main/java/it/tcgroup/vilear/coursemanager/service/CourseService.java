package it.tcgroup.vilear.coursemanager.service;

import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.UploadRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.entity.enumerated.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface CourseService {

    IdResponseV1 insertCourse(CourseRequestV1 courseInsertRequest);
    CourseResponseV1 getCourse(UUID idCourse);
    CourseResponseV1 updateCourse(CourseRequestV1 courseUpdateRequest, UUID idCourse);
    CourseResponseV1 patchCourse(CourseRequestV1 courseUpdateRequest, UUID courseId);
    void deleteCourse(UUID courseId);
    PaginationResponseV1<CourseResponseV1> getCoursePagination(int page, int pageSize, String courseTitle, String contentsArea, String learnerType, String supplyModality,
                                                               String paymentModality, String foundsType, String courseStartDate, String partFullTime, String courseCode, String businessName,
                                                               String courseType, String specialInitiatives);

    CourseResponseV1 addCourseLogo(UploadRequestV1 logo, UUID idCourse) throws IOException;
    CourseResponseV1 addCourseAttachments(List<UploadRequestV1> attachmentList, UUID idCourse) throws IOException;
    void deleteCourseLogo(UUID idCourse);
    void deleteCourseAttachments(UUID idCourse, List<UUID> attachmentList);

    CourseResponseV1 patchCourseStatus(UUID idCourse, CourseStatusEnum newStatus);

}
