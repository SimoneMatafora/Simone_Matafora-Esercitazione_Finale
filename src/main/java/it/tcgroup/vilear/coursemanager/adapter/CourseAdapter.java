package it.tcgroup.vilear.coursemanager.adapter;

import it.tcgroup.vilear.coursemanager.adapter.course.*;
import it.tcgroup.vilear.coursemanager.common.util.DateUtil;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class CourseAdapter {

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private PartnerAdapter partnerAdapter;

    @Autowired
    private AddressCourseAdapter addressCourseAdapter;

    @Autowired
    private PartnerCourseAdapter partnerCourseAdapter;

    @Autowired
    private PlacementCourseAdapter placementCourseAdapter;

    @Autowired
    private RecipientManagmentCourseAdapter recipientManagmentCourseAdapter;

    @Autowired
    private TeacherCourseAdapter teacherCourseAdapter;

    public CourseEntity adptCourseRequestToCourse(CourseRequestV1 courseRequest){

        if(courseRequest == null)
            return null;

        CourseEntity course = new CourseEntity();

        course.setActuatorSubject(partnerAdapter.adptPartnerRequestToPartnerDto(courseRequest.getActuatorSubject()));
        course.setAfternoonEndHour(courseRequest.getAfternoonEndHour());
        course.setAfternoonStartHour(courseRequest.getAfternoonStartHour());
        course.setAmountFinSecurityCapital(courseRequest.getAmountFinSecurityCapital());
        course.setAmountAttendanceBenefits(courseRequest.getAmountAttendanceBenefits());
        course.setAmountAutorizedFT(courseRequest.getAmountAutorizedFT());
        course.setAmountAutorizedFTDate(courseRequest.getAmountAutorizedFTDate());
        course.setAmountReportFT(courseRequest.getAmountReportFT());
        course.setAttendanceBenefits(courseRequest.getAttendanceBenefits());
        course.setAutProgetctFTRealizedDate(courseRequest.getAutProgetctFTRealizedDate());
        course.setBusinessEmail(courseRequest.getBusinessEmail());
        course.setBusinessName(courseRequest.getBusinessName());
        course.setCertificateTypeCourse(courseRequest.getCertificateTypeCourse());
        course.setCoachingHours(courseRequest.getCoachingHours());
        course.setCommercialTaxableCommunicationDate(courseRequest.getCommercialTaxableCommunicationDate());
        course.setContentsArea(courseRequest.getContentsArea());
        course.setCosts(courseRequest.getCosts());
        course.setCourseCode(courseRequest.getCourseCode());
        course.setCourseDescription(courseRequest.getCourseDescription());
        course.setCourseEndDate(courseRequest.getCourseEndDate());
        //course.setCourseLogo(courseRequest.getCourseLogo());
        course.setCourseStartDate(courseRequest.getCourseStartDate());
        course.setCourseTitle(courseRequest.getCourseTitle());
        course.setCourseType(courseRequest.getCourseType());
        course.setDailyHours(courseRequest.getDailyHours());
        course.setDailyRegister(courseRequest.getDailyRegister());
        course.setDeliveryDateInAdministration(courseRequest.getDeliveryDateInAdministration());
        course.setDisabled(courseRequest.getDisabled());
        //course.setDocumentAttachment(courseRequest.getDocumentAttachment());
        course.setEducationalTargetDescription(courseRequest.getEducationalTargetDescription());
        course.setEntourageHours(courseRequest.getEntourageHours());
        course.setExpiredReportingDate(courseRequest.getExpiredReportingDate());
        course.setExternalReferenceCode(courseRequest.getExternalReferenceCode());
        course.setFoundsTypeCourse(courseRequest.getFoundsTypeCourse());
        course.setHeadquatersCourse(addressCourseAdapter.adptAddressCourseRequestToAddressCourse(courseRequest.getHeadquatersCourse()));
        course.setInvoiceAuthorizationDate(courseRequest.getInvoiceAuthorizationDate());
        course.setIssueTicket(courseRequest.getIssueTicket());
        course.setLearnerType(courseRequest.getLearnerType());
        course.setMinimumNumericOfParticipants(courseRequest.getMinimumNumericOfParticipants());
        course.setMorningEndHour(courseRequest.getMorningEndHour());
        course.setMorningStartHour(courseRequest.getMorningStartHour());
        course.setNote(courseRequest.getNote());
        course.setNumberOfTickets(courseRequest.getNumberOfTickets());
        course.setOrenatationHours(courseRequest.getOrenatationHours());
        course.setPartFullTimeCourse(courseRequest.getPartFullTimeCourse());
        course.setPartnerList(partnerCourseAdapter.adptPartnerCourseRequestToPartnerCourse(courseRequest.getPartnerList()));
        course.setPaymentModality(courseRequest.getPaymentModality());
        course.setPlacementList(placementCourseAdapter.adptPlacementCourseRequestToPlacementCourse(courseRequest.getPlacementList()));
        course.setPracticeHours(courseRequest.getPracticeHours());
        course.setReceiptFTConfirmationDate(courseRequest.getReceiptFTConfirmationDate());
        course.setRecipient(courseRequest.getRecipient());
        course.setRecipientManagment(recipientManagmentCourseAdapter.adptRecipientManagmentCourseRequestToRecipientManagmentCourse(courseRequest.getRecipientManagment()));
        course.setReportNote(courseRequest.getReportNote());
        course.setSendedCanceledProjectDate(courseRequest.getSendedCanceledProjectDate());
        course.setSendedEletronicReportingDate(courseRequest.getSendedEletronicReportingDate());
        course.setSendedLearnersFTDate(courseRequest.getSendedLearnersFTDate());
        course.setSendedPaperReportingDate(courseRequest.getSendedPaperReportingDate());
        course.setSendedProjectDate(courseRequest.getSendedProjectDate());
        course.setSkilsAnalysisHours(courseRequest.getSkilsAnalysisHours());
        course.setSpecialInitiatives(courseRequest.getSpecialInitiatives());
        course.setSupplyModality(courseRequest.getSupplyModality());
        course.setTeacherList(teacherCourseAdapter.adptTeacherCourseRequestToTeacherCourse(courseRequest.getTeacherList()));
        course.setTheoryHours(courseRequest.getTheoryHours());
        course.setTicketAmount(courseRequest.getTicketAmount());
        course.setTotalAmountWithoutFS(courseRequest.getTotalAmountWithoutFS());
        course.setTotalHours(courseRequest.getTotalHours());
        course.setTotalHoursTraining(courseRequest.getTotalHoursTraining());
        course.setTotalPartnerCost(courseRequest.getTotalPartnerCost());
        course.setTotalPartnerCostOnPercent(courseRequest.getTotalPartnerCostOnPercent());
        course.setTradeUnionTeachingRequest(courseRequest.getTradeUnionTeachingRequest());
        course.setVisitHours(courseRequest.getVisitHours());
        course.setStatus(courseRequest.getStatus());

        return course;
    }

    public IdResponseV1 adptCourseIdToCourseIdResponse(CourseEntity course){

        if(course == null)
            return null;

        IdResponseV1 courseIdResponse = new IdResponseV1();
        courseIdResponse.setId(course.getId());

        return courseIdResponse;
    }

    public CourseResponseV1 adptCourseToCourseResponse(CourseEntity course){

        if(course == null)
            return null;

        CourseResponseV1 courseResponse = new CourseResponseV1();

        courseResponse.setId(course.getId().toString());
        courseResponse.setActuatorSubject(partnerAdapter.adptPartnerDtoToPartnerResponse(course.getActuatorSubject()));
        courseResponse.setAfternoonEndHour(course.getAfternoonEndHour());
        courseResponse.setAfternoonStartHour(course.getAfternoonStartHour());
        courseResponse.setAmountFinSecurityCapital(course.getAmountFinSecurityCapital());
        courseResponse.setAmountAttendanceBenefits(course.getAmountAttendanceBenefits());
        courseResponse.setAmountAutorizedFT(course.getAmountAutorizedFT());
        courseResponse.setAmountAutorizedFTDate(course.getAmountAutorizedFTDate());
        courseResponse.setAmountReportFT(course.getAmountReportFT());
        courseResponse.setAttendanceBenefits(course.getAttendanceBenefits());
        courseResponse.setAutProgetctFTRealizedDate(course.getAutProgetctFTRealizedDate());
        courseResponse.setBusinessEmail(course.getBusinessEmail());
        courseResponse.setBusinessName(course.getBusinessName());
        courseResponse.setCertificateTypeCourse(course.getCertificateTypeCourse());
        courseResponse.setCoachingHours(course.getCoachingHours());
        courseResponse.setCommercialTaxableCommunicationDate(course.getCommercialTaxableCommunicationDate());
        courseResponse.setContentsArea(course.getContentsArea());
        courseResponse.setCosts(course.getCosts());
        courseResponse.setCourseCode(course.getCourseCode());
        courseResponse.setCourseDescription(course.getCourseDescription());
        courseResponse.setCourseEndDate(course.getCourseEndDate());
        courseResponse.setCourseLogo(course.getCourseLogo());
        courseResponse.setCourseStartDate(course.getCourseStartDate());
        courseResponse.setCourseTitle(course.getCourseTitle());
        courseResponse.setCourseType(course.getCourseType());
        courseResponse.setDailyHours(course.getDailyHours());
        courseResponse.setDailyRegister(course.getDailyRegister());
        courseResponse.setDeliveryDateInAdministration(course.getDeliveryDateInAdministration());
        courseResponse.setDisabled(course.getDisabled());
        courseResponse.setDocumentAttachment(course.getDocumentsAttachment());
        courseResponse.setEducationalTargetDescription(course.getEducationalTargetDescription());
        courseResponse.setEntourageHours(course.getEntourageHours());
        courseResponse.setExpiredReportingDate(course.getExpiredReportingDate());
        courseResponse.setExternalReferenceCode(course.getExternalReferenceCode());
        courseResponse.setFoundsTypeCourse(course.getFoundsTypeCourse());
        courseResponse.setHeadquatersCourse(addressCourseAdapter.adptAddressCourseToAddressCourseResponse(course.getHeadquatersCourse()));
        courseResponse.setInvoiceAuthorizationDate(course.getInvoiceAuthorizationDate());
        courseResponse.setIssueTicket(course.getIssueTicket());
        courseResponse.setLearnerType(course.getLearnerType());
        courseResponse.setMinimumNumericOfParticipants(course.getMinimumNumericOfParticipants());
        courseResponse.setMorningEndHour(course.getMorningEndHour());
        courseResponse.setMorningStartHour(course.getMorningStartHour());
        courseResponse.setNote(course.getNote());
        courseResponse.setNumberOfTickets(course.getNumberOfTickets());
        courseResponse.setOrenatationHours(course.getOrenatationHours());
        courseResponse.setPartFullTimeCourse(course.getPartFullTimeCourse());
        courseResponse.setPartnerList(partnerCourseAdapter.adptPartnerCourseToPartnerCourseResponse(course.getPartnerList()));
        courseResponse.setPaymentModality(course.getPaymentModality());
        courseResponse.setPlacementList(placementCourseAdapter.adptPlacementCourseToPlacementCourseResponse(course.getPlacementList()));
        courseResponse.setPracticeHours(course.getPracticeHours());
        courseResponse.setReceiptFTConfirmationDate(course.getReceiptFTConfirmationDate());
        courseResponse.setRecipient(course.getRecipient());
        courseResponse.setRecipientManagment(recipientManagmentCourseAdapter.adptRecipientManagmentCourseToRecipientManagmentCourseResponse(course.getRecipientManagment()));
        courseResponse.setReportNote(course.getReportNote());
        courseResponse.setSendedCanceledProjectDate(course.getSendedCanceledProjectDate());
        courseResponse.setSendedEletronicReportingDate(course.getSendedEletronicReportingDate());
        courseResponse.setSendedLearnersFTDate(course.getSendedLearnersFTDate());
        courseResponse.setSendedPaperReportingDate(course.getSendedPaperReportingDate());
        courseResponse.setSendedProjectDate(course.getSendedProjectDate());
        courseResponse.setSkilsAnalysisHours(course.getSkilsAnalysisHours());
        courseResponse.setSpecialInitiatives(course.getSpecialInitiatives());
        courseResponse.setSupplyModality(course.getSupplyModality());
        courseResponse.setTeacherList(teacherCourseAdapter.adptTeacherCourseToTeacherCourseResponse(course.getTeacherList()));
        courseResponse.setTheoryHours(course.getTheoryHours());
        courseResponse.setTicketAmount(course.getTicketAmount());
        courseResponse.setTotalAmountWithoutFS(course.getTotalAmountWithoutFS());
        courseResponse.setTotalHours(course.getTotalHours());
        courseResponse.setTotalHoursTraining(course.getTotalHoursTraining());
        courseResponse.setTotalPartnerCost(course.getTotalPartnerCost());
        courseResponse.setTotalPartnerCostOnPercent(course.getTotalPartnerCostOnPercent());
        courseResponse.setTradeUnionTeachingRequest(course.getTradeUnionTeachingRequest());
        courseResponse.setVisitHours(course.getVisitHours());
        courseResponse.setStatus(course.getStatus());

        return courseResponse;
    }

    public List<CourseResponseV1> adptCourseToCourseResponse(List<CourseEntity> courseList){

        if(courseList == null)
            return null;

        List<CourseResponseV1> courseResponseList = new LinkedList<>();

        for(CourseEntity att : courseList){
            courseResponseList.add(this.adptCourseToCourseResponse(att));
        }
        return courseResponseList;
    }

    public PaginationResponseV1<CourseResponseV1> adpCoursePaginationTooursePaginationResposne(Pagination<CourseEntity> coursesPagination){

        if(coursesPagination == null)
            return null;

        PaginationResponseV1<CourseResponseV1> coursePaginationResponse = new PaginationResponseV1<>();

        coursePaginationResponse.setItems(this.adptCourseToCourseResponse(coursesPagination.getItems()));
        coursePaginationResponse.setStats(coursesPagination.getStats());

        return coursePaginationResponse;
    }

}
