package it.tcgroup.vilear.coursemanager.controller.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.dto.AddressDto;
import it.tcgroup.vilear.coursemanager.entity.enumerated.*;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

public class CourseResponseV1 {

    @JsonProperty("id")
    private String id;

    @JsonProperty( "course_title")
    private String courseTitle;

    @JsonProperty("status")
    private CourseStatusEnum status;

    @JsonProperty( "contents_area")
    private ContentsAreaCourseEnum contentsArea;

    @JsonProperty( "learner_type")
    private LearnerTypeCourseEnum learnerType;

    @JsonProperty( "supply_modality")
    private SupplyModalityCourseEnum supplyModality;

    @JsonProperty( "payment_modality")
    private PaymentModalityEnum paymentModality;

    @JsonProperty( "costs")
    private Double costs;

    @JsonProperty( "founds_type")
    private FoundsTypeCourseEnum foundsTypeCourse;

    @JsonProperty( "educational_target_description")
    private String educationalTargetDescription;

    @JsonProperty( "course_description")
    private String courseDescription;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty( "course_start_date")
    private Date courseStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty( "course_end_date")
    private Date courseEndDate;

    @JsonProperty( "theory_hours")
    private Double theoryHours;

    @JsonProperty( "practice_hours")
    private Double practiceHours;

    @JsonProperty( "coaching_hours")
    private Double coachingHours;

    @JsonProperty( "visit_hours")
    private Double visitHours;

    @JsonProperty( "skils_analysis_hours")
    private Double skilsAnalysisHours;

    @JsonProperty( "total_hours")
    private Double totalHours;

    @JsonProperty( "total_hours_training")
    private Double totalHoursTraining;

    @JsonProperty( "daily_hours")
    private Double dailyHours;

    @JsonProperty( "certificate_type")
    private CertificateTypeCourseEnum certificateTypeCourse;

    @JsonProperty( "minimum_numeric_of_participants")
    private Integer minimumNumericOfParticipants;

    @JsonProperty( "disabled")
    private Boolean disabled;

    @JsonProperty( "course_logo")
    private Attachment courseLogo;

    @JsonProperty( "part_full_time")
    private PartFullTimeCourseEnum partFullTimeCourse;

    @JsonFormat(pattern = "HH:mm:ss")
    @JsonProperty( "morning_start_hour")
    private Date morningStartHour;

    @JsonFormat(pattern = "HH:mm:ss")
    @JsonProperty( "morning_end_hour")
    private Date morningEndHour;

    @JsonFormat(pattern = "HH:mm:ss")
    @JsonProperty( "afternoon_start_hour")
    private Date afternoonStartHour;

    @JsonFormat(pattern = "HH:mm:ss")
    @JsonProperty( "afternoon_end_hour")
    private Date afternoonEndHour;

    @JsonProperty( "actuator_subject")
    private PartnerResponseV1 actuatorSubject;

    @JsonProperty( "course_code")
    private String courseCode;

    @JsonProperty( "business_name")
    private String businessName;

    @JsonProperty( "business_email")
    private String businessEmail;

    @JsonProperty( "external_reference_code")
    private String externalReferenceCode;

    @JsonProperty( "course_type")
    private CourseTypeEnum courseType;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    @JsonProperty( "sended_project_date")
    private Date sendedProjectDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    @JsonProperty( "receipt_FT_confirmation_date")
    private Date receiptFTConfirmationDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    @JsonProperty( "sended_canceled_project_date")
    private Date sendedCanceledProjectDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    @JsonProperty( "aut_progetct_FT_realized_date")
    private Date autProgetctFTRealizedDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    @JsonProperty( "sended_learners_FT_date")
    private Date sendedLearnersFTDate;

    @JsonProperty( "entourage_hours")
    private Double entourageHours;

    @JsonProperty( "orenatation_hours")
    private Double orenatationHours;

    @JsonProperty( "special_initiatives")
    private SpecialInitiativesCourseEnum specialInitiatives;

    @JsonProperty( "trade_union_teaching_request")
    private Boolean tradeUnionTeachingRequest;

    @JsonProperty( "note")
    private String note;

    @JsonProperty( "recipient")
    private RecipientTypeLearnerCourseEnum recipient;

    @JsonProperty( "issue_ticket")
    private Boolean issueTicket;

    @JsonProperty( "ticket_amount")
    private Double ticketAmount;

    @JsonProperty( "number_of_tickets")
    private Integer numberOfTickets;

    @JsonProperty( "attendance_benefits")
    private Boolean attendanceBenefits;

    @JsonProperty( "amount_attendance_benefits")
    private Double amountAttendanceBenefits;

    @JsonProperty( "amount_report_FT")
    private Double amountReportFT;

