package it.tcgroup.vilear.coursemanager.service.impl;

import it.tcgroup.vilear.coursemanager.adapter.AttachmentAdapter;
import it.tcgroup.vilear.coursemanager.adapter.CourseAdapter;
import it.tcgroup.vilear.coursemanager.common.exception.NotFoundException;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.UploadRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.UploadResponseV1;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.Pagination;
import it.tcgroup.vilear.coursemanager.entity.enumerated.*;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;
import it.tcgroup.vilear.coursemanager.repository.CourseEMRepository;
import it.tcgroup.vilear.coursemanager.repository.CourseRepository;
import it.tcgroup.vilear.coursemanager.service.CourseService;
import it.tcgroup.vilear.coursemanager.service.FilemanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Transactional
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseAdapter courseAdapter;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseEMRepository courseEMRepository;

    @Autowired
    private FilemanagerService filemanagerService;

    @Autowired
    private AttachmentAdapter attachmentAdapter;


    @Override
    public IdResponseV1 insertCourse(CourseRequestV1 courseInsertRequest) {

        CourseEntity course = courseAdapter.adptCourseRequestToCourse(courseInsertRequest);
        courseRepository.save(course);

        return courseAdapter.adptCourseIdToCourseIdResponse(course);
    }

    @Override
    public CourseResponseV1 getCourse(UUID courseId){

        Optional<CourseEntity> courseOpt = courseRepository.findById(courseId);

        if(!courseOpt.isPresent()){
            throw new NotFoundException("Course with id " + courseId+ " not found");
        }

        return courseAdapter.adptCourseToCourseResponse(courseOpt.get());
    }

    @Modifying
    @Override
    public CourseResponseV1 updateCourse(CourseRequestV1 courseUpdateRequest, UUID courseId){

        Optional<CourseEntity> courseOpt = courseRepository.findById(courseId);

        if(!courseOpt.isPresent()){
            throw new NotFoundException("Course with id " + courseId+ " not found");
        }

        CourseEntity course = courseOpt.get();

        CourseEntity courseUpdate = courseAdapter.adptCourseRequestToCourse(courseUpdateRequest);

        course.setActuatorSubject(courseUpdate.getActuatorSubject());
        course.setAfternoonEndHour(courseUpdate.getAfternoonEndHour());
        course.setAfternoonStatrHour(courseUpdate.getAfternoonStatrHour());
        course.setAmount_fin_security_capital(courseUpdate.getAmount_fin_security_capital());
        course.setAmountAttendanceBenefits(courseUpdate.getAmountAttendanceBenefits());
        course.setAmountAutorizedFT(courseUpdate.getAmountAutorizedFT());
        course.setAmountAutorizedFTDate(courseUpdate.getAmountAutorizedFTDate());
        course.setAmountReportFT(courseUpdate.getAmountReportFT());
        course.setAttendanceBenefits(courseUpdate.getAttendanceBenefits());
        course.setAutProgetctFTRealizedDate(courseUpdate.getAutProgetctFTRealizedDate());
        course.setBusinessEmail(courseUpdate.getBusinessEmail());
        course.setBusinessName(courseUpdate.getBusinessName());
        course.setCertificateTypeCourse(courseUpdate.getCertificateTypeCourse());
        course.setCoachingHours(courseUpdate.getCoachingHours());
        course.setCommercialTaxableCommunicationDate(courseUpdate.getCommercialTaxableCommunicationDate());
        course.setContentsArea(courseUpdate.getContentsArea());
        course.setCosts(courseUpdate.getCosts());
        course.setCourseCode(courseUpdate.getCourseCode());
        course.setCourseDescription(courseUpdate.getCourseDescription());
        course.setCourseEndDate(courseUpdate.getCourseEndDate());
        //course.setCourseLogo(courseUpdate.getCourseLogo());
        course.setCourseStartDate(courseUpdate.getCourseStartDate());
        course.setCourseTitle(courseUpdate.getCourseTitle());
        course.setCourseType(courseUpdate.getCourseType());
        course.setDailyHours(courseUpdate.getDailyHours());
        course.setDailyRegister(courseUpdate.getDailyRegister());
        course.setDeliveryDateInAdministration(courseUpdate.getDeliveryDateInAdministration());
        course.setDisabled(courseUpdate.getDisabled());
        //course.setDocumentAttachment(courseUpdate.getDocumentAttachment());
        course.setEducationalTargetDescription(courseUpdate.getEducationalTargetDescription());
        course.setEntourageHours(courseUpdate.getEntourageHours());
        course.setExpiredReportingDate(courseUpdate.getExpiredReportingDate());
        course.setExternalReferenceCode(courseUpdate.getExternalReferenceCode());
        course.setFoundsTypeCourse(courseUpdate.getFoundsTypeCourse());
        course.setHeadquatersCourse(courseUpdate.getHeadquatersCourse());
        course.setInvoiceAuthorizationDate(courseUpdate.getInvoiceAuthorizationDate());
        course.setIssueTicket(courseUpdate.getIssueTicket());
        course.setLearnerType(courseUpdate.getLearnerType());
        course.setMinimumNumericOfParticipants(courseUpdate.getMinimumNumericOfParticipants());
        course.setMorningEndHour(courseUpdate.getMorningEndHour());
        course.setMorningStartHour(courseUpdate.getMorningStartHour());
        course.setNote(courseUpdate.getNote());
        course.setOrenatationHours(courseUpdate.getOrenatationHours());
        course.setPartFullTimeCourse(courseUpdate.getPartFullTimeCourse());
        course.setPartnerList(courseUpdate.getPartnerList());
        course.setPaymentModality(courseUpdate.getPaymentModality());
        course.setPlacementList(courseUpdate.getPlacementList());
        course.setPracticeHours(courseUpdate.getPracticeHours());
        course.setReceiptFTConfirmationDate(courseUpdate.getReceiptFTConfirmationDate());
        course.setRecipient(courseUpdate.getRecipient());
        course.setRecipientManagment(courseUpdate.getRecipientManagment());
        course.setReportNote(courseUpdate.getReportNote());
        course.setSendedCanceledProjectDate(courseUpdate.getSendedCanceledProjectDate());
        course.setSendedEletronicReportingDate(courseUpdate.getSendedEletronicReportingDate());
        course.setSendedLearnersFTDate(courseUpdate.getSendedLearnersFTDate());
        course.setSendedPaperReportingDate(courseUpdate.getSendedPaperReportingDate());
        course.setSendedProjectDate(courseUpdate.getSendedProjectDate());
        course.setSkilsAnalysisHours(courseUpdate.getSkilsAnalysisHours());
        course.setSpecialInitiatives(courseUpdate.getSpecialInitiatives());
        course.setSupplyModality(courseUpdate.getSupplyModality());
        course.setTeacherList(courseUpdate.getTeacherList());
        course.setTheoryHours(courseUpdate.getTheoryHours());
        course.setTicket_amount(courseUpdate.getTicket_amount());
        course.setTotalAmountWithoutFS(courseUpdate.getTotalAmountWithoutFS());
        course.setTotalHours(courseUpdate.getTotalHours());
        course.setTotalHoursTraining(courseUpdate.getTotalHoursTraining());
        course.setTotalPartnerCost(courseUpdate.getTotalPartnerCost());
        course.setTotalPartnerCostOnPercent(courseUpdate.getTotalPartnerCostOnPercent());
        course.setTradeUnionTeachingRequest(courseUpdate.getTradeUnionTeachingRequest());
        course.setVisitHours(courseUpdate.getVisitHours());

        courseRepository.save(course);

        return courseAdapter.adptCourseToCourseResponse(course);
    }

    @Modifying
    @Override
    public CourseResponseV1 patchCourse(CourseRequestV1 courseUpdateRequest, UUID courseId){

        Optional<CourseEntity> courseOpt = courseRepository.findById(courseId);
        if(!courseOpt.isPresent()){
            throw new NotFoundException("Course with id " + courseId+ " not found");
        }

        CourseEntity course = courseOpt.get();

        CourseEntity coursePatch = courseAdapter.adptCourseRequestToCourse(courseUpdateRequest);

        if(coursePatch.getActuatorSubject() != null)
            course.setActuatorSubject(coursePatch.getActuatorSubject());
        if(coursePatch.getAfternoonEndHour() != null)
            course.setAfternoonEndHour(coursePatch.getAfternoonEndHour());
        if(coursePatch.getAfternoonStatrHour() != null)
            course.setAfternoonStatrHour(coursePatch.getAfternoonStatrHour());
        if(coursePatch.getAmount_fin_security_capital() != null)
            course.setAmount_fin_security_capital(coursePatch.getAmount_fin_security_capital());
        if(coursePatch.getAmountAttendanceBenefits() != null)
            course.setAmountAttendanceBenefits(coursePatch.getAmountAttendanceBenefits());
        if(coursePatch.getAmountAutorizedFT() != null)
            course.setAmountAutorizedFT(coursePatch.getAmountAutorizedFT());
        if(coursePatch.getAmountAutorizedFTDate() != null)
            course.setAmountAutorizedFTDate(coursePatch.getAmountAutorizedFTDate());
        if(coursePatch.getAmountReportFT() != null)
            course.setAmountReportFT(coursePatch.getAmountReportFT());
        if(coursePatch.getAttendanceBenefits() != null)
            course.setAttendanceBenefits(coursePatch.getAttendanceBenefits());
        if(coursePatch.getAutProgetctFTRealizedDate() != null)
            course.setAutProgetctFTRealizedDate(coursePatch.getAutProgetctFTRealizedDate());
        if(coursePatch.getBusinessEmail() != null)
            course.setBusinessEmail(coursePatch.getBusinessEmail());
        if(coursePatch.getBusinessName() != null)
            course.setBusinessName(coursePatch.getBusinessName());
        if(coursePatch.getCertificateTypeCourse() != null)
            course.setCertificateTypeCourse(coursePatch.getCertificateTypeCourse());
        if(coursePatch.getCoachingHours() != null)
            course.setCoachingHours(coursePatch.getCoachingHours());
        if(coursePatch.getCommercialTaxableCommunicationDate() != null)
            course.setCommercialTaxableCommunicationDate(coursePatch.getCommercialTaxableCommunicationDate());
        if(coursePatch.getContentsArea() != null)
            course.setContentsArea(coursePatch.getContentsArea());
        if(coursePatch.getCosts() != null)
            course.setCosts(coursePatch.getCosts());
        if(coursePatch.getCourseCode() != null)
            course.setCourseCode(coursePatch.getCourseCode());
        if(coursePatch.getCourseDescription() != null)
            course.setCourseDescription(coursePatch.getCourseDescription());
        if(coursePatch.getCourseEndDate() != null)
            course.setCourseEndDate(coursePatch.getCourseEndDate());
        /*if(coursePatch.getCourseLogo() != null)
            course.setCourseLogo(coursePatch.getCourseLogo());*/
        if(coursePatch.getCourseStartDate() != null)
            course.setCourseStartDate(coursePatch.getCourseStartDate());
        if(coursePatch.getCourseTitle() != null)
            course.setCourseTitle(coursePatch.getCourseTitle());
        if(coursePatch.getCourseType() != null)
            course.setCourseType(coursePatch.getCourseType());
        if(coursePatch.getDailyHours() != null)
            course.setDailyHours(coursePatch.getDailyHours());
        if(coursePatch.getDailyRegister() != null)
            course.setDailyRegister(coursePatch.getDailyRegister());
        if(coursePatch.getDeliveryDateInAdministration() != null)
            course.setDeliveryDateInAdministration(coursePatch.getDeliveryDateInAdministration());
        if(coursePatch.getDisabled() != null)
            course.setDisabled(coursePatch.getDisabled());
        /*if(coursePatch.getDocumentAttachment() != null)
            course.setDocumentAttachment(coursePatch.getDocumentAttachment());*/
        if(coursePatch.getEducationalTargetDescription() != null)
            course.setEducationalTargetDescription(coursePatch.getEducationalTargetDescription());
        if(coursePatch.getEntourageHours() != null)
            course.setEntourageHours(coursePatch.getEntourageHours());
        if(coursePatch.getExpiredReportingDate() != null)
            course.setExpiredReportingDate(coursePatch.getExpiredReportingDate());
        if(coursePatch.getExternalReferenceCode() != null)
            course.setExternalReferenceCode(coursePatch.getExternalReferenceCode());
        if(coursePatch.getFoundsTypeCourse() != null)
            course.setFoundsTypeCourse(coursePatch.getFoundsTypeCourse());
        if(coursePatch.getHeadquatersCourse() != null)
            course.setHeadquatersCourse(coursePatch.getHeadquatersCourse());
        if(coursePatch.getInvoiceAuthorizationDate() != null)
            course.setInvoiceAuthorizationDate(coursePatch.getInvoiceAuthorizationDate());
        if(coursePatch.getIssueTicket() != null)
            course.setIssueTicket(coursePatch.getIssueTicket());
        if(coursePatch.getLearnerType() != null)
            course.setLearnerType(coursePatch.getLearnerType());
        if(coursePatch.getMinimumNumericOfParticipants() != null)
            course.setMinimumNumericOfParticipants(coursePatch.getMinimumNumericOfParticipants());
        if(coursePatch.getMorningEndHour() != null)
            course.setMorningEndHour(coursePatch.getMorningEndHour());
        if(coursePatch.getMorningStartHour() != null)
            course.setMorningStartHour(coursePatch.getMorningStartHour());
        if(coursePatch.getNote() != null)
            course.setNote(coursePatch.getNote());
        if(coursePatch.getOrenatationHours() != null)
            course.setOrenatationHours(coursePatch.getOrenatationHours());
        if(coursePatch.getPartFullTimeCourse() != null)
            course.setPartFullTimeCourse(coursePatch.getPartFullTimeCourse());
        if(coursePatch.getPartnerList() != null)
            course.setPartnerList(coursePatch.getPartnerList());
        if(coursePatch.getPaymentModality() != null)
            course.setPaymentModality(coursePatch.getPaymentModality());
        if(coursePatch.getPlacementList() != null)
            course.setPlacementList(coursePatch.getPlacementList());
        if(coursePatch.getPracticeHours() != null)
            course.setPracticeHours(coursePatch.getPracticeHours());
        if(coursePatch.getReceiptFTConfirmationDate() != null)
            course.setReceiptFTConfirmationDate(coursePatch.getReceiptFTConfirmationDate());
        if(coursePatch.getRecipient() != null)
            course.setRecipient(coursePatch.getRecipient());
        if(coursePatch.getRecipientManagment() != null)
            course.setRecipientManagment(coursePatch.getRecipientManagment());
        if(coursePatch.getReportNote() != null)
            course.setReportNote(coursePatch.getReportNote());
        if(coursePatch.getSendedCanceledProjectDate() != null)
            course.setSendedCanceledProjectDate(coursePatch.getSendedCanceledProjectDate());
        if(coursePatch.getSendedEletronicReportingDate() != null)
            course.setSendedEletronicReportingDate(coursePatch.getSendedEletronicReportingDate());
        if(coursePatch.getSendedLearnersFTDate() != null)
            course.setSendedLearnersFTDate(coursePatch.getSendedLearnersFTDate());
        if(coursePatch.getSendedPaperReportingDate() != null)
            course.setSendedPaperReportingDate(coursePatch.getSendedPaperReportingDate());
        if(coursePatch.getSendedProjectDate() != null)
            course.setSendedProjectDate(coursePatch.getSendedProjectDate());
        if(coursePatch.getSkilsAnalysisHours() != null)
            course.setSkilsAnalysisHours(coursePatch.getSkilsAnalysisHours());
        if(coursePatch.getSpecialInitiatives() != null)
            course.setSpecialInitiatives(coursePatch.getSpecialInitiatives());
        if(coursePatch.getSupplyModality() != null)
            course.setSupplyModality(coursePatch.getSupplyModality());
        if(coursePatch.getTeacherList() != null)
            course.setTeacherList(coursePatch.getTeacherList());
        if(coursePatch.getTheoryHours() != null)
            course.setTheoryHours(coursePatch.getTheoryHours());
        if(coursePatch.getTicket_amount() != null)
            course.setTicket_amount(coursePatch.getTicket_amount());
        if(coursePatch.getTotalAmountWithoutFS() != null)
            course.setTotalAmountWithoutFS(coursePatch.getTotalAmountWithoutFS());
        if(coursePatch.getTotalHours() != null)
            course.setTotalHours(coursePatch.getTotalHours());
        if(coursePatch.getTotalHoursTraining() != null)
            course.setTotalHoursTraining(coursePatch.getTotalHoursTraining());
        if(coursePatch.getTotalPartnerCost() != null)
            course.setTotalPartnerCost(coursePatch.getTotalPartnerCost());
        if(coursePatch.getTotalPartnerCostOnPercent() != null)
            course.setTotalPartnerCostOnPercent(coursePatch.getTotalPartnerCostOnPercent());
        if(coursePatch.getTradeUnionTeachingRequest() != null)
            course.setTradeUnionTeachingRequest(coursePatch.getTradeUnionTeachingRequest());
        if(coursePatch.getVisitHours() != null)
            course.setVisitHours(coursePatch.getVisitHours());

        courseRepository.save(course);

        return courseAdapter.adptCourseToCourseResponse(course);
    }

    @Override
    public void deleteCourse(UUID courseId){
        courseRepository.deleteById(courseId);
    }

    @Override
    public PaginationResponseV1<CourseResponseV1> getCoursePagination(int page, int pageSize, String courseTitle, String contentsArea, String learnerType, String supplyModality,
                                                                      String paymentModality, String foundsType, String courseStartDate, String partFullTime, String courseCode, String businessName,
                                                                      String courseType, String specialInitiatives){

        Pagination<CourseEntity> coursesPagination = new Pagination<>();


        List<CourseEntity> courseList = courseEMRepository.getCoursesForPagination(courseTitle, contentsArea, learnerType, supplyModality, paymentModality, foundsType,
              courseStartDate, partFullTime, courseCode, businessName, courseType, specialInitiatives);

        coursesPagination.setStats(new PaginationResponseV1.InfoPagination(courseList.size(), page, pageSize));

        int start = coursesPagination.getStats().getStartPage();
        int count = 0;
        List<CourseEntity> courseForPagination = new LinkedList<>();

        while (count < coursesPagination.getStats().getPageSize() && ((start - 1) + count) < courseList.size()) {
            courseForPagination.add((courseList.get((start - 1) + count)));
            count++;
        }

        coursesPagination.setItems(courseForPagination);

        return courseAdapter.adpCoursePaginationTooursePaginationResposne(coursesPagination);

    }


    @Override
    public CourseResponseV1 addCourseLogo(UploadRequestV1 logo, UUID idCourse) throws IOException{

        Optional<CourseEntity> courseOpt = courseRepository.findById(idCourse);
        if(!courseOpt.isPresent()){
            throw new NotFoundException("Course with id " + idCourse+ " not found");
        }

        CourseEntity course = courseOpt.get();
        logo.setResourceId(idCourse.toString());
        logo.setResourceType("course's logo");
        UploadResponseV1 response = filemanagerService.uploadFile(logo);
        course.setCourseLogo(attachmentAdapter.adptUploadResponseToAttachment(response));
        courseRepository.save(course);

        return courseAdapter.adptCourseToCourseResponse(course);
    }

    @Override
    public void deleteCourseLogo(UUID idCourse){

        Optional<CourseEntity> courseOpt = courseRepository.findById(idCourse);
        if(!courseOpt.isPresent()){
            throw new NotFoundException("Course with id " + idCourse+ " not found");
        }

        CourseEntity course = courseOpt.get();
        course.setCourseLogo(null);
        courseRepository.save(course);
    }

    @Override
    public CourseResponseV1 addCourseAttachments(List<UploadRequestV1> attachmentList, UUID idCourse) throws IOException{

        Optional<CourseEntity> courseOpt = courseRepository.findById(idCourse);
        if(!courseOpt.isPresent()){
            throw new NotFoundException("Course with id " + idCourse+ " not found");
        }

        CourseEntity course = courseOpt.get();
        List<Attachment> attachments = course.getDocumentsAttachment();
        if(attachments == null){
            attachments = new LinkedList<>();
        }

        UploadResponseV1 response;

        for (UploadRequestV1 att : attachmentList){

            att.setResourceId(idCourse.toString());
            att.setResourceType("document");
            response = filemanagerService.uploadFile(att);
            attachments.add(attachmentAdapter.adptUploadResponseToAttachment(response));
        }

        course.setDocumentsAttachment(attachments);
        courseRepository.save(course);
        return courseAdapter.adptCourseToCourseResponse(course);

    }

    @Override
    public void deleteCourseAttachments(UUID idCourse, List<UUID> attachmentList){

        Optional<CourseEntity> courseOpt = courseRepository.findById(idCourse);
        if(!courseOpt.isPresent()){
            throw new NotFoundException("Course with id " + idCourse+ " not found");
        }

        CourseEntity course = courseOpt.get();

        for (UUID uuidAttacment : attachmentList){

            Iterator<Attachment> iterator = course.getDocumentsAttachment().iterator();
            while (iterator.hasNext()){

                Attachment att = iterator.next();
                if(att.getFileManagerId().equalsIgnoreCase(uuidAttacment.toString())){
                    course.getDocumentsAttachment().remove(att);
                    break;
                }
            }
        }
        courseRepository.save(course);
    }


}
