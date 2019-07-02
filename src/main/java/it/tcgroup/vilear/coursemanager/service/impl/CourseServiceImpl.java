package it.tcgroup.vilear.coursemanager.service.impl;

import it.tcgroup.vilear.coursemanager.adapter.AttachmentAdapter;
import it.tcgroup.vilear.coursemanager.adapter.CourseAdapter;
import it.tcgroup.vilear.coursemanager.adapter.LearnerAdapter;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;
import it.tcgroup.vilear.coursemanager.common.exception.BadRequestException;
import it.tcgroup.vilear.coursemanager.common.exception.NotFoundException;
import it.tcgroup.vilear.coursemanager.common.util.DateUtil;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1.PlacementCourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.UploadRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.UploadResponseV1;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.LearnerEntity;
import it.tcgroup.vilear.coursemanager.entity.Pagination;
import it.tcgroup.vilear.coursemanager.entity.enumerated.*;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.CandidateCourse;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.PartnerCourse;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.PlacementCourse;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.RecipientManagmentCourse;
import it.tcgroup.vilear.coursemanager.repository.CourseEMRepository;
import it.tcgroup.vilear.coursemanager.repository.CourseRepository;
import it.tcgroup.vilear.coursemanager.repository.LearnerRepository;
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

    @Autowired
    private LearnerRepository learnerRepository;

    @Autowired
    private LearnerAdapter learnerAdapter;

    @Autowired
    private DateUtil dateUtil;


    @Override
    public IdResponseV1 insertCourse(CourseRequestV1 courseInsertRequest) {
        try{

            UUID idCourse = UUID.randomUUID();

            CourseEntity course = courseAdapter.adptCourseRequestToCourse(courseInsertRequest);

            if(course == null)
                return null;

            course.setId(idCourse);

            if(courseInsertRequest.getCourseLogo() != null &&
               courseInsertRequest.getCourseLogo().getFileContent() != null &&
               !courseInsertRequest.getCourseLogo().getFileContent().isEmpty()){

                UploadRequestV1 request = courseInsertRequest.getCourseLogo();
                request.setResourceId(idCourse.toString());
                UploadResponseV1 response = filemanagerService.uploadFile(request);
                course.setCourseLogo(attachmentAdapter.adptUploadResponseToAttachment(response));
            }


            if(course.getPlacementList()!= null) {
                for (PlacementCourse placement : course.getPlacementList()) {
                    if (!this.checkDateDifference(placement.getExpiredPlacementDate(),course.getCourseEndDate(),180))
                        throw new BadRequestException("ExpiredPlacementDate bad request.");
                }
            }
            if (!this.checkDateDifference(course.getSendedPaperReportingDate(),course.getCourseEndDate(),74))
                throw new BadRequestException("SendedPaperReportingDate bad request.");
            if (!this.checkDateDifference(course.getSendedEletronicReportingDate(),course.getDeliveryDateInAdministration(),60))
                throw new BadRequestException("SendedEletronicReportingDate bad request.");
            if (!this.checkDateDifference(course.getExpiredReportingDate(),course.getCourseEndDate(),60))
                throw new BadRequestException("ExpiredReportingDate bad request.");

            if( course.getAmountFinSecurityCapital()!=null && course.getAmountAutorizedFT()!=null && course.getTotalHours()!=null){

                if(course.getTotalHours().equals(0.0))
                    throw new BadRequestException("Total hours is zero. Impossibile to divide");

                Double total = ((course.getAmountAutorizedFT()-140)/course.getTotalHours())*4;

                if(Math.abs(course.getAmountFinSecurityCapital() - total) >= 0.01)
                    throw new BadRequestException("AmountFinSecurityCapital error.");
            }

            boolean found;
            if (course.getPartnerList() != null) {
                for (PartnerCourse partnerCourse : course.getPartnerList()) {
                    if(partnerCourse.getSubSupplierList() != null) {
                        for (PartnerCourse.SubSupplier subSupplier : partnerCourse.getSubSupplierList()) {
                            if(subSupplier.getSubSupplierService() != null) {
                                for (SupplyServicePartnerCourseEnum supplyServicePartnerCourseEnum : subSupplier.getSubSupplierService()) {
                                    found = false;
                                    if (partnerCourse.getSupplyServices() != null) {
                                        for (PartnerCourse.SupplierService supplierService : partnerCourse.getSupplyServices()) {
                                            if (supplierService.getSupplierService().compareTo(supplyServicePartnerCourseEnum) == 0) {
                                                found = true;
                                                break;
                                            }
                                        }
                                        if (!found)
                                            throw new BadRequestException("The sub supplier can only have the services of the partner");
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if(course.getTotalHours() != null){

                double totalHours = 0.0;

                if(course.getTheoryHours() == null)
                    course.setTheoryHours(0.0);

                if(course.getPracticeHours() == null)
                    course.setPracticeHours(0.0);

                if(course.getCoachingHours() == null)
                    course.setCoachingHours(0.0);

                if(course.getVisitHours() == null)
                    course.setVisitHours(0.0);

                if(course.getSkilsAnalysisHours() == null)
                    course.setSkilsAnalysisHours(0.0);


                totalHours = course.getTheoryHours() + course.getPracticeHours() + course.getCoachingHours()
                        + course.getVisitHours() + course.getSkilsAnalysisHours();
                if (Math.abs(totalHours - course.getTotalHours()) >= 0.01)
                    throw new BadRequestException("Total hours must be equals to  theory_hours + practice_hours + coaching_hours + visit_hours + skils_analysis_hours");

            }

            if(course.getRecipientManagment() != null){

                for (RecipientManagmentCourse recipient : course.getRecipientManagment())
                    this.checkNecessaryHours(recipient, course);
            }

            //Setto io automativamente le ore necessarie di formazione, calcolandole in base al numero di discenti partecipanti al corso
            if(course.getRecipientManagment() != null && course.getTotalHours() != null)
                course.setTotalHoursTraining(course.getTotalHours() * course.getRecipientManagment().size());

            if(course.getTotalHoursTraining() != null){

                    if(course.getTotalHours() == null || (course.getRecipientManagment() == null && course.getTotalHoursTraining()!=0))
                        throw new BadRequestException("TotalHoursTraining bad request. Incorrect total training hours");
                    if(Math.abs(course.getTotalHoursTraining() - course.getTotalHours() * course.getRecipientManagment().size()) >= 0.01)
                        throw new BadRequestException("TotalHoursTraining bad request. Incorrect total training hours");


            }

            if(course.getCandidateCourseList() != null && !course.getCandidateCourseList().isEmpty()){

                List<RecipientManagmentCourse> recipientList = course.getRecipientManagment();

                if(recipientList == null)
                    recipientList = new ArrayList<>();

                for(CandidateCourse att : course.getCandidateCourseList()){

                    if(att.getAccepted()){

                        Optional<LearnerEntity> optLearner = learnerRepository.findById(att.getId());
                        if(optLearner.isPresent()){

                            RecipientManagmentCourse recipient = new RecipientManagmentCourse();
                            recipient.setLearner(learnerAdapter.adptLearnerToLearnerDto(optLearner.get()));
                            recipientList.add(recipient);

                        }
                    }
                }
                course.setRecipientManagment(recipientList);
            }

            //Setto io automativamente il nuovo valore per le ore necessarie, di formazione, ricalcolandole in base al nuovo numero di discenti partecipanti al corso
            if(course.getRecipientManagment() != null)
                course.setTotalHoursTraining(course.getTotalHours() * course.getRecipientManagment().size());

            course.setStatus(CourseStatusEnum.IN_ATTESA_DI_PUBBLICAZIONE);
            courseRepository.save(course);

            return courseAdapter.adptCourseIdToCourseIdResponse(course);

        } catch (IOException e) {
            throw new BadParametersException("ERROR Upload file");
        }

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

        try {

            if(courseUpdateRequest == null)
                return null;


            if(courseUpdateRequest.getPlacementList()!= null) {
                for (PlacementCourseRequestV1 placement : courseUpdateRequest.getPlacementList()) {
                    if (!this.checkDateDifference(placement.getExpiredPlacementDate(),courseUpdateRequest.getCourseEndDate(),180))
                        throw new BadRequestException("ExpiredPlacementDate bad request.");
                }
            }
            if (!this.checkDateDifference(courseUpdateRequest.getSendedPaperReportingDate(),courseUpdateRequest.getCourseEndDate(),74))
                throw new BadRequestException("SendedPaperReportingDate bad request.");
            if (!this.checkDateDifference(courseUpdateRequest.getSendedEletronicReportingDate(),courseUpdateRequest.getDeliveryDateInAdministration(),60))
                throw new BadRequestException("SendedEletronicReportingDate bad request.");
            if (!this.checkDateDifference(courseUpdateRequest.getExpiredReportingDate(),courseUpdateRequest.getCourseEndDate(),60))
                throw new BadRequestException("ExpiredReportingDate bad request.");

            if( courseUpdateRequest.getAmountFinSecurityCapital()!=null && courseUpdateRequest.getAmountAutorizedFT()!=null && courseUpdateRequest.getTotalHours()!=null){
                if(courseUpdateRequest.getTotalHours().equals(0.0))
                    throw new BadRequestException("Total hours is zero. Impossibile to divide");

                Double total = ((courseUpdateRequest.getAmountAutorizedFT()-140)/courseUpdateRequest.getTotalHours())*4;
                if(Math.abs(courseUpdateRequest.getAmountFinSecurityCapital() - total) >= 0.01)
                    throw new BadRequestException("AmountFinSecurityCapital error. ");
            }
            Optional<CourseEntity> courseOpt = courseRepository.findById(courseId);

        if(!courseOpt.isPresent()){
            throw new NotFoundException("Course with id " + courseId+ " not found");
        }

        CourseEntity course = courseOpt.get();

        CourseEntity courseUpdate = courseAdapter.adptCourseRequestToCourse(courseUpdateRequest);

        if(courseUpdate != null) {

            if (courseUpdateRequest.getCourseLogo() != null &&
                    courseUpdateRequest.getCourseLogo().getFileContent() != null &&
                    !courseUpdateRequest.getCourseLogo().getFileContent().isEmpty()) {

                UploadRequestV1 request = courseUpdateRequest.getCourseLogo();
                request.setResourceId(course.getId().toString());
                UploadResponseV1 response = filemanagerService.uploadFile(request);
                course.setCourseLogo(attachmentAdapter.adptUploadResponseToAttachment(response));
            }
        }

        course.setActuatorSubject(courseUpdate.getActuatorSubject());
        course.setAfternoonEndHour(courseUpdate.getAfternoonEndHour());
        course.setAfternoonStartHour(courseUpdate.getAfternoonStartHour());
        course.setAmountFinSecurityCapital(courseUpdate.getAmountFinSecurityCapital ());
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
        course.setTicketAmount(courseUpdate.getTicketAmount());
        course.setNumberOfTickets(courseUpdate.getNumberOfTickets());
        course.setTotalAmountWithoutFS(courseUpdate.getTotalAmountWithoutFS());
        course.setTotalHours(courseUpdate.getTotalHours());
        course.setTotalHoursTraining(courseUpdate.getTotalHoursTraining());
        course.setTotalPartnerCost(courseUpdate.getTotalPartnerCost());
        course.setTotalPartnerCostOnPercent(courseUpdate.getTotalPartnerCostOnPercent());
        course.setTradeUnionTeachingRequest(courseUpdate.getTradeUnionTeachingRequest());
        course.setVisitHours(courseUpdate.getVisitHours());

        boolean found;
        if (course.getPartnerList() != null) {
            for (PartnerCourse partnerCourse : course.getPartnerList()) {
                if(partnerCourse.getSubSupplierList() != null) {
                    for (PartnerCourse.SubSupplier subSupplier : partnerCourse.getSubSupplierList()) {
                        if(subSupplier.getSubSupplierService() != null) {
                            for (SupplyServicePartnerCourseEnum supplyServicePartnerCourseEnum : subSupplier.getSubSupplierService()) {
                                found = false;
                                if (partnerCourse.getSupplyServices() != null) {
                                    for (PartnerCourse.SupplierService supplierService : partnerCourse.getSupplyServices()) {
                                        if (supplierService.getSupplierService().compareTo(supplyServicePartnerCourseEnum) == 0) {
                                            found = true;
                                            break;
                                        }
                                    }
                                    if (!found)
                                        throw new BadRequestException("The sub supplier can only have the services of the partner");
                                }
                            }
                        }
                    }
                }
            }
        }

        if(course.getRecipientManagment() != null){
            for (RecipientManagmentCourse recipient : course.getRecipientManagment())
                this.checkNecessaryHours(recipient,course);
        }

        if(course.getTotalHours() != null){
            Double totalHours;
            if(course.getTheoryHours() == null || course.getPracticeHours() == null
                    || course.getCoachingHours() == null || course.getVisitHours() == null ||
                    course.getSkilsAnalysisHours() == null)
                throw new BadRequestException("TotalHours bad request. Incorrect total hours");
            else{
                totalHours = course.getTheoryHours() + course.getPracticeHours() + course.getCoachingHours()
                        + course.getVisitHours() + course.getSkilsAnalysisHours();
                if (Math.abs(totalHours - course.getTotalHours()) >= 0.01)
                    throw new BadRequestException("Total hours must be equals to  theory_hours + practice_hours + coaching_hours + visit_hours + skils_analysis_hours");
            }
        }

        if(course.getTotalHoursTraining() != null){
            if(course.getTotalHours() == null || (course.getRecipientManagment() == null && course.getTotalHoursTraining()!=0))
                throw new BadRequestException("TotalHoursTraining bad request. Incorrect total training hours");
            if(Math.abs(course.getTotalHoursTraining() - course.getTotalHours() * course.getRecipientManagment().size()) >= 0.01)
                throw new BadRequestException("TotalHoursTraining bad request. Incorrect total training hours");
        }


        course.setRecipientManagment(courseUpdate.getRecipientManagment());

        if(courseUpdate.getCandidateCourseList() != null && !courseUpdate.getCandidateCourseList().isEmpty()){

            List<RecipientManagmentCourse> recipientList = courseUpdate.getRecipientManagment();

            if(recipientList == null)
                recipientList = new ArrayList<>();

            for(CandidateCourse att : courseUpdate.getCandidateCourseList()){

                if(att.getAccepted() && !att.getCandidated()){

                    Optional<LearnerEntity> optLearner = learnerRepository.findById(att.getId());
                    if(optLearner.isPresent()){

                        RecipientManagmentCourse recipient = new RecipientManagmentCourse();
                        recipient.setLearner(learnerAdapter.adptLearnerToLearnerDto(optLearner.get()));
                        recipientList.add(recipient);
                        att.setCandidated(true);

                    }
                }else if(!att.getAccepted() && att.getCandidated()){

                    course.getRecipientManagment().removeIf(learner -> learner.getLearner().getId().equalsIgnoreCase(att.getId().toString()));
                    att.setCandidated(false);

                }
            }

            course.setCandidateCourseList(courseUpdate.getCandidateCourseList());
            course.setRecipientManagment((recipientList));

            for(CandidateCourse att : course.getCandidateCourseList()){

                if(att.getCandidated() && att.getAccepted()){

                    boolean idDeleted = true;

                    Iterator<RecipientManagmentCourse> iterator = course.getRecipientManagment().iterator();

                    while(iterator.hasNext() && idDeleted){

                        RecipientManagmentCourse recipientManagment = iterator.next();

                        if(recipientManagment.getLearner().getId().equalsIgnoreCase(att.getId().toString()))
                            idDeleted = false;

                    }

                    if(idDeleted){
                        att.setAccepted(false);
                        att.setCandidated(false);
                    }

                }
            }

        }

        courseRepository.save(course);

        return courseAdapter.adptCourseToCourseResponse(course);

        } catch (IOException e) {
            throw new BadParametersException("ERROR Upload file");
        }
    }

    @Modifying
    @Override
    public CourseResponseV1 patchCourse(CourseRequestV1 courseUpdateRequest, UUID courseId){

        try {


            Optional<CourseEntity> courseOpt = courseRepository.findById(courseId);
            if (!courseOpt.isPresent()) {
                throw new NotFoundException("Course with id " + courseId + " not found");
            }

            CourseEntity course = courseOpt.get();

            CourseEntity coursePatch = courseAdapter.adptCourseRequestToCourse(courseUpdateRequest);

            if(coursePatch.getActuatorSubject() != null)
                course.setActuatorSubject(coursePatch.getActuatorSubject());

            if(coursePatch.getAfternoonEndHour() != null)
                course.setAfternoonEndHour(coursePatch.getAfternoonEndHour());

            if(coursePatch.getAfternoonStartHour() != null)
                course.setAfternoonStartHour(coursePatch.getAfternoonStartHour());

            if(coursePatch.getAmountFinSecurityCapital () != null){

                if(coursePatch.getAmountAutorizedFT() != null)
                    course.setAmountAutorizedFT(coursePatch.getAmountAutorizedFT());

                if(coursePatch.getTotalHours()!=null)
                    course.setTotalHours(coursePatch.getTotalHours());

                if(course.getAmountAutorizedFT()!=null && course.getTotalHours()!=null){
                    if(course.getTotalHours().equals(0.0))
                        throw new BadRequestException("Total hours is zero. Impossibile to divide");

                    Double total = ((course.getAmountAutorizedFT()-140)/course.getTotalHours())*4;

                    if(Math.abs(coursePatch.getAmountFinSecurityCapital() - total) >= 0.01)
                        throw new BadRequestException("AmountFinSecurityCapital error. ");
                }
                course.setAmountFinSecurityCapital (coursePatch.getAmountFinSecurityCapital ());
            }

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

            if(coursePatch.getCourseLogo() != null&&
               courseUpdateRequest.getCourseLogo().getFileContent() != null &&
               !courseUpdateRequest.getCourseLogo().getFileContent().isEmpty()) {

                UploadRequestV1 request = courseUpdateRequest.getCourseLogo();
                request.setResourceId(course.getId().toString());
                UploadResponseV1 response = filemanagerService.uploadFile(request);
                course.setCourseLogo(attachmentAdapter.adptUploadResponseToAttachment(response));
            }

            if (coursePatch.getCourseStartDate() != null)
                course.setCourseStartDate(coursePatch.getCourseStartDate());

            if (coursePatch.getCourseTitle() != null)
                course.setCourseTitle(coursePatch.getCourseTitle());

            if (coursePatch.getCourseType() != null)
                course.setCourseType(coursePatch.getCourseType());

            if (coursePatch.getDailyHours() != null)
                course.setDailyHours(coursePatch.getDailyHours());

            if (coursePatch.getDailyRegister() != null)
                course.setDailyRegister(coursePatch.getDailyRegister());

            if (coursePatch.getDeliveryDateInAdministration() != null)
                course.setDeliveryDateInAdministration(coursePatch.getDeliveryDateInAdministration());

            if (coursePatch.getDisabled() != null)
                course.setDisabled(coursePatch.getDisabled());

            /*if(coursePatch.getDocumentAttachment() != null)
                course.setDocumentAttachment(coursePatch.getDocumentAttachment());*/

            if(coursePatch.getEducationalTargetDescription() != null)
                course.setEducationalTargetDescription(coursePatch.getEducationalTargetDescription());

            if(coursePatch.getEntourageHours() != null)
                course.setEntourageHours(coursePatch.getEntourageHours());

            if(coursePatch.getExpiredReportingDate() != null){

                if (!this.checkDateDifference(coursePatch.getExpiredReportingDate(),course.getCourseEndDate(),60))
                    throw new BadRequestException("ExpiredReportingDate bad request.");

                course.setExpiredReportingDate(coursePatch.getExpiredReportingDate());
            }

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

            if(coursePatch.getPlacementList() != null){
                if(!coursePatch.getPlacementList().isEmpty()) {
                    for (PlacementCourse placement : coursePatch.getPlacementList()) {
                        if (!this.checkDateDifference(placement.getExpiredPlacementDate(),course.getCourseEndDate(),180))
                            throw new BadRequestException("ExpiredPlacementDate bad request.");
                    }
                }
                course.setPlacementList(coursePatch.getPlacementList());
            }

            if(coursePatch.getPracticeHours() != null)
                course.setPracticeHours(coursePatch.getPracticeHours());

            if(coursePatch.getReceiptFTConfirmationDate() != null)
                course.setReceiptFTConfirmationDate(coursePatch.getReceiptFTConfirmationDate());

            if(coursePatch.getReportNote() != null)
                course.setReportNote(coursePatch.getReportNote());

            if(coursePatch.getSendedCanceledProjectDate() != null)
                course.setSendedCanceledProjectDate(coursePatch.getSendedCanceledProjectDate());

            if(coursePatch.getSendedEletronicReportingDate() != null){

                if (!this.checkDateDifference(coursePatch.getSendedEletronicReportingDate(),course.getDeliveryDateInAdministration(),60))
                    throw new BadRequestException("SendedEletronicReportingDate bad request.");

                course.setSendedEletronicReportingDate(coursePatch.getSendedEletronicReportingDate());
            }

            if(coursePatch.getSendedLearnersFTDate() != null)
                course.setSendedLearnersFTDate(coursePatch.getSendedLearnersFTDate());

            if(coursePatch.getSendedPaperReportingDate() != null){

                if (!this.checkDateDifference(coursePatch.getSendedPaperReportingDate(),course.getCourseEndDate(),74))
                    throw new BadRequestException("SendedPaperReportingDate bad request.");

                course.setSendedPaperReportingDate(coursePatch.getSendedPaperReportingDate());
            }

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

            if(coursePatch.getTicketAmount() != null)
                course.setTicketAmount(coursePatch.getTicketAmount());

            if(coursePatch.getNumberOfTickets() != null)
                course.setNumberOfTickets(coursePatch.getNumberOfTickets());

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

            boolean found;
            if (course.getPartnerList() != null) {
                for (PartnerCourse partnerCourse : course.getPartnerList()) {
                    if(partnerCourse.getSubSupplierList() != null) {
                        for (PartnerCourse.SubSupplier subSupplier : partnerCourse.getSubSupplierList()) {
                            if(subSupplier.getSubSupplierService() != null) {
                                for (SupplyServicePartnerCourseEnum supplyServicePartnerCourseEnum : subSupplier.getSubSupplierService()) {
                                    found = false;
                                    if (partnerCourse.getSupplyServices() != null) {
                                        for (PartnerCourse.SupplierService supplierService : partnerCourse.getSupplyServices()) {
                                            if (supplierService.getSupplierService().compareTo(supplyServicePartnerCourseEnum) == 0) {
                                                found = true;
                                                break;
                                            }
                                        }
                                        if (!found)
                                            throw new BadRequestException("The sub supplier can only have the services of the partner");
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if(coursePatch.getRecipient() != null){
                course.setRecipient(coursePatch.getRecipient());
            }

            if(coursePatch.getRecipientManagment() != null){

                List<String> lernerRemoved = new ArrayList<>();
                List<CandidateCourse> candidateCourseList = course.getCandidateCourseList();

                if(coursePatch.getRecipientManagment().isEmpty()){

                    for(CandidateCourse attCandidate : candidateCourseList){
                            attCandidate.setAccepted(false);
                            attCandidate.setCandidated(false);
                    }

                }else {

                    //Lista di studenti ammessi al corso vecchia(quella già presente nel corso, che dovrà essere aggiornata)
                    List<RecipientManagmentCourse> lista = course.getRecipientManagment();

                    //Scorro tutta la lista degli ammessi al corso NUOVA(mandata con la patch)
                    for (RecipientManagmentCourse recipient : coursePatch.getRecipientManagment()) {

                        this.checkNecessaryHours(recipient, course);
                        lista.removeIf(att -> att.getLearner().getId().equalsIgnoreCase(recipient.getLearner().getId()));

                    }

                    for (RecipientManagmentCourse removLearner : lista) {
                        for (CandidateCourse attCandidate : candidateCourseList) {

                            if (attCandidate.getId().toString().equalsIgnoreCase(removLearner.getLearner().getId())) {
                                attCandidate.setAccepted(false);
                                attCandidate.setCandidated(false);
                            }
                        }
                    }
                }

                course.setRecipientManagment(coursePatch.getRecipientManagment());
                //Setto io automativamente il nuovo valore per le ore necessarie, di formazione, ricalcolandole in base al nuovo numero di discenti partecipanti al corso
                course.setTotalHoursTraining(course.getTotalHours() * course.getRecipientManagment().size());
                course.setCandidateCourseList(candidateCourseList);
            }

            if(course.getTotalHours() != null){

                double totalHours;

                if(course.getTheoryHours() == null || course.getPracticeHours() == null
                        || course.getCoachingHours() == null || course.getVisitHours() == null ||
                        course.getSkilsAnalysisHours() == null)

                    throw new BadRequestException("TotalHours bad request. Incorrect total hours");

                else{

                    totalHours = course.getTheoryHours() + course.getPracticeHours() + course.getCoachingHours()
                            + course.getVisitHours() + course.getSkilsAnalysisHours();

                    if (Math.abs(totalHours - course.getTotalHours()) >= 0.01)
                        throw new BadRequestException("Total hours must be equals to  theory_hours + practice_hours + coaching_hours + visit_hours + skils_analysis_hours");
                }
            }

            if(course.getTotalHoursTraining() != null){

                if(Math.abs(course.getTotalHoursTraining() - course.getTotalHours() * course.getRecipientManagment().size()) >= 0.01){
                    throw new BadRequestException("TotalHoursTraining bad request. Incorrect total training hours");

                }
            }


            if(coursePatch.getCandidateCourseList() != null && !coursePatch.getCandidateCourseList().isEmpty()){

                List<RecipientManagmentCourse> recipientList = coursePatch.getRecipientManagment();

                if(recipientList == null)
                    recipientList = new ArrayList<>();

                for(CandidateCourse att : coursePatch.getCandidateCourseList()){

                    if(att.getAccepted() && !att.getCandidated()){

                        Optional<LearnerEntity> optLearner = learnerRepository.findById(att.getId());

                        if(optLearner.isPresent()){

                            RecipientManagmentCourse recipient = new RecipientManagmentCourse();
                            recipient.setLearner(learnerAdapter.adptLearnerToLearnerDto(optLearner.get()));
                            recipientList.add(recipient);
                            att.setCandidated(true);

                        }
                    }else if(!att.getAccepted() && att.getCandidated()){

                        recipientList.removeIf(learner -> learner.getLearner().getId().equalsIgnoreCase(att.getId().toString()));
                        att.setCandidated(false);
                    }
                }

                course.setCandidateCourseList(coursePatch.getCandidateCourseList());
                course.setRecipientManagment((recipientList));
                course.setTotalHoursTraining(course.getTotalHours() * course.getRecipientManagment().size());
            }

            courseRepository.save(course);

            return courseAdapter.adptCourseToCourseResponse(course);

        }catch (IOException e) {
            throw new BadParametersException("ERROR Upload file");
        }
    }

    @Override
    public void deleteCourse(UUID courseId){

        Optional<CourseEntity> optCourse = courseRepository.findById(courseId);

        if(optCourse.isPresent())
            courseRepository.delete(optCourse.get());
        else
            throw new NotFoundException("Course with uuid: " + courseId + " isn't present ");
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
    public List<Attachment> addCourseAttachments(List<UploadRequestV1> attachmentList, UUID idCourse) throws IOException{

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

        List<Attachment> actualAttachment = new LinkedList<>();

        for (UploadRequestV1 att : attachmentList){

            att.setResourceId(idCourse.toString());
            att.setResourceType("document");
            response = filemanagerService.uploadFile(att);
            attachments.add(attachmentAdapter.adptUploadResponseToAttachment(response));
            actualAttachment.add(attachmentAdapter.adptUploadResponseToAttachment(response));
        }

        course.setDocumentsAttachment(attachments);
        courseRepository.save(course);
        return actualAttachment;

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

    @Override
    public CourseResponseV1 patchCourseStatus(UUID idCourse) {
        Optional<CourseEntity> courseOpt = courseRepository.findById(idCourse);
        if(!courseOpt.isPresent()){
            throw new NotFoundException("Course with id " + idCourse+ " not found");
        }
        CourseEntity course = courseOpt.get();

        if(course.getStatus().toValue().equalsIgnoreCase(CourseStatusEnum.IN_ATTESA_DI_PUBBLICAZIONE.toValue()) &&
            course.getCourseType() !=null  && course.getSupplyModality() != null && course.getContentsArea() != null &&
            course.getActuatorSubject() != null  && course.getFoundsTypeCourse() != null &&
            course.getCourseCode() != null && course.getCourseTitle() != null && course.getCourseDescription() != null &&
            course.getEducationalTargetDescription() != null && course.getHeadquatersCourse() != null && !course.getHeadquatersCourse().isEmpty() &&
            course.getCourseStartDate() != null && course.getCourseEndDate() != null && course.getTheoryHours() != null && course.getPracticeHours() != null &&
            course.getOrenatationHours() != null && course.getCoachingHours() != null && course.getTotalHours() != null && course.getTotalHoursTraining() != null &&
            course.getMorningStartHour() != null && course.getMorningEndHour() != null && course.getAfternoonStartHour() != null && course.getAfternoonEndHour() != null &&
            course.getMinimumNumericOfParticipants() != null && course.getAmountReportFT() != null && course.getAmountAutorizedFT() != null && course.getTeacherList() != null &&
            !course.getTeacherList().isEmpty() && course.getRecipientManagment() != null && !course.getRecipientManagment().isEmpty() && course.getPlacementList() != null &&
            !course.getPlacementList().isEmpty() && course.getPlacementList().get(0).getHiringDate() != null &&
            course.getPlacementList().get(0).getMissionHours() != null && course.getPlacementList().get(0).getBonusAmount() != null)
                course.setStatus(CourseStatusEnum.PUBBLICATO);
            courseRepository.save(course);

        return courseAdapter.adptCourseToCourseResponse(course);

    }

    private boolean checkDateDifference(Date date, Date dateToAddDays, Integer days){

        if(date!=null) {
            if (dateToAddDays != null) {

                Calendar dateCalendar = Calendar.getInstance();
                dateCalendar.setTime(date);
                Calendar dateToAddDaysCalendar = Calendar.getInstance();
                dateToAddDaysCalendar.setTime(dateUtil.addDays(dateToAddDays, days));
                Boolean sameDay = (dateCalendar.get(Calendar.YEAR) == dateToAddDaysCalendar.get(Calendar.YEAR) &&
                        dateCalendar.get(Calendar.MONTH) == dateToAddDaysCalendar.get(Calendar.MONTH) &&
                        dateCalendar.get(Calendar.DAY_OF_MONTH) == dateToAddDaysCalendar.get(Calendar.DAY_OF_MONTH));
                if (sameDay)
                    return true;
                else return false;

            } else
                throw new BadRequestException("Bad request: La data per effettuare i controlli è null");

        }
        return true;
    }

    private boolean checkNecessaryHours(RecipientManagmentCourse recipient, CourseEntity course){

        double necessaryHours, specSecHours;

        //IL CAMPO SARA' OBBLIGATORIO
        if(recipient.getNecessaryHours() != null) {

            if (course.getTotalHours() == null)
                throw new BadRequestException("NecessaryHours bad request. Incorrect course total_hours");

            necessaryHours = course.getTotalHours() * 0.7;
            //se discente ha l'esonero sicurezza generale allora si sottraggono le 4 ore (2.8 = 70% di 4)
            if (recipient.getExonerationGeneralSecurity() != null && recipient.getExonerationGeneralSecurity())
                necessaryHours -= 2.8;
            //se discente ha l'esonero diritti e doveri allora si sottraggono le 4 ore (2.8 = 70% di 4)
            if (recipient.getExonerationRightsAndDuties() != null && recipient.getExonerationRightsAndDuties())
                necessaryHours -= 2.8;
            //se discente ha modulo sicurezza generale allora si sottraggono le 4 ore al 70% (2.8 = 70% di 4)
            //e si aggiungono le 4 ore al 90% (3.6 = 90% di 4)
            if (recipient.getGeneralSecurityModule() != null && recipient.getGeneralSecurityModule())
                necessaryHours = necessaryHours - 2.8 + 3.6;
            //se discente ha modulo sicurezza specifica allora si sottraggono le ore di sicurezza specifica al 70%
            //e si aggiungono al 90%
            if (recipient.getSpecificSecurityModule() != null && recipient.getSpecificSecurityModule()) {
                if (recipient.getSpecificationSsecurityExonerate() != null) {
                    specSecHours = Double.valueOf(recipient.getSpecificationSsecurityExonerate().getValue());
                    necessaryHours = necessaryHours - (specSecHours * 0.7) + (specSecHours * 0.9);
                } else
                    necessaryHours = necessaryHours - 2.8 + 3.6;
            }
            System.out.println("ore necessarie = " + necessaryHours);
            if (Math.abs(recipient.getNecessaryHours() - necessaryHours) >= 0.01)
                throw new BadRequestException("NecessaryHours bad request. Incorrect total necessaryHours, for learner with id:" + recipient.getLearner().getId() +", the necessary_hours must be equals to " + necessaryHours);

        }

        return true;
    }

    private CourseEntity checkCandidateLearner(CourseEntity course, CourseEntity courseUpdate){

        if(courseUpdate.getCandidateCourseList() != null && !courseUpdate.getCandidateCourseList().isEmpty()){

            List<RecipientManagmentCourse> recipientList = courseUpdate.getRecipientManagment();

            if(recipientList == null)
                recipientList = new ArrayList<>();

            for(CandidateCourse att : courseUpdate.getCandidateCourseList()){

                if(att.getAccepted() && !att.getCandidated()){

                    Optional<LearnerEntity> optLearner = learnerRepository.findById(att.getId());
                    if(optLearner.isPresent()){

                        RecipientManagmentCourse recipient = new RecipientManagmentCourse();
                        recipient.setLearner(learnerAdapter.adptLearnerToLearnerDto(optLearner.get()));
                        recipientList.add(recipient);
                        att.setCandidated(true);

                    }
                }else if(!att.getAccepted() && att.getCandidated()){

                    course.getRecipientManagment().removeIf(learner -> learner.getLearner().getId().equalsIgnoreCase(att.getId().toString()));
                    att.setCandidated(false);

                }
            }

            course.setCandidateCourseList(courseUpdate.getCandidateCourseList());
            course.setRecipientManagment((recipientList));

            for(CandidateCourse att : course.getCandidateCourseList()){

                if(att.getCandidated() && att.getAccepted()){

                    boolean idDeleted = true;

                    Iterator<RecipientManagmentCourse> iterator = course.getRecipientManagment().iterator();

                    while(iterator.hasNext() && idDeleted){

                        RecipientManagmentCourse recipientManagment = iterator.next();

                        if(recipientManagment.getLearner().getId().equalsIgnoreCase(att.getId().toString()))
                            idDeleted = false;

                    }

                    if(idDeleted){
                        att.setAccepted(false);
                        att.setCandidated(false);
                    }

                }
            }
        }

        return course;
    }
}