    @JsonProperty( "amount_fin_security_capital")
    private Double amountFinSecurityCapital;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    @JsonProperty( "amount_autorized_FT_date")
    private Date amountAutorizedFTDate;

    @JsonProperty( "amount_autorized_FT")
    private Double amountAutorizedFT;

    @JsonProperty( "total_partner_cost")
    private Double totalPartnerCost;

    @JsonProperty( "total_partner_cost_on_percent")
    private Double totalPartnerCostOnPercent;

    @JsonProperty( "total_amount_without_FS")
    private Double totalAmountWithoutFS;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    @JsonProperty( "sended_paper_reporting_date")
    private Date sendedPaperReportingDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    @JsonProperty( "sended_eletronic_reporting_date")
    private Date sendedEletronicReportingDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    @JsonProperty( "expired_reporting_date")
    private Date expiredReportingDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    @JsonProperty( "invoice_authorization_date")
    private Date invoiceAuthorizationDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    @JsonProperty( "delivery_date_in_administration")
    private Date deliveryDateInAdministration;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    @JsonProperty( "commercial_taxable_communication_date")
    private Date commercialTaxableCommunicationDate;

    @JsonProperty( "report_note")
    private String reportNote;

    @JsonProperty("invoice_authorization")
    private Boolean invoiceAuthorization;

    @JsonProperty("accounting_code")
    private String accountingCode;

    @JsonProperty( "daily_register")
    private String dailyRegister;

    @JsonProperty( "headquaters_course")
    private List<AddressCourseResponseV1> headquatersCourse;

    @JsonProperty( "candidates")
    private List<CandidateCourseResponseV1> candidateList;

    @JsonProperty( "learners")
    private List<RecipientManagmentCourseResponseV1> recipientManagment;

    @JsonProperty( "partners")
    private List<PartnerCourseResponseV1> partnerList;

    @JsonProperty( "teachers")
    private List<TeacherCourseResponseV1> teacherList;

    @JsonProperty( "placement")
    private List<PlacementCourseResponseV1> placementList;

    @JsonProperty( "document_attachment")
    private List<Attachment> documentAttachment;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public static class AddressCourseResponseV1{

        @JsonProperty("address")
        private AddressDto address;

        @JsonProperty("main")
        private Boolean main;

        public AddressCourseResponseV1() {
        }

        public AddressCourseResponseV1(AddressDto address, Boolean main) {
            this.address = address;
            this.main = main;
        }

        public AddressDto getAddress() {
            return address;
        }

        public void setAddress(AddressDto address) {
            this.address = address;
        }

        public Boolean getMain() {
            return main;
        }

        public void setMain(Boolean main) {
            this.main = main;
        }

        @Override
        public String toString() {
            return "AddressCourse{" +
                    "address=" + address +
                    ", main=" + main +
                    '}';
        }

    }

    public static class CandidateCourseResponseV1{

        @JsonProperty("id")
        private UUID id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("surname")
        private String surname;

        @JsonProperty("fiscal_code")
        private String fiscalCode;

        @JsonProperty("accepted")
        private Boolean accepted;

        @JsonProperty("candidated")
        private Boolean candidated;

        public CandidateCourseResponseV1() {
        }

        public CandidateCourseResponseV1(UUID id, String name, String surname, String fiscalCode, Boolean accepted, Boolean candidated) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.fiscalCode = fiscalCode;
            this.accepted = accepted;
            this.candidated = candidated;
        }

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public Boolean getAccepted() {
            return accepted;
        }

        public void setAccepted(Boolean accepted) {
            this.accepted = accepted;
        }

        public String getFiscalCode() {
            return fiscalCode;
        }

        public void setFiscalCode(String fiscalCode) {
            this.fiscalCode = fiscalCode;
        }

        public Boolean getCandidated() {
            return candidated;
        }

        public void setCandidated(Boolean candidated) {
            this.candidated = candidated;
        }

