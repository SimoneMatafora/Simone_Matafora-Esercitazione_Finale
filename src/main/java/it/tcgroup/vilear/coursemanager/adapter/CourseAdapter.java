package it.tcgroup.vilear.coursemanager.adapter;

import it.tcgroup.vilear.coursemanager.adapter.course.*;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseAdapter {

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

        CourseEntity course = new CourseEntity();

        course.setActuatorSubject(partnerAdapter.adptPartnerRequestToPartner(courseRequest.getActuatorSubject()));
        course.setAfternoonEndHour(courseRequest.getAfternoonEndHour());
        course.setAfternoonStatrHour(courseRequest.getAfternoonStatrHour());
        course.setAmount_fin_security_capital(courseRequest.getAmount_fin_security_capital());
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
        course.setCourseLogo(courseRequest.getCourseLogo());
        course.setCourseStartDate(courseRequest.getCourseStartDate());
        course.setCourseTitle(courseRequest.getCourseTitle());
        course.setCourseType(courseRequest.getCourseType());
        course.setDailyHours(courseRequest.getDailyHours());
        course.setDailyRegister(courseRequest.getDailyRegister());
        course.setDeliveryDateInAdministration(courseRequest.getDeliveryDateInAdministration());
        course.setDisabled(courseRequest.getDisabled());
        course.setDocumentAttachment(courseRequest.getDocumentAttachment());
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
        course.setTicket_amount(courseRequest.getTicket_amount());
        course.setTotalAmountWithoutFS(courseRequest.getTotalAmountWithoutFS());
        course.setTotalHours(courseRequest.getTotalHours());
        course.setTotalHoursTraining(courseRequest.getTotalHoursTraining());
        course.setTotalPartnerCost(courseRequest.getTotalPartnerCost());
        course.setTotalPartnerCostOnPercent(courseRequest.getTotalPartnerCostOnPercent());
        course.setTradeUnionTeachingRequest(courseRequest.getTradeUnionTeachingRequest());
        course.setVisitHours(courseRequest.getVisitHours());

        return course;


    }
}
