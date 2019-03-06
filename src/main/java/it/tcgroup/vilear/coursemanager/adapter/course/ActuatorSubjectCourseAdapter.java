package it.tcgroup.vilear.coursemanager.adapter.course;

import it.tcgroup.vilear.coursemanager.adapter.PartnerAdapter;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.ActuatorSubjectCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActuatorSubjectCourseAdapter {

    @Autowired
    private PartnerAdapter.TeacherPartnerAdapter teacherPartnerAdapter;

    @Autowired
    private PartnerAdapter.AddressPartnerAdapter addressPartnerAdapter;

    public ActuatorSubjectCourse adptActuatorSubjectCourseRequestToActuatorSubjectCourse(CourseRequestV1.ActuatorSubjectCourseRequestV1 actuatorSubjectCourseRequest){

        if(actuatorSubjectCourseRequest == null)
            return null;

        ActuatorSubjectCourse actuatorSubjectCourse = new ActuatorSubjectCourse();

        actuatorSubjectCourse.setAccreditedFt(actuatorSubjectCourseRequest.getAccreditedFt());
        actuatorSubjectCourse.setAccreditedFtCode(actuatorSubjectCourseRequest.getAccreditedFtCode());
        actuatorSubjectCourse.setBusinessName(actuatorSubjectCourseRequest.getBusinessName());
        actuatorSubjectCourse.setCompany(actuatorSubjectCourseRequest.getCompany());
        actuatorSubjectCourse.setCostElement(actuatorSubjectCourseRequest.getCostElement());
        actuatorSubjectCourse.setEmail(actuatorSubjectCourseRequest.getEmail());
        actuatorSubjectCourse.setFax(actuatorSubjectCourseRequest.getFax());
        actuatorSubjectCourse.setManagerName(actuatorSubjectCourseRequest.getManagerName());
        actuatorSubjectCourse.setManagerNumber(actuatorSubjectCourseRequest.getManagerNumber());
        actuatorSubjectCourse.setNote(actuatorSubjectCourseRequest.getNote());
        actuatorSubjectCourse.setPhone(actuatorSubjectCourseRequest.getPhone());
        actuatorSubjectCourse.setVatNumber(actuatorSubjectCourseRequest.getVatNumber());
        actuatorSubjectCourse.setTeacherList(teacherPartnerAdapter.adptTeacherPartnerRequestToTeacherPartner(actuatorSubjectCourseRequest.getTeacherList()));
        actuatorSubjectCourse.setAddressList(addressPartnerAdapter.adptAddressPartnerRequestToAddressPartner(actuatorSubjectCourseRequest.getAddressList()));

        return actuatorSubjectCourse;

    }

    public CourseResponseV1.ActuatorSubjectCourseResponseV1 adptActuatorSubjectCourseToActuatorSubjectCourseResponse(ActuatorSubjectCourse actuatorSubjectCourse){

        if(actuatorSubjectCourse == null)
            return null;

        CourseResponseV1.ActuatorSubjectCourseResponseV1 actuatorSubjectCourseResponse = new CourseResponseV1.ActuatorSubjectCourseResponseV1();

        actuatorSubjectCourseResponse.setAccreditedFt(actuatorSubjectCourse.getAccreditedFt());
        actuatorSubjectCourseResponse.setAccreditedFtCode(actuatorSubjectCourse.getAccreditedFtCode());
        actuatorSubjectCourseResponse.setBusinessName(actuatorSubjectCourse.getBusinessName());
        actuatorSubjectCourseResponse.setCompany(actuatorSubjectCourse.getCompany());
        actuatorSubjectCourseResponse.setCostElement(actuatorSubjectCourse.getCostElement());
        actuatorSubjectCourseResponse.setEmail(actuatorSubjectCourse.getEmail());
        actuatorSubjectCourseResponse.setFax(actuatorSubjectCourse.getFax());
        actuatorSubjectCourseResponse.setManagerName(actuatorSubjectCourse.getManagerName());
        actuatorSubjectCourseResponse.setManagerNumber(actuatorSubjectCourse.getManagerNumber());
        actuatorSubjectCourseResponse.setNote(actuatorSubjectCourse.getNote());
        actuatorSubjectCourseResponse.setPhone(actuatorSubjectCourse.getPhone());
        actuatorSubjectCourseResponse.setVatNumber(actuatorSubjectCourse.getVatNumber());
        actuatorSubjectCourseResponse.setTeacherList(teacherPartnerAdapter.adptTeacherPartnerToTeacherPartnerResponse(actuatorSubjectCourse.getTeacherList()));
        actuatorSubjectCourseResponse.setAddressList(addressPartnerAdapter.adptAddressPartnerToAddressPartnerResponse(actuatorSubjectCourse.getAddressList()));

        return actuatorSubjectCourseResponse;

    }

}