        @Override
        public String toString() {
            return "CandidateCourseResponseV1{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", fiscalCode='" + fiscalCode + '\'' +
                    ", accepted=" + accepted +
                    ", candidated=" + candidated +
                    '}';
        }
    }

    public static class RecipientManagmentCourseResponseV1{

        @JsonProperty("learner_info")
        private LearnerResponseV1 learner;

        @JsonProperty("recipient_code")
        private RecipientTypeLearnerCourseEnum[] recipientType;

        @JsonProperty("exoneration_general_security")
        private Boolean exonerationGeneralSecurity;

        @JsonProperty("exoneration_rights_and_duties")
        private Boolean exonerationRightsAndDuties;

        @JsonProperty("general_security_module")
        private Boolean generalSecurityModule;

        @JsonProperty("specific_security_module")
        private Boolean specificSecurityModule;

        @JsonProperty("necessary_hours")
        private Double necessaryHours;

        @JsonProperty("specification_security_exonerate")
        private SecurityExonerateLearnerCourseEnum specificationSsecurityExonerate;

        @JsonProperty("withdrawn")
        private Boolean withdrawn;

        @JsonProperty("withdrawn_with_reason")
        private ReasonWithdrawnLearnerCourseEnum withdrawnReason;

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
        @JsonProperty("withdrawn_date")
        private Date withdrawnDate;

        @JsonProperty("withdrawn_form")
        private Attachment withdrawnForm;

        @JsonProperty("num_issued_tickets")
        private Integer numIssuedTickets;

        @JsonProperty("actual_training_days")
        private Integer actualTrainingDays;


        public RecipientManagmentCourseResponseV1() {
        }

        public RecipientManagmentCourseResponseV1(LearnerResponseV1 learner, RecipientTypeLearnerCourseEnum[] recipientType, Boolean exonerationGeneralSecurity, Boolean exonerationRightsAndDuties, Boolean generalSecurityModule, Boolean specificSecurityModule, Double necessaryHours, SecurityExonerateLearnerCourseEnum specificationSsecurityExonerate, Boolean withdrawn, ReasonWithdrawnLearnerCourseEnum withdrawnReason, Date withdrawnDate, Attachment withdrawnForm, Integer numIssuedTickets, Integer actualTrainingDays) {
            this.learner = learner;
            this.recipientType = recipientType;
            this.exonerationGeneralSecurity = exonerationGeneralSecurity;
            this.exonerationRightsAndDuties = exonerationRightsAndDuties;
            this.generalSecurityModule = generalSecurityModule;
            this.specificSecurityModule = specificSecurityModule;
            this.necessaryHours = necessaryHours;
            this.specificationSsecurityExonerate = specificationSsecurityExonerate;
            this.withdrawn = withdrawn;
            this.withdrawnReason = withdrawnReason;
            this.withdrawnDate = withdrawnDate;
            this.withdrawnForm = withdrawnForm;
            this.numIssuedTickets = numIssuedTickets;
            this.actualTrainingDays = actualTrainingDays;
        }

        public LearnerResponseV1 getLearner() {
            return learner;
        }

        public void setLearner(LearnerResponseV1 learner) {
            this.learner = learner;
        }

        public RecipientTypeLearnerCourseEnum[] getRecipientType() {
            return recipientType;
        }

        public void setRecipientType(RecipientTypeLearnerCourseEnum[] recipientType) {
            this.recipientType = recipientType;
        }

        public Boolean getExonerationGeneralSecurity() {
            return exonerationGeneralSecurity;
        }

        public void setExonerationGeneralSecurity(Boolean exonerationGeneralSecurity) {
            this.exonerationGeneralSecurity = exonerationGeneralSecurity;
        }

        public Boolean getExonerationRightsAndDuties() {
            return exonerationRightsAndDuties;
        }

        public void setExonerationRightsAndDuties(Boolean exonerationRightsAndDuties) {
            this.exonerationRightsAndDuties = exonerationRightsAndDuties;
        }

        public Double getNecessaryHours() {
            return necessaryHours;
        }

        public void setNecessaryHours(Double necessaryHours) {
            this.necessaryHours = necessaryHours;
        }

        public SecurityExonerateLearnerCourseEnum getSpecificationSsecurityExonerate() {
            return specificationSsecurityExonerate;
        }

        public void setSpecificationSsecurityExonerate(SecurityExonerateLearnerCourseEnum specificationSsecurityExonerate) {
            this.specificationSsecurityExonerate = specificationSsecurityExonerate;
        }

        public Boolean getWithdrawn() {
            return withdrawn;
        }

        public void setWithdrawn(Boolean withdrawn) {
            this.withdrawn = withdrawn;
        }

        public ReasonWithdrawnLearnerCourseEnum getWithdrawnReason() {
            return withdrawnReason;
        }

        public void setWithdrawnReason(ReasonWithdrawnLearnerCourseEnum withdrawnReason) {
            this.withdrawnReason = withdrawnReason;
        }

        public Date getWithdrawnDate() {
            return withdrawnDate;
        }

        public void setWithdrawnDate(Date withdrawnDate) {
            this.withdrawnDate = withdrawnDate;
        }

        public Attachment getWithdrawnForm() {
            return withdrawnForm;
        }

        public void setWithdrawnForm(Attachment withdrawnForm) {
            this.withdrawnForm = withdrawnForm;
        }

        public Boolean getGeneralSecurityModule() {
            return generalSecurityModule;
        }

        public void setGeneralSecurityModule(Boolean generalSecurityModule) {
            this.generalSecurityModule = generalSecurityModule;
        }

        public Boolean getSpecificSecurityModule() {
            return specificSecurityModule;
        }

        public void setSpecificSecurityModule(Boolean specificSecurityModule) {
            this.specificSecurityModule = specificSecurityModule;
        }

        public Integer getNumIssuedTickets() {
            return numIssuedTickets;
        }

        public void setNumIssuedTickets(Integer numIssuedTickets) {
            this.numIssuedTickets = numIssuedTickets;
        }

        public Integer getActualTrainingDays() {
            return actualTrainingDays;
        }

        public void setActualTrainingDays(Integer actualTrainingDays) {
            this.actualTrainingDays = actualTrainingDays;
        }

        @Override
        public String toString() {
            return "RecipientManagmentCourseResponseV1{" +
                    "learner=" + learner +
                    ", recipientType=" + Arrays.toString(recipientType) +
                    ", exonerationGeneralSecurity=" + exonerationGeneralSecurity +
                    ", exonerationRightsAndDuties=" + exonerationRightsAndDuties +
                    ", generalSecurityModule=" + generalSecurityModule +
                    ", specificSecurityModule=" + specificSecurityModule +
                    ", necessaryHours=" + necessaryHours +
                    ", specificationSsecurityExonerate=" + specificationSsecurityExonerate +
                    ", withdrawn=" + withdrawn +
                    ", withdrawnReason=" + withdrawnReason +
                    ", withdrawnDate=" + withdrawnDate +
                    ", withdrawnForm=" + withdrawnForm +
                    ", numIssuedTickets=" + numIssuedTickets +
                    ", actualTrainingDays=" + actualTrainingDays +
                    '}';
        }
    }

    public static class PartnerCourseResponseV1{

        @JsonProperty("supplier")
        private PartnerResponseV1 supplier;

        @JsonProperty("supply_services")
        private List<SupplierServiceResponseV1> supplyServices;

        @JsonProperty("first_payment_date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
        private Date firstPaymentDate;

        @JsonProperty("amount_first_paymen")
        private Double amountFirstPaymen;

        @JsonProperty("second_payment_date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
        private Date secondPaymentDate;

        @JsonProperty("amount_second_paymen")
        private Double amountSecondPaymen;

        @JsonProperty("third_payment_date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
        private Date thirdPaymentDate;

        @JsonProperty("amount_third_paymen")
        private Double amountThirdPaymen;

        @JsonProperty("sub_suppliers_list")
        private List<SubSupplierResponseV1> subSupplierList;


        public static class SupplierServiceResponseV1 implements Serializable{

            @JsonProperty("supplier_service")
            private SupplyServicePartnerCourseEnum supplierService;

            @JsonProperty("service_cost")
            private Double serviceCost;

            public SupplierServiceResponseV1() {
            }

            public SupplierServiceResponseV1(SupplyServicePartnerCourseEnum supplierService, Double serviceCost) {
                this.supplierService = supplierService;
                this.serviceCost = serviceCost;
            }

            public SupplyServicePartnerCourseEnum getSupplierService() {
                return supplierService;
            }

            public void setSupplierService(SupplyServicePartnerCourseEnum supplierService) {
                this.supplierService = supplierService;
            }

            public Double getServiceCost() {
                return serviceCost;
            }

            public void setServiceCost(Double serviceCost) {
                this.serviceCost = serviceCost;
            }

            @Override
            public String toString() {
                return "SupplierServiceRequestV1{" +
                        "supplierService=" + supplierService +
                        ", serviceCost=" + serviceCost +
                        '}';
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof SupplierServiceResponseV1)) return false;
                SupplierServiceResponseV1 that = (SupplierServiceResponseV1) o;
                return supplierService == that.supplierService &&
                        Objects.equals(serviceCost, that.serviceCost);
            }

            @Override
            public int hashCode() {
                return Objects.hash(supplierService, serviceCost);
            }
        }


        public static class SubSupplierResponseV1 implements Serializable {

            @JsonProperty("sub_supplier")
            private PartnerResponseV1 subSupplier;

            @JsonProperty("sub_suplly_service")
            private List<SupplyServicePartnerCourseEnum> subSupplierService;

            public SubSupplierResponseV1() {
            }

            public SubSupplierResponseV1(PartnerResponseV1 subSupplier, List<SupplyServicePartnerCourseEnum> subSupplierService) {
                this.subSupplier = subSupplier;
                this.subSupplierService = subSupplierService;
            }

            public PartnerResponseV1 getSubSupplier() {
                return subSupplier;
            }

            public void setSubSupplier(PartnerResponseV1 subSupplier) {
                this.subSupplier = subSupplier;
            }

            public List<SupplyServicePartnerCourseEnum> getSubSupplierService() {
                return subSupplierService;
            }

            public void setSubSupplierService(List<SupplyServicePartnerCourseEnum> subSupplierService) {
                this.subSupplierService = subSupplierService;
            }

            @Override
            public String toString() {
                return "SubSupplier{" +
                        "subSupplier=" + subSupplier +
                        ", subSupplierService=" + subSupplierService +
                        '}';
            }

        }

        public PartnerCourseResponseV1() {
        }

        public PartnerCourseResponseV1(PartnerResponseV1 supplier, List<SupplierServiceResponseV1> supplyServices, Date firstPaymentDate, Double amountFirstPaymen, Date secondPaymentDate, Double amountSecondPaymen, Date thirdPaymentDate, Double amountThirdPaymen, List<SubSupplierResponseV1> subSupplierList) {
            this.supplier = supplier;
            this.supplyServices = supplyServices;
            this.firstPaymentDate = firstPaymentDate;
            this.amountFirstPaymen = amountFirstPaymen;
            this.secondPaymentDate = secondPaymentDate;
            this.amountSecondPaymen = amountSecondPaymen;
            this.thirdPaymentDate = thirdPaymentDate;
            this.amountThirdPaymen = amountThirdPaymen;
            this.subSupplierList = subSupplierList;
        }

        public PartnerResponseV1 getSupplier() {
            return supplier;
        }

        public void setSupplier(PartnerResponseV1 supplier) {
            this.supplier = supplier;
        }

        public List<SupplierServiceResponseV1> getSupplyServices() {
            return supplyServices;
        }

        public void setSupplyServices(List<SupplierServiceResponseV1> supplyServices) {
            this.supplyServices = supplyServices;
        }

        public Date getFirstPaymentDate() {
            return firstPaymentDate;
        }

        public void setFirstPaymentDate(Date firstPaymentDate) {
            this.firstPaymentDate = firstPaymentDate;
        }

        public Double getAmountFirstPaymen() {
            return amountFirstPaymen;
        }

        public void setAmountFirstPaymen(Double amountFirstPaymen) {
            this.amountFirstPaymen = amountFirstPaymen;
        }

        public Date getSecondPaymentDate() {
            return secondPaymentDate;
        }

        public void setSecondPaymentDate(Date secondPaymentDate) {
            this.secondPaymentDate = secondPaymentDate;
        }

        public Double getAmountSecondPaymen() {
            return amountSecondPaymen;
        }

        public void setAmountSecondPaymen(Double amountSecondPaymen) {
            this.amountSecondPaymen = amountSecondPaymen;
        }

        public Date getThirdPaymentDate() {
            return thirdPaymentDate;
        }

        public void setThirdPaymentDate(Date thirdPaymentDate) {
            this.thirdPaymentDate = thirdPaymentDate;
        }

        public Double getAmountThirdPaymen() {
            return amountThirdPaymen;
        }

        public void setAmountThirdPaymen(Double amountThirdPaymen) {
            this.amountThirdPaymen = amountThirdPaymen;
        }

        public List<SubSupplierResponseV1> getSubSupplierList() {
            return subSupplierList;
        }

        public void setSubSupplierList(List<SubSupplierResponseV1> subSupplierList) {
            this.subSupplierList = subSupplierList;
        }

        @Override
        public String toString() {
            return "PartnerCourseResponseV1{" +
                    "supplier=" + supplier +
                    ", supplyServices=" + supplyServices +
                    ", firstPaymentDate=" + firstPaymentDate +
                    ", amountFirstPaymen=" + amountFirstPaymen +
                    ", secondPaymentDate=" + secondPaymentDate +
                    ", amountSecondPaymen=" + amountSecondPaymen +
                    ", thirdPaymentDate=" + thirdPaymentDate +
                    ", amountThirdPaymen=" + amountThirdPaymen +
                    ", subSupplierList=" + subSupplierList +
                    '}';
        }
    }

    public static class TeacherCourseResponseV1{

        @JsonProperty("teacher")
        private TeacherResponseV1 teacher;

        @JsonProperty("role")
        private RoleTeacherCourseEnum role;

        @JsonProperty("status")
        private WorkingPositionEnum workingPosition;

        @JsonProperty("gross_hourly_cost")
        private Double grossHourlyCost;

        @JsonProperty("payment_modality")
        private PaymentModalityEnum paymentModality;

        @JsonProperty("net_hourly_cost")
        private Double netHourlyCost;

        @JsonProperty("main")
        private Boolean main;

        @JsonProperty("hours_teaching_letter_assignment")
        private Double hoursTeachingLetterAssignment;

        @JsonProperty("total_hours_performed")
        private Double totalHoursPerformed;

        @JsonProperty("trade_unino_teaching_letter")
        private Attachment tradeUninoTeachingLetter;

        @JsonProperty("learner_judgement_form")
        private Attachment learnerJudgementForm;

        @JsonProperty("acronym_trade_unino")
        private AcronymTradeUninoEnum acronymTradeUnino;

        @JsonProperty("payment_modality_trade_union_teaching")
        private PaymentModalityTradeUnionEnum paymentModalityTradeUnion;


        public TeacherCourseResponseV1() {
        }

        public TeacherCourseResponseV1(TeacherResponseV1 teacher, RoleTeacherCourseEnum role, WorkingPositionEnum workingPosition, Double grossHourlyCost, PaymentModalityEnum paymentModality, Double netHourlyCost, Boolean main, Double hoursTeachingLetterAssignment, Double totalHoursPerformed, Attachment tradeUninoTeachingLetter, Attachment learnerJudgementForm, AcronymTradeUninoEnum acronymTradeUnino, PaymentModalityTradeUnionEnum paymentModalityTradeUnion) {
            this.teacher = teacher;
            this.role = role;
            this.workingPosition = workingPosition;
            this.grossHourlyCost = grossHourlyCost;
            this.paymentModality = paymentModality;
            this.netHourlyCost = netHourlyCost;
            this.main = main;
            this.hoursTeachingLetterAssignment = hoursTeachingLetterAssignment;
            this.totalHoursPerformed = totalHoursPerformed;
            this.tradeUninoTeachingLetter = tradeUninoTeachingLetter;
            this.learnerJudgementForm = learnerJudgementForm;
            this.acronymTradeUnino = acronymTradeUnino;
            this.paymentModalityTradeUnion = paymentModalityTradeUnion;
        }

        public TeacherResponseV1 getTeacher() {
            return teacher;
        }

        public void setTeacher(TeacherResponseV1 teacher) {
            this.teacher = teacher;
        }

        public RoleTeacherCourseEnum getRole() {
            return role;
        }

        public void setRole(RoleTeacherCourseEnum role) {
            this.role = role;
        }

        public Double getGrossHourlyCost() {
            return grossHourlyCost;
        }

        public void setGrossHourlyCost(Double grossHourlyCost) {
            this.grossHourlyCost = grossHourlyCost;
        }

        public PaymentModalityEnum getPaymentModality() {
            return paymentModality;
        }

        public void setPaymentModality(PaymentModalityEnum paymentModality) {
            this.paymentModality = paymentModality;
        }

        public Double getNetHourlyCost() {
            return netHourlyCost;
        }

        public void setNetHourlyCost(Double netHourlyCost) {
            this.netHourlyCost = netHourlyCost;
        }

        public Boolean getMain() {
            return main;
        }

        public void setMain(Boolean main) {
            this.main = main;
        }

        public Double getHoursTeachingLetterAssignment() {
            return hoursTeachingLetterAssignment;
        }

        public void setHoursTeachingLetterAssignment(Double hoursTeachingLetterAssignment) {
            this.hoursTeachingLetterAssignment = hoursTeachingLetterAssignment;
        }

        public Double getTotalHoursPerformed() {
            return totalHoursPerformed;
        }

        public void setTotalHoursPerformed(Double totalHoursPerformed) {
            this.totalHoursPerformed = totalHoursPerformed;
        }

        public Attachment getTradeUninoTeachingLetter() {
            return tradeUninoTeachingLetter;
        }

        public void setTradeUninoTeachingLetter(Attachment tradeUninoTeachingLetter) {
            this.tradeUninoTeachingLetter = tradeUninoTeachingLetter;
        }

        public Attachment getLearnerJudgementForm() {
            return learnerJudgementForm;
        }

        public void setLearnerJudgementForm(Attachment learnerJudgementForm) {
            this.learnerJudgementForm = learnerJudgementForm;
        }

        public AcronymTradeUninoEnum getAcronymTradeUnino() {
            return acronymTradeUnino;
        }

        public PaymentModalityTradeUnionEnum getPaymentModalityTradeUnion() {
            return paymentModalityTradeUnion;
        }

        public void setPaymentModalityTradeUnion(PaymentModalityTradeUnionEnum paymentModalityTradeUnion) {
            this.paymentModalityTradeUnion = paymentModalityTradeUnion;
        }

        public WorkingPositionEnum getWorkingPosition() {
            return workingPosition;
        }

        public void setWorkingPosition(WorkingPositionEnum workingPosition) {
            this.workingPosition = workingPosition;
        }

        public void setAcronymTradeUnino(AcronymTradeUninoEnum acronymTradeUnino) {
            this.acronymTradeUnino = acronymTradeUnino;
        }

        @Override
        public String toString() {
            return "TeacherCourseResponseV1{" +
                    "teacher=" + teacher +
                    ", role=" + role +
                    ", workingPosition=" + workingPosition +
                    ", grossHourlyCost=" + grossHourlyCost +
                    ", paymentModality=" + paymentModality +
                    ", netHourlyCost=" + netHourlyCost +
                    ", main=" + main +
                    ", hoursTeachingLetterAssignment=" + hoursTeachingLetterAssignment +
                    ", totalHoursPerformed=" + totalHoursPerformed +
                    ", tradeUninoTeachingLetter='" + tradeUninoTeachingLetter + '\'' +
                    ", learnerJudgementForm='" + learnerJudgementForm + '\'' +
                    ", acronymTradeUnino='" + acronymTradeUnino + '\'' +
                    ", paymentModalityTradeUnion=" + paymentModalityTradeUnion +
                    '}';
        }
    }

    public static class PlacementCourseResponseV1{

        @JsonProperty("learner")
        private LearnerResponseV1 learner;

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
        @JsonProperty("hiring_date")
        private Date hiringDate;

        @JsonProperty("mission_hours")
        private Double missionHours;

        @JsonProperty("bonus_amount")
        private Double bonusAmount;

        @JsonProperty("expired_placement_date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
        private Date expiredPlacementDate;

        @JsonProperty("sended_eletronic_placement_date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
        private Date sendedEletronicPlacementDate;

        @JsonProperty("coherence")
        private Boolean coherence;

        @JsonProperty("note")
        private String note;


        public PlacementCourseResponseV1() {
        }

        public PlacementCourseResponseV1(LearnerResponseV1 learner, Date hiringDate, Double missionHours, Double bonusAmount, Date expiredPlacementDate, Date sendedEletronicPlacementDate, Boolean coherence, String note) {
            this.learner = learner;
            this.hiringDate = hiringDate;
            this.missionHours = missionHours;
            this.bonusAmount = bonusAmount;
            this.expiredPlacementDate = expiredPlacementDate;
            this.sendedEletronicPlacementDate = sendedEletronicPlacementDate;
            this.coherence = coherence;
            this.note = note;
        }

        public LearnerResponseV1 getLearner() {
            return learner;
        }

        public void setLearner(LearnerResponseV1 learner) {
            this.learner = learner;
        }

        public Date getHiringDate() {
            return hiringDate;
        }

        public void setHiringDate(Date hiringDate) {
            this.hiringDate = hiringDate;
        }

        public Double getMissionHours() {
            return missionHours;
        }

        public void setMissionHours(Double missionHours) {
            this.missionHours = missionHours;
        }

        public Double getBonusAmount() {
            return bonusAmount;
        }

        public void setBonusAmount(Double bonusAmount) {
            this.bonusAmount = bonusAmount;
        }

        public Date getExpiredPlacementDate() {
            return expiredPlacementDate;
        }

        public void setExpiredPlacementDate(Date expiredPlacementDate) {
            this.expiredPlacementDate = expiredPlacementDate;
        }

        public Date getSendedEletronicPlacementDate() {
            return sendedEletronicPlacementDate;
        }

        public void setSendedEletronicPlacementDate(Date sendedEletronicPlacementDate) {
            this.sendedEletronicPlacementDate = sendedEletronicPlacementDate;
        }

        public Boolean getCoherence() {
            return coherence;
        }

        public void setCoherence(Boolean coherence) {
            this.coherence = coherence;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        @Override
        public String toString() {
            return "PlacementCourseRequestV1{" +
                    "learner=" + learner +
                    ", hiringDate=" + hiringDate +
                    ", missionHours=" + missionHours +
                    ", bonusAmount=" + bonusAmount +
                    ", expiredPlacementDate=" + expiredPlacementDate +
                    ", sendedEletronicPlacementDate=" + sendedEletronicPlacementDate +
                    ", coherence=" + coherence +
                    ", note='" + note + '\'' +
                    '}';
        }
    }

    public CourseResponseV1() {
    }

    public CourseResponseV1(String id, String courseTitle, CourseStatusEnum status, ContentsAreaCourseEnum contentsArea, LearnerTypeCourseEnum learnerType, SupplyModalityCourseEnum supplyModality, PaymentModalityEnum paymentModality, Double costs, FoundsTypeCourseEnum foundsTypeCourse, String educationalTargetDescription, String courseDescription, Date courseStartDate, Date courseEndDate, Double theoryHours, Double practiceHours, Double coachingHours, Double visitHours, Double skilsAnalysisHours, Double totalHours, Double totalHoursTraining, Double dailyHours, CertificateTypeCourseEnum certificateTypeCourse, Integer minimumNumericOfParticipants, Boolean disabled, Attachment courseLogo, PartFullTimeCourseEnum partFullTimeCourse, Date morningStartHour, Date morningEndHour, Date afternoonStartHour, Date afternoonEndHour, PartnerResponseV1 actuatorSubject, String courseCode, String businessName, String businessEmail, String externalReferenceCode, CourseTypeEnum courseType, Date sendedProjectDate, Date receiptFTConfirmationDate, Date sendedCanceledProjectDate, Date autProgetctFTRealizedDate, Date sendedLearnersFTDate, Double entourageHours, Double orenatationHours, SpecialInitiativesCourseEnum specialInitiatives, Boolean tradeUnionTeachingRequest, String note, RecipientTypeLearnerCourseEnum recipient, Boolean issueTicket, Double ticketAmount, Integer numberOfTickets, Boolean attendanceBenefits, Double amountAttendanceBenefits, Double amountReportFT, Double amountFinSecurityCapital, Date amountAutorizedFTDate, Double amountAutorizedFT, Double totalPartnerCost, Double totalPartnerCostOnPercent, Double totalAmountWithoutFS, Date sendedPaperReportingDate, Date sendedEletronicReportingDate, Date expiredReportingDate, Date invoiceAuthorizationDate, Date deliveryDateInAdministration, Date commercialTaxableCommunicationDate, String reportNote, Boolean invoiceAuthorization, String accountingCode, String dailyRegister, List<AddressCourseResponseV1> headquatersCourse, List<CandidateCourseResponseV1> candidateList, List<RecipientManagmentCourseResponseV1> recipientManagment, List<PartnerCourseResponseV1> partnerList, List<TeacherCourseResponseV1> teacherList, List<PlacementCourseResponseV1> placementList, List<Attachment> documentAttachment) {
        this.id = id;
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
        this.invoiceAuthorization = invoiceAuthorization;
        this.accountingCode = accountingCode;
        this.dailyRegister = dailyRegister;
        this.headquatersCourse = headquatersCourse;
        this.candidateList = candidateList;
        this.recipientManagment = recipientManagment;
        this.partnerList = partnerList;
        this.teacherList = teacherList;
        this.placementList = placementList;
        this.documentAttachment = documentAttachment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public PartnerResponseV1 getActuatorSubject() {
        return actuatorSubject;
    }

    public void setActuatorSubject(PartnerResponseV1 actuatorSubject) {
        this.actuatorSubject = actuatorSubject;
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

    public List<AddressCourseResponseV1> getHeadquatersCourse() {
        return headquatersCourse;
    }

    public void setHeadquatersCourse(List<AddressCourseResponseV1> headquatersCourse) {
        this.headquatersCourse = headquatersCourse;
    }

    public List<RecipientManagmentCourseResponseV1> getRecipientManagment() {
        return recipientManagment;
    }

    public void setRecipientManagment(List<RecipientManagmentCourseResponseV1> recipientManagment) {
        this.recipientManagment = recipientManagment;
    }

    public List<PartnerCourseResponseV1> getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(List<PartnerCourseResponseV1> partnerList) {
        this.partnerList = partnerList;
    }

    public List<TeacherCourseResponseV1> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<TeacherCourseResponseV1> teacherList) {
        this.teacherList = teacherList;
    }

    public List<PlacementCourseResponseV1> getPlacementList() {
        return placementList;
    }

    public void setPlacementList(List<PlacementCourseResponseV1> placementList) {
        this.placementList = placementList;
    }

    public List<Attachment> getDocumentAttachment() {
        return documentAttachment;
    }

    public void setDocumentAttachment(List<Attachment> documentAttachment) {
        this.documentAttachment = documentAttachment;
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

    public Double getAmountFinSecurityCapital() {
        return amountFinSecurityCapital;
    }

    public void setAmountFinSecurityCapital(Double amountFinSecurityCapital) {
        this.amountFinSecurityCapital = amountFinSecurityCapital;
    }

    public CourseStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CourseStatusEnum status) {
        this.status = status;
    }

    public Boolean getInvoiceAuthorization() {
        return invoiceAuthorization;
    }

    public void setInvoiceAuthorization(Boolean invoiceAuthorization) {
        this.invoiceAuthorization = invoiceAuthorization;
    }

    public String getAccountingCode() {
        return accountingCode;
    }

    public void setAccountingCode(String accountingCode) {
        this.accountingCode = accountingCode;
    }

    public List<CandidateCourseResponseV1> getCandidateList() {
        return candidateList;
    }

    public void setCandidateList(List<CandidateCourseResponseV1> candidateList) {
        this.candidateList = candidateList;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "CourseResponseV1{" +
                "id='" + id + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", status=" + status +
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
                ", invoiceAuthorization=" + invoiceAuthorization +
                ", accountingCode='" + accountingCode + '\'' +
                ", dailyRegister='" + dailyRegister + '\'' +
                ", headquatersCourse=" + headquatersCourse +
                ", candidateList=" + candidateList +
                ", recipientManagment=" + recipientManagment +
                ", partnerList=" + partnerList +
                ", teacherList=" + teacherList +
                ", placementList=" + placementList +
                ", documentAttachment=" + documentAttachment +
                '}';
    }
}
