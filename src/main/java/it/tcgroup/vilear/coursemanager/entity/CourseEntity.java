package it.tcgroup.vilear.coursemanager.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.tcgroup.vilear.coursemanager.entity.dto.PartnerDto;
import it.tcgroup.vilear.coursemanager.entity.enumerated.*;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.*;
import it.tcgroup.vilear.coursemanager.entity.jsonb.dataType.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.type.CalendarTimeType;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.UUID;
import java.util.*;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "hibernate_lazy_initializer", "handler"})
@Table(name = "course")
@TypeDefs({
        @TypeDef(name = "JsonDataAddressCourseType", typeClass = JsonDataAddressCourseType.class),
        @TypeDef(name = "JsonDataRecipientManagmentCourseType", typeClass = JsonDataRecipientManagmentCourseType.class),
        @TypeDef(name = "JsonDataPartnerCourseType", typeClass = JsonDataPartnerCourseType.class),
        @TypeDef(name = "JsonDataTeacherCourseType", typeClass = JsonDataTeacherCourseType.class),
        @TypeDef(name = "JsonDataPlacementCourseType", typeClass = JsonDataPlacementCourseType.class),
        @TypeDef(name = "JavaDataPartnerDtoType", typeClass = JavaDataPartnerDtoType.class),
        @TypeDef(name = "JsonDataAttachmentType", typeClass = JsonDataAttachmentType.class),
        @TypeDef(name = "JsonDataAttachmentListType", typeClass = JsonDataAttachmentListType.class),
})
public class CourseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "course_title")
    private String courseTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CourseStatusEnum status;

    @Enumerated(EnumType.STRING)
    @Column(name = "contents_area")
    private ContentsAreaCourseEnum contentsArea;

    @Enumerated(EnumType.STRING)
    @Column(name = "learner_type")
    private LearnerTypeCourseEnum learnerType;

    @Enumerated(EnumType.STRING)
    @Column(name = "supply_modality")
    private SupplyModalityCourseEnum supplyModality;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_modality")
    private PaymentModalityEnum paymentModality;

    @Column(name = "costs")
    private Double costs;

    @Enumerated(EnumType.STRING)
    @Column(name = "founds_type")
    private FoundsTypeCourseEnum foundsTypeCourse;

    @Column(name = "educational_target_description")
    private String educationalTargetDescription;

    @Column(name = "course_description")
    private String courseDescription;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "course_start_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date courseStartDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "course_end_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date courseEndDate;

    @Column(name = "theory_hours")
    private Double theoryHours;

    @Column(name = "practice_hours")
    private Double practiceHours;

    @Column(name = "coaching_hours")
    private Double coachingHours;

    @Column(name = "visit_hours")
    private Double visitHours;

    @Column(name = "skils_analysis_hours")
    private Double skilsAnalysisHours;

    @Column(name = "total_hours")
    private Double totalHours;

    @Column(name = "total_hours_training")
    private Double totalHoursTraining;

    @Column(name = "daily_hours")
    private Double dailyHours;

    @Enumerated(EnumType.STRING)
    @Column(name = "certificate_type")
    private CertificateTypeCourseEnum certificateTypeCourse;

    @Column(name = "minimum_numeric_of_participants")
    private Integer minimumNumericOfParticipants;

    @Column(name = "disabled")
    private Boolean disabled;

    @Type(type = "JsonDataAttachmentType")
    @Column(name = "course_logo")
    private Attachment courseLogo;

    @Enumerated(EnumType.STRING)
    @Column(name = "part_full_time")
    private PartFullTimeCourseEnum partFullTimeCourse;

    @Temporal(value = TemporalType.TIME)
    @Column(name = "morning_start_hour")
    private Date morningStartHour;

    @Temporal(value = TemporalType.TIME)
    @Column(name = "morning_end_hour")
    private Date morningEndHour;

    @Temporal(value = TemporalType.TIME)
    @Column(name = "afternoon_start_hour")
    private Date afternoonStartHour;

    @Temporal(value = TemporalType.TIME)
    @Column(name = "afternoon_end_hour")
    private Date afternoonEndHour;

    @Type(type = "JavaDataPartnerDtoType")
    @Column(name = "actuator_subject")
    private PartnerDto actuatorSubject;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "business_email")
    private String businessEmail;

    @Column(name = "external_reference_code")
    private String externalReferenceCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_type")
    private CourseTypeEnum courseType;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "sended_project_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date sendedProjectDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "receipt_FT_confirmation_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date receiptFTConfirmationDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "sended_canceled_project_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date sendedCanceledProjectDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "aut_progetct_FT_realized_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date autProgetctFTRealizedDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "sended_learners_FT_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date sendedLearnersFTDate;

    @Column(name = "entourage_hours")
    private Double entourageHours;

    @Column(name = "orenatation_hours")
    private Double orenatationHours;

    @Enumerated(EnumType.STRING)
    @Column(name = "special_initiatives")
    private SpecialInitiativesCourseEnum specialInitiatives;

    @Column(name = "trade_union_teaching_request")
    private Boolean tradeUnionTeachingRequest;

    @Column(name = "note")
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(name = "recipient")
    private RecipientTypeLearnerCourseEnum recipient;

    @Column(name = "issue_ticket")
    private Boolean issueTicket;

    @Column(name = "ticket_amount")
    private Double ticketAmount;

    @Column(name = "number_of_tickets")
    private Integer numberOfTickets;

    @Column(name = "attendance_benefits")
    private Boolean attendanceBenefits;

    @Column(name = "amount_attendance_benefits")
    private Double amountAttendanceBenefits;

    @Column(name = "amount_report_FT")
    private Double amountReportFT;

    @Column(name = "amount_fin_security_capital")
    private Double amountFinSecurityCapital;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "amount_autorized_FT_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date amountAutorizedFTDate;

    @Column(name = "amount_autorized_FT")
    private Double amountAutorizedFT;

    @Column(name = "total_partner_cost")
    private Double totalPartnerCost;

    @Column(name = "total_partner_cost_on_percent")
    private Double totalPartnerCostOnPercent;

    @Column(name = "total_amount_without_FS")
    private Double totalAmountWithoutFS;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "sended_paper_reporting_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date sendedPaperReportingDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "sended_eletronic_reporting_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date sendedEletronicReportingDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "expired_reporting_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date expiredReportingDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "invoice_authorization_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date invoiceAuthorizationDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "delivery_date_in_administration", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date deliveryDateInAdministration;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "commercial_taxable_communication_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date commercialTaxableCommunicationDate;

    @Column(name = "report_note")
    private String reportNote;

    @Column(name = "daily_register")
    private String dailyRegister;

    @Type(type = "JsonDataAddressCourseType")
    @Column(name = "headquaters_course")
    private List<AddressCourse> headquatersCourse;

    @Type(type = "JsonDataRecipientManagmentCourseType")
    @Column(name = "recipient_managment")
    private List<RecipientManagmentCourse> recipientManagment;

    @Type(type = "JsonDataPartnerCourseType")
    @Column(name = "partners")
    private List<PartnerCourse> partnerList;

    @Type(type = "JsonDataTeacherCourseType")
    @Column(name = "teachers")
    private List<TeacherCourse> teacherList;

    @Type(type = "JsonDataPlacementCourseType")
    @Column(name = "placement")
    private List<PlacementCourse> placementList;

    @Type(type = "JsonDataAttachmentListType")
    @Column(name = "document_attachment")
    private List<Attachment> documentsAttachment;

    public CourseEntity() {
    }

    public CourseEntity(String courseTitle, CourseStatusEnum status, ContentsAreaCourseEnum contentsArea, LearnerTypeCourseEnum learnerType, SupplyModalityCourseEnum supplyModality, PaymentModalityEnum paymentModality, Double costs, FoundsTypeCourseEnum foundsTypeCourse, String educationalTargetDescription, String courseDescription, Date courseStartDate, Date courseEndDate, Double theoryHours, Double practiceHours, Double coachingHours, Double visitHours, Double skilsAnalysisHours, Double totalHours, Double totalHoursTraining, Double dailyHours, CertificateTypeCourseEnum certificateTypeCourse, Integer minimumNumericOfParticipants, Boolean disabled, Attachment courseLogo, PartFullTimeCourseEnum partFullTimeCourse, Date morningStartHour, Date morningEndHour, Date afternoonStartHour, Date afternoonEndHour, PartnerDto actuatorSubject, String courseCode, String businessName, String businessEmail, String externalReferenceCode, CourseTypeEnum courseType, Date sendedProjectDate, Date receiptFTConfirmationDate, Date sendedCanceledProjectDate, Date autProgetctFTRealizedDate, Date sendedLearnersFTDate, Double entourageHours, Double orenatationHours, SpecialInitiativesCourseEnum specialInitiatives, Boolean tradeUnionTeachingRequest, String note, RecipientTypeLearnerCourseEnum recipient, Boolean issueTicket, Double ticketAmount, Integer numberOfTickets, Boolean attendanceBenefits, Double amountAttendanceBenefits, Double amountReportFT, Double amountFinSecurityCapital, Date amountAutorizedFTDate, Double amountAutorizedFT, Double totalPartnerCost, Double totalPartnerCostOnPercent, Double totalAmountWithoutFS, Date sendedPaperReportingDate, Date sendedEletronicReportingDate, Date expiredReportingDate, Date invoiceAuthorizationDate, Date deliveryDateInAdministration, Date commercialTaxableCommunicationDate, String reportNote, String dailyRegister, List<AddressCourse> headquatersCourse, List<RecipientManagmentCourse> recipientManagment, List<PartnerCourse> partnerList, List<TeacherCourse> teacherList, List<PlacementCourse> placementList, List<Attachment> documentsAttachment) {
        this.courseTitle = courseTitle;
        this.status = status;
        this.contentsArea = contentsArea;
        this.learnerType = learnerType;
        this.supplyModality = supplyModality;
        this.paymentModality = paymentModality;
        this.costs = costs;
        this.foundsTypeCourse = foundsTypeCourse;
        this.educationalTargetDescription = educationalTargetDescription;
        this.courseDescription = courseDescription;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.theoryHours = theoryHours;
        this.practiceHours = practiceHours;
        this.coachingHours = coachingHours;
        this.visitHours = visitHours;
        this.skilsAnalysisHours = skilsAnalysisHours;
        this.totalHours = totalHours;
        this.totalHoursTraining = totalHoursTraining;
        this.dailyHours = dailyHours;
        this.certificateTypeCourse = certificateTypeCourse;
        this.minimumNumericOfParticipants = minimumNumericOfParticipants;
        this.disabled = disabled;
        this.courseLogo = courseLogo;
        this.partFullTimeCourse = partFullTimeCourse;
        this.morningStartHour = morningStartHour;
        this.morningEndHour = morningEndHour;
        this.afternoonStartHour = afternoonStartHour;
        this.afternoonEndHour = afternoonEndHour;
        this.actuatorSubject = actuatorSubject;
        this.courseCode = courseCode;
        this.businessName = businessName;
        this.businessEmail = businessEmail;
        this.externalReferenceCode = externalReferenceCode;
        this.courseType = courseType;
        this.sendedProjectDate = sendedProjectDate;
        this.receiptFTConfirmationDate = receiptFTConfirmationDate;
        this.sendedCanceledProjectDate = sendedCanceledProjectDate;
        this.autProgetctFTRealizedDate = autProgetctFTRealizedDate;
        this.sendedLearnersFTDate = sendedLearnersFTDate;
        this.entourageHours = entourageHours;
        this.orenatationHours = orenatationHours;
        this.specialInitiatives = specialInitiatives;
        this.tradeUnionTeachingRequest = tradeUnionTeachingRequest;
        this.note = note;
        this.recipient = recipient;
        this.issueTicket = issueTicket;
        this.ticketAmount = ticketAmount;
        this.numberOfTickets = numberOfTickets;
        this.attendanceBenefits = attendanceBenefits;
        this.amountAttendanceBenefits = amountAttendanceBenefits;
        this.amountReportFT = amountReportFT;
        this.amountFinSecurityCapital = amountFinSecurityCapital;
        this.amountAutorizedFTDate = amountAutorizedFTDate;
        this.amountAutorizedFT = amountAutorizedFT;
        this.totalPartnerCost = totalPartnerCost;
        this.totalPartnerCostOnPercent = totalPartnerCostOnPercent;
        this.totalAmountWithoutFS = totalAmountWithoutFS;
        this.sendedPaperReportingDate = sendedPaperReportingDate;
        this.sendedEletronicReportingDate = sendedEletronicReportingDate;
        this.expiredReportingDate = expiredReportingDate;
        this.invoiceAuthorizationDate = invoiceAuthorizationDate;
        this.deliveryDateInAdministration = deliveryDateInAdministration;
        this.commercialTaxableCommunicationDate = commercialTaxableCommunicationDate;
        this.reportNote = reportNote;
        this.dailyRegister = dailyRegister;
        this.headquatersCourse = headquatersCourse;
        this.recipientManagment = recipientManagment;
        this.partnerList = partnerList;
        this.teacherList = teacherList;
        this.placementList = placementList;
        this.documentsAttachment = documentsAttachment;
    }

    public CourseStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CourseStatusEnum status) {
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public ContentsAreaCourseEnum getContentsArea() {
        return contentsArea;
    }

    public void setContentsArea(ContentsAreaCourseEnum contentsArea) {
        this.contentsArea = contentsArea;
    }

    public LearnerTypeCourseEnum getLearnerType() {
        return learnerType;
    }

    public void setLearnerType(LearnerTypeCourseEnum learnerType) {
        this.learnerType = learnerType;
    }

    public SupplyModalityCourseEnum getSupplyModality() {
        return supplyModality;
    }

    public void setSupplyModality(SupplyModalityCourseEnum supplyModality) {
        this.supplyModality = supplyModality;
    }

    public PaymentModalityEnum getPaymentModality() {
        return paymentModality;
    }

    public void setPaymentModality(PaymentModalityEnum paymentModality) {
        this.paymentModality = paymentModality;
    }

    public Double getCosts() {
        return costs;
    }

    public void setCosts(Double costs) {
        this.costs = costs;
    }

    public FoundsTypeCourseEnum getFoundsTypeCourse() {
        return foundsTypeCourse;
    }

    public void setFoundsTypeCourse(FoundsTypeCourseEnum foundsTypeCourse) {
        this.foundsTypeCourse = foundsTypeCourse;
    }

    public String getEducationalTargetDescription() {
        return educationalTargetDescription;
    }

    public void setEducationalTargetDescription(String educationalTargetDescription) {
        this.educationalTargetDescription = educationalTargetDescription;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Date getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(Date courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public Date getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(Date courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public Double getTheoryHours() {
        return theoryHours;
    }

    public void setTheoryHours(Double theoryHours) {
        this.theoryHours = theoryHours;
    }

    public Double getPracticeHours() {
        return practiceHours;
    }

    public void setPracticeHours(Double practiceHours) {
        this.practiceHours = practiceHours;
    }

    public Double getCoachingHours() {
        return coachingHours;
    }

    public void setCoachingHours(Double coachingHours) {
        this.coachingHours = coachingHours;
    }

    public Double getVisitHours() {
        return visitHours;
    }

    public void setVisitHours(Double visitHours) {
        this.visitHours = visitHours;
    }

    public Double getSkilsAnalysisHours() {
        return skilsAnalysisHours;
    }

    public void setSkilsAnalysisHours(Double skilsAnalysisHours) {
        this.skilsAnalysisHours = skilsAnalysisHours;
    }

    public Double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
    }

    public Double getTotalHoursTraining() {
        return totalHoursTraining;
    }

    public void setTotalHoursTraining(Double totalHoursTraining) {
        this.totalHoursTraining = totalHoursTraining;
    }

    public Double getDailyHours() {
        return dailyHours;
    }

    public void setDailyHours(Double dailyHours) {
        this.dailyHours = dailyHours;
    }

    public CertificateTypeCourseEnum getCertificateTypeCourse() {
        return certificateTypeCourse;
    }

    public void setCertificateTypeCourse(CertificateTypeCourseEnum certificateTypeCourse) {
        this.certificateTypeCourse = certificateTypeCourse;
    }

    public Integer getMinimumNumericOfParticipants() {
        return minimumNumericOfParticipants;
    }

    public void setMinimumNumericOfParticipants(Integer minimumNumericOfParticipants) {
        this.minimumNumericOfParticipants = minimumNumericOfParticipants;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Attachment getCourseLogo() {
        return courseLogo;
    }

    public void setCourseLogo(Attachment courseLogo) {
        this.courseLogo = courseLogo;
    }

    public PartFullTimeCourseEnum getPartFullTimeCourse() {
        return partFullTimeCourse;
    }

    public void setPartFullTimeCourse(PartFullTimeCourseEnum partFullTimeCourse) {
        this.partFullTimeCourse = partFullTimeCourse;
    }

    public Date getMorningStartHour() {
        return morningStartHour;
    }

    public void setMorningStartHour(Date morningStartHour) {
        this.morningStartHour = morningStartHour;
    }

    public Date getMorningEndHour() {
        return morningEndHour;
    }

    public void setMorningEndHour(Date morningEndHour) {
        this.morningEndHour = morningEndHour;
    }

    public Date getAfternoonEndHour() {
        return afternoonEndHour;
    }

    public void setAfternoonEndHour(Date afternoonEndHour) {
        this.afternoonEndHour = afternoonEndHour;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public String getExternalReferenceCode() {
        return externalReferenceCode;
    }

    public void setExternalReferenceCode(String externalReferenceCode) {
        this.externalReferenceCode = externalReferenceCode;
    }

    public CourseTypeEnum getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseTypeEnum courseType) {
        this.courseType = courseType;
    }

    public Date getSendedProjectDate() {
        return sendedProjectDate;
    }

    public void setSendedProjectDate(Date sendedProjectDate) {
        this.sendedProjectDate = sendedProjectDate;
    }

    public Date getReceiptFTConfirmationDate() {
        return receiptFTConfirmationDate;
    }

    public void setReceiptFTConfirmationDate(Date receiptFTConfirmationDate) {
        this.receiptFTConfirmationDate = receiptFTConfirmationDate;
    }

    public Date getSendedCanceledProjectDate() {
        return sendedCanceledProjectDate;
    }

    public void setSendedCanceledProjectDate(Date sendedCanceledProjectDate) {
        this.sendedCanceledProjectDate = sendedCanceledProjectDate;
    }

    public Date getAutProgetctFTRealizedDate() {
        return autProgetctFTRealizedDate;
    }

    public void setAutProgetctFTRealizedDate(Date autProgetctFTRealizedDate) {
        this.autProgetctFTRealizedDate = autProgetctFTRealizedDate;
    }

    public Date getSendedLearnersFTDate() {
        return sendedLearnersFTDate;
    }

    public void setSendedLearnersFTDate(Date sendedLearnersFTDate) {
        this.sendedLearnersFTDate = sendedLearnersFTDate;
    }

    public Double getEntourageHours() {
        return entourageHours;
    }

    public void setEntourageHours(Double entourageHours) {
        this.entourageHours = entourageHours;
    }

    public Double getOrenatationHours() {
        return orenatationHours;
    }

    public void setOrenatationHours(Double orenatationHours) {
        this.orenatationHours = orenatationHours;
    }

    public SpecialInitiativesCourseEnum getSpecialInitiatives() {
        return specialInitiatives;
    }

    public void setSpecialInitiatives(SpecialInitiativesCourseEnum specialInitiatives) {
        this.specialInitiatives = specialInitiatives;
    }

    public Boolean getTradeUnionTeachingRequest() {
        return tradeUnionTeachingRequest;
    }

    public void setTradeUnionTeachingRequest(Boolean tradeUnionTeachingRequest) {
        this.tradeUnionTeachingRequest = tradeUnionTeachingRequest;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public RecipientTypeLearnerCourseEnum getRecipient() {
        return recipient;
    }

    public void setRecipient(RecipientTypeLearnerCourseEnum recipient) {
        this.recipient = recipient;
    }

    public Boolean getIssueTicket() {
        return issueTicket;
    }

    public void setIssueTicket(Boolean issueTicket) {
        this.issueTicket = issueTicket;
    }

    public Date getAfternoonStartHour() {
        return afternoonStartHour;
    }

    public void setAfternoonStartHour(Date afternoonStartHour) {
        this.afternoonStartHour = afternoonStartHour;
    }

    public Double getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(Double ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public Boolean getAttendanceBenefits() {
        return attendanceBenefits;
    }

    public void setAttendanceBenefits(Boolean attendanceBenefits) {
        this.attendanceBenefits = attendanceBenefits;
    }

    public Double getAmountAttendanceBenefits() {
        return amountAttendanceBenefits;
    }

    public void setAmountAttendanceBenefits(Double amountAttendanceBenefits) {
        this.amountAttendanceBenefits = amountAttendanceBenefits;
    }

    public Double getAmountReportFT() {
        return amountReportFT;
    }

    public void setAmountReportFT(Double amountReportFT) {
        this.amountReportFT = amountReportFT;
    }

    public Double getAmountFinSecurityCapital() {
        return amountFinSecurityCapital;
    }

    public void setAmountFinSecurityCapital(Double amountFinSecurityCapital) {
        this.amountFinSecurityCapital = amountFinSecurityCapital;
    }

    public Date getAmountAutorizedFTDate() {
        return amountAutorizedFTDate;
    }

    public void setAmountAutorizedFTDate(Date amountAutorizedFTDate) {
        this.amountAutorizedFTDate = amountAutorizedFTDate;
    }

    public Double getAmountAutorizedFT() {
        return amountAutorizedFT;
    }

    public void setAmountAutorizedFT(Double amountAutorizedFT) {
        this.amountAutorizedFT = amountAutorizedFT;
    }

    public Double getTotalPartnerCost() {
        return totalPartnerCost;
    }

    public void setTotalPartnerCost(Double totalPartnerCost) {
        this.totalPartnerCost = totalPartnerCost;
    }

    public Double getTotalPartnerCostOnPercent() {
        return totalPartnerCostOnPercent;
    }

    public void setTotalPartnerCostOnPercent(Double totalPartnerCostOnPercent) {
        this.totalPartnerCostOnPercent = totalPartnerCostOnPercent;
    }

    public Double getTotalAmountWithoutFS() {
        return totalAmountWithoutFS;
    }

    public void setTotalAmountWithoutFS(Double totalAmountWithoutFS) {
        this.totalAmountWithoutFS = totalAmountWithoutFS;
    }

    public Date getSendedPaperReportingDate() {
        return sendedPaperReportingDate;
    }

    public void setSendedPaperReportingDate(Date sendedPaperReportingDate) {
        this.sendedPaperReportingDate = sendedPaperReportingDate;
    }

    public Date getSendedEletronicReportingDate() {
        return sendedEletronicReportingDate;
    }

    public void setSendedEletronicReportingDate(Date sendedEletronicReportingDate) {
        this.sendedEletronicReportingDate = sendedEletronicReportingDate;
    }

    public Date getExpiredReportingDate() {
        return expiredReportingDate;
    }

    public void setExpiredReportingDate(Date expiredReportingDate) {
        this.expiredReportingDate = expiredReportingDate;
    }

    public Date getInvoiceAuthorizationDate() {
        return invoiceAuthorizationDate;
    }

    public void setInvoiceAuthorizationDate(Date invoiceAuthorizationDate) {
        this.invoiceAuthorizationDate = invoiceAuthorizationDate;
    }

    public Date getDeliveryDateInAdministration() {
        return deliveryDateInAdministration;
    }

    public void setDeliveryDateInAdministration(Date deliveryDateInAdministration) {
        this.deliveryDateInAdministration = deliveryDateInAdministration;
    }

    public Date getCommercialTaxableCommunicationDate() {
        return commercialTaxableCommunicationDate;
    }

    public void setCommercialTaxableCommunicationDate(Date commercialTaxableCommunicationDate) {
        this.commercialTaxableCommunicationDate = commercialTaxableCommunicationDate;
    }

    public String getReportNote() {
        return reportNote;
    }

    public void setReportNote(String reportNote) {
        this.reportNote = reportNote;
    }

    public String getDailyRegister() {
        return dailyRegister;
    }

    public void setDailyRegister(String dailyRegister) {
        this.dailyRegister = dailyRegister;
    }

    public PartnerDto getActuatorSubject() {
        return actuatorSubject;
    }

    public void setActuatorSubject(PartnerDto actuatorSubject) {
        this.actuatorSubject = actuatorSubject;
    }

    public List<AddressCourse> getHeadquatersCourse() {
        return headquatersCourse;
    }

    public void setHeadquatersCourse(List<AddressCourse> headquatersCourse) {
        this.headquatersCourse = headquatersCourse;
    }

    public List<RecipientManagmentCourse> getRecipientManagment() {
        return recipientManagment;
    }

    public void setRecipientManagment(List<RecipientManagmentCourse> recipientManagment) {
        this.recipientManagment = recipientManagment;
    }

    public List<PartnerCourse> getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(List<PartnerCourse> partnerList) {
        this.partnerList = partnerList;
    }

    public List<TeacherCourse> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<TeacherCourse> teacherList) {
        this.teacherList = teacherList;
    }

    public List<PlacementCourse> getPlacementList() {
        return placementList;
    }

    public void setPlacementList(List<PlacementCourse> placementList) {
        this.placementList = placementList;
    }

    public List<Attachment> getDocumentsAttachment() {
        return documentsAttachment;
    }

    public void setDocumentsAttachment(List<Attachment> documentsAttachment) {
        this.documentsAttachment = documentsAttachment;
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "id=" + id +
                ", courseTitle='" + courseTitle + '\'' +
                ", contentsArea=" + contentsArea +
                ", learnerType=" + learnerType +
                ", supplyModality=" + supplyModality +
                ", paymentModality=" + paymentModality +
                ", costs=" + costs +
                ", foundsTypeCourse=" + foundsTypeCourse +
                ", educationalTargetDescription='" + educationalTargetDescription + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", courseStartDate=" + courseStartDate +
                ", courseEndDate=" + courseEndDate +
                ", theoryHours=" + theoryHours +
                ", practiceHours=" + practiceHours +
                ", coachingHours=" + coachingHours +
                ", visitHours=" + visitHours +
                ", skilsAnalysisHours=" + skilsAnalysisHours +
                ", totalHours=" + totalHours +
                ", totalHoursTraining=" + totalHoursTraining +
                ", dailyHours=" + dailyHours +
                ", certificateTypeCourse=" + certificateTypeCourse +
                ", minimumNumericOfParticipants=" + minimumNumericOfParticipants +
                ", disabled=" + disabled +
                ", courseLogo=" + courseLogo +
                ", partFullTimeCourse=" + partFullTimeCourse +
                ", morningStartHour=" + morningStartHour +
                ", morningEndHour=" + morningEndHour +
                ", afternoonStartHour=" + afternoonStartHour +
                ", afternoonEndHour=" + afternoonEndHour +
                ", actuatorSubject=" + actuatorSubject +
                ", courseCode='" + courseCode + '\'' +
                ", businessName='" + businessName + '\'' +
                ", businessEmail='" + businessEmail + '\'' +
                ", externalReferenceCode='" + externalReferenceCode + '\'' +
                ", courseType=" + courseType +
                ", sendedProjectDate=" + sendedProjectDate +
                ", receiptFTConfirmationDate=" + receiptFTConfirmationDate +
                ", sendedCanceledProjectDate=" + sendedCanceledProjectDate +
                ", autProgetctFTRealizedDate=" + autProgetctFTRealizedDate +
                ", sendedLearnersFTDate=" + sendedLearnersFTDate +
                ", entourageHours=" + entourageHours +
                ", orenatationHours=" + orenatationHours +
                ", specialInitiatives=" + specialInitiatives +
                ", tradeUnionTeachingRequest=" + tradeUnionTeachingRequest +
                ", note='" + note + '\'' +
                ", recipient=" + recipient +
                ", issueTicket=" + issueTicket +
                ", ticketAmount=" + ticketAmount +
                ", numberOfTickets=" + numberOfTickets +
                ", attendanceBenefits=" + attendanceBenefits +
                ", amountAttendanceBenefits=" + amountAttendanceBenefits +
                ", amountReportFT=" + amountReportFT +
                ", amountFinSecurityCapital=" + amountFinSecurityCapital +
                ", amountAutorizedFTDate=" + amountAutorizedFTDate +
                ", amountAutorizedFT=" + amountAutorizedFT +
                ", totalPartnerCost=" + totalPartnerCost +
                ", totalPartnerCostOnPercent=" + totalPartnerCostOnPercent +
                ", totalAmountWithoutFS=" + totalAmountWithoutFS +
                ", sendedPaperReportingDate=" + sendedPaperReportingDate +
                ", sendedEletronicReportingDate=" + sendedEletronicReportingDate +
                ", expiredReportingDate=" + expiredReportingDate +
                ", invoiceAuthorizationDate=" + invoiceAuthorizationDate +
                ", deliveryDateInAdministration=" + deliveryDateInAdministration +
                ", commercialTaxableCommunicationDate=" + commercialTaxableCommunicationDate +
                ", reportNote='" + reportNote + '\'' +
                ", dailyRegister='" + dailyRegister + '\'' +
                ", headquatersCourse=" + headquatersCourse +
                ", recipientManagment=" + recipientManagment +
                ", partnerList=" + partnerList +
                ", teacherList=" + teacherList +
                ", placementList=" + placementList +
                ", documentsAttachment=" + documentsAttachment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEntity that = (CourseEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(courseTitle, that.courseTitle) &&
                contentsArea == that.contentsArea &&
                learnerType == that.learnerType &&
                supplyModality == that.supplyModality &&
                paymentModality == that.paymentModality &&
                Objects.equals(costs, that.costs) &&
                foundsTypeCourse == that.foundsTypeCourse &&
                Objects.equals(educationalTargetDescription, that.educationalTargetDescription) &&
                Objects.equals(courseDescription, that.courseDescription) &&
                Objects.equals(courseStartDate, that.courseStartDate) &&
                Objects.equals(courseEndDate, that.courseEndDate) &&
                Objects.equals(theoryHours, that.theoryHours) &&
                Objects.equals(practiceHours, that.practiceHours) &&
                Objects.equals(coachingHours, that.coachingHours) &&
                Objects.equals(visitHours, that.visitHours) &&
                Objects.equals(skilsAnalysisHours, that.skilsAnalysisHours) &&
                Objects.equals(totalHours, that.totalHours) &&
                Objects.equals(totalHoursTraining, that.totalHoursTraining) &&
                Objects.equals(dailyHours, that.dailyHours) &&
                certificateTypeCourse == that.certificateTypeCourse &&
                Objects.equals(minimumNumericOfParticipants, that.minimumNumericOfParticipants) &&
                Objects.equals(disabled, that.disabled) &&
                Objects.equals(courseLogo, that.courseLogo) &&
                partFullTimeCourse == that.partFullTimeCourse &&
                Objects.equals(morningStartHour, that.morningStartHour) &&
                Objects.equals(morningEndHour, that.morningEndHour) &&
                Objects.equals(afternoonStartHour, that.afternoonStartHour) &&
                Objects.equals(afternoonEndHour, that.afternoonEndHour) &&
                Objects.equals(actuatorSubject, that.actuatorSubject) &&
                Objects.equals(courseCode, that.courseCode) &&
                Objects.equals(businessName, that.businessName) &&
                Objects.equals(businessEmail, that.businessEmail) &&
                Objects.equals(externalReferenceCode, that.externalReferenceCode) &&
                courseType == that.courseType &&
                Objects.equals(sendedProjectDate, that.sendedProjectDate) &&
                Objects.equals(receiptFTConfirmationDate, that.receiptFTConfirmationDate) &&
                Objects.equals(sendedCanceledProjectDate, that.sendedCanceledProjectDate) &&
                Objects.equals(autProgetctFTRealizedDate, that.autProgetctFTRealizedDate) &&
                Objects.equals(sendedLearnersFTDate, that.sendedLearnersFTDate) &&
                Objects.equals(entourageHours, that.entourageHours) &&
                Objects.equals(orenatationHours, that.orenatationHours) &&
                specialInitiatives == that.specialInitiatives &&
                Objects.equals(tradeUnionTeachingRequest, that.tradeUnionTeachingRequest) &&
                Objects.equals(note, that.note) &&
                recipient == that.recipient &&
                Objects.equals(issueTicket, that.issueTicket) &&
                Objects.equals(ticketAmount, that.ticketAmount) &&
                Objects.equals(numberOfTickets, that.numberOfTickets) &&
                Objects.equals(attendanceBenefits, that.attendanceBenefits) &&
                Objects.equals(amountAttendanceBenefits, that.amountAttendanceBenefits) &&
                Objects.equals(amountReportFT, that.amountReportFT) &&
                Objects.equals(amountFinSecurityCapital, that.amountFinSecurityCapital) &&
                Objects.equals(amountAutorizedFTDate, that.amountAutorizedFTDate) &&
                Objects.equals(amountAutorizedFT, that.amountAutorizedFT) &&
                Objects.equals(totalPartnerCost, that.totalPartnerCost) &&
                Objects.equals(totalPartnerCostOnPercent, that.totalPartnerCostOnPercent) &&
                Objects.equals(totalAmountWithoutFS, that.totalAmountWithoutFS) &&
                Objects.equals(sendedPaperReportingDate, that.sendedPaperReportingDate) &&
                Objects.equals(sendedEletronicReportingDate, that.sendedEletronicReportingDate) &&
                Objects.equals(expiredReportingDate, that.expiredReportingDate) &&
                Objects.equals(invoiceAuthorizationDate, that.invoiceAuthorizationDate) &&
                Objects.equals(deliveryDateInAdministration, that.deliveryDateInAdministration) &&
                Objects.equals(commercialTaxableCommunicationDate, that.commercialTaxableCommunicationDate) &&
                Objects.equals(reportNote, that.reportNote) &&
                Objects.equals(dailyRegister, that.dailyRegister) &&
                Objects.equals(headquatersCourse, that.headquatersCourse) &&
                Objects.equals(recipientManagment, that.recipientManagment) &&
                Objects.equals(partnerList, that.partnerList) &&
                Objects.equals(teacherList, that.teacherList) &&
                Objects.equals(placementList, that.placementList) &&
                Objects.equals(documentsAttachment, that.documentsAttachment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseTitle, contentsArea, learnerType, supplyModality, paymentModality, costs, foundsTypeCourse, educationalTargetDescription, courseDescription, courseStartDate, courseEndDate, theoryHours, practiceHours, coachingHours, visitHours, skilsAnalysisHours, totalHours, totalHoursTraining, dailyHours, certificateTypeCourse, minimumNumericOfParticipants, disabled, courseLogo, partFullTimeCourse, morningStartHour, morningEndHour, afternoonStartHour, afternoonEndHour, actuatorSubject, courseCode, businessName, businessEmail, externalReferenceCode, courseType, sendedProjectDate, receiptFTConfirmationDate, sendedCanceledProjectDate, autProgetctFTRealizedDate, sendedLearnersFTDate, entourageHours, orenatationHours, specialInitiatives, tradeUnionTeachingRequest, note, recipient, issueTicket, ticketAmount, numberOfTickets, attendanceBenefits, amountAttendanceBenefits, amountReportFT, amountFinSecurityCapital, amountAutorizedFTDate, amountAutorizedFT, totalPartnerCost, totalPartnerCostOnPercent, totalAmountWithoutFS, sendedPaperReportingDate, sendedEletronicReportingDate, expiredReportingDate, invoiceAuthorizationDate, deliveryDateInAdministration, commercialTaxableCommunicationDate, reportNote, dailyRegister, headquatersCourse, recipientManagment, partnerList, teacherList, placementList, documentsAttachment);
    }
}
