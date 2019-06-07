package it.tcgroup.vilear.coursemanager.controller.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.enumerated.*;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.PartnerCourse;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;

public class CourseRequestV1 {

    @NotNull
    @JsonProperty( "course_title")
    private String courseTitle;

    @JsonProperty("status")
    private CourseStatusEnum status;

    @NotNull
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

    @NotNull
    @JsonProperty( "founds_type")
    private FoundsTypeCourseEnum foundsTypeCourse;

    @JsonProperty( "educational_target_description")
    private String educationalTargetDescription;

    @JsonProperty( "course_description")
    private String courseDescription;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty( "course_start_date")
    private Date courseStartDate;

    @NotNull
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
    private UploadRequestV1 courseLogo;
    
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
    private PartnerRequestV1 actuatorSubject;

    @NotNull
    @JsonProperty( "course_code")
    private String courseCode;

    @JsonProperty( "business_name")
    private String businessName;

    @JsonProperty( "business_email")
    private String businessEmail;

    @JsonProperty( "external_reference_code")
    private String externalReferenceCode;

    @NotNull
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
    private List<AddressCourseRequestV1> headquatersCourse;

    @JsonProperty( "candidates")
    private List<CandidateCourseRequestV1> candidateList;

    @JsonProperty( "learners")
    private List<RecipientManagmentCourseRequestV1> recipientManagment;

    @JsonProperty( "partners")
    private List<PartnerCourseRequestV1> partnerList;

    @JsonProperty( "teachers")
    private List<TeacherCourseRequestV1> teacherList;

    @JsonProperty( "placement")
    private List<PlacementCourseRequestV1> placementList;

    @JsonProperty( "document_attachment")
    private String documentAttachment;

    public static class ActuatorSubjectCourseRequestV1{

        @JsonProperty("business_name")
        private String businessName;

        @JsonProperty("vat_number")
        private String vatNumber;

        @JsonProperty( "company")
        private String company;

        @JsonProperty( "email")
        private String email;

        @JsonProperty( "phone")
        private String phone;

        @JsonProperty( "fax")
        private String fax;

        @JsonProperty( "manager_name")
        private String managerName;

        @JsonProperty( "manager_number")
        private String managerNumber;

        @JsonProperty( "accredited_ft")
        private Boolean accreditedFt;

        @JsonProperty( "accredited_ft_code")
        private String accreditedFtCode;

        @JsonProperty( "cost_element")
        private String costElement;

        @JsonProperty( "note")
        private String note;

        @JsonProperty( "teacher_list")
        private List<PartnerRequestV1.TeacherPartnerRequestV1> teacherList = new LinkedList<>();

        @JsonProperty( "address")
        private List<PartnerRequestV1.AddressPartnerRequestV1> addressList = new LinkedList<>();

        public ActuatorSubjectCourseRequestV1() {
        }

        public ActuatorSubjectCourseRequestV1(String businessName, String vatNumber, String company, String email, String phone, String fax, String managerName, String managerNumber, Boolean accreditedFt, String accreditedFtCode, String costElement, String note, List<PartnerRequestV1.TeacherPartnerRequestV1> teacherList, List<PartnerRequestV1.AddressPartnerRequestV1> addressList) {
            this.businessName = businessName;
            this.vatNumber = vatNumber;
            this.company = company;
            this.email = email;
            this.phone = phone;
            this.fax = fax;
            this.managerName = managerName;
            this.managerNumber = managerNumber;
            this.accreditedFt = accreditedFt;
            this.accreditedFtCode = accreditedFtCode;
            this.costElement = costElement;
            this.note = note;
            this.teacherList = teacherList;
            this.addressList = addressList;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public String getVatNumber() {
            return vatNumber;
        }

        public void setVatNumber(String vatNumber) {
            this.vatNumber = vatNumber;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getManagerName() {
            return managerName;
        }

        public void setManagerName(String managerName) {
            this.managerName = managerName;
        }

        public String getManagerNumber() {
            return managerNumber;
        }

        public void setManagerNumber(String managerNumber) {
            this.managerNumber = managerNumber;
        }

        public Boolean getAccreditedFt() {
            return accreditedFt;
        }

        public void setAccreditedFt(Boolean accreditedFt) {
            this.accreditedFt = accreditedFt;
        }

        public String getAccreditedFtCode() {
            return accreditedFtCode;
        }

        public void setAccreditedFtCode(String accreditedFtCode) {
            this.accreditedFtCode = accreditedFtCode;
        }

        public String getCostElement() {
            return costElement;
        }

        public void setCostElement(String costElement) {
            this.costElement = costElement;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public List<PartnerRequestV1.TeacherPartnerRequestV1> getTeacherList() {
            return teacherList;
        }

        public void setTeacherList(List<PartnerRequestV1.TeacherPartnerRequestV1> teacherList) {
            this.teacherList = teacherList;
        }

        public List<PartnerRequestV1.AddressPartnerRequestV1> getAddressList() {
            return addressList;
        }

        public void setAddressList(List<PartnerRequestV1.AddressPartnerRequestV1> addressList) {
            this.addressList = addressList;
        }

        @Override
        public String toString() {
            return "ActuatorSubjectCourseRequestV1{" +
                    "businessName='" + businessName + '\'' +
                    ", vatNumber='" + vatNumber + '\'' +
                    ", company='" + company + '\'' +
                    ", email='" + email + '\'' +
                    ", phone='" + phone + '\'' +
                    ", fax='" + fax + '\'' +
                    ", managerName='" + managerName + '\'' +
                    ", managerNumber='" + managerNumber + '\'' +
                    ", accreditedFt=" + accreditedFt +
                    ", accreditedFtCode='" + accreditedFtCode + '\'' +
                    ", costElement='" + costElement + '\'' +
                    ", note='" + note + '\'' +
                    ", teacherList=" + teacherList +
                    ", addressList=" + addressList +
                    '}';
        }
    }

    public static class AddressCourseRequestV1{

        @JsonProperty("address")
        private TeacherRequestV1.AddressRequest address;

        @JsonProperty("main")
        private Boolean main;

        public AddressCourseRequestV1() {
        }

        public AddressCourseRequestV1(TeacherRequestV1.AddressRequest address, Boolean main) {
            this.address = address;
            this.main = main;
        }

        public TeacherRequestV1.AddressRequest getAddress() {
            return address;
        }

        public void setAddress(TeacherRequestV1.AddressRequest address) {
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

    public static class CandidateCourseRequestV1{

        @JsonProperty("id")
        private UUID id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("surname")
        private String surname;

        @JsonProperty("accepted")
        private Boolean accepted;

        public CandidateCourseRequestV1() {
        }

        public CandidateCourseRequestV1(UUID id, String name, String surname, Boolean accepted) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.accepted = accepted;
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

        @Override
        public String toString() {
            return "CandidateCourseRequestV1{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", accepted=" + accepted +
                    '}';
        }
    }

    public static class RecipientManagmentCourseRequestV1{

        @JsonProperty("learner_info")
        private LearnerRequestDtoV1 learner;

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
        private UploadRequestV1 withdrawnForm;

        @JsonProperty("num_issued_tickets")
        private Integer numIssuedTickets;

        public RecipientManagmentCourseRequestV1() {
        }

        public RecipientManagmentCourseRequestV1(LearnerRequestDtoV1 learner, RecipientTypeLearnerCourseEnum[] recipientType, Boolean exonerationGeneralSecurity, Boolean exonerationRightsAndDuties, Boolean generalSecurityModule, Boolean specificSecurityModule, Double necessaryHours, SecurityExonerateLearnerCourseEnum specificationSsecurityExonerate, Boolean withdrawn, ReasonWithdrawnLearnerCourseEnum withdrawnReason, Date withdrawnDate, UploadRequestV1 withdrawnForm, Integer numIssuedTickets) {
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
        }

        public LearnerRequestDtoV1 getLearner() {
            return learner;
        }

        public void setLearner(LearnerRequestDtoV1 learner) {
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

        public UploadRequestV1 getWithdrawnForm() {
            return withdrawnForm;
        }

        public void setWithdrawnForm(UploadRequestV1 withdrawnForm) {
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

        @Override
        public String toString() {
            return "RecipientManagmentCourseRequestV1{" +
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
                    '}';
        }

        public static class LearnerRequestDtoV1{

            @JsonProperty("id")
            private String id;

            @NotNull
            @JsonProperty("name")
            private String name;

            @NotNull
            @JsonProperty("surname")
            private String surname;

            @NotNull
            @JsonProperty("fiscal_code")
            private String fiscalCode;

            @NotNull
            @JsonProperty("date_of_birth")
            @JsonFormat(pattern = "yyyy-MM-dd")
            private Date dateOfBirth;

            @NotNull
            @JsonProperty("birth_place")
            private  String birthPlace;

            @JsonProperty("phone")
            private  String phone;

            @NotNull
            @JsonProperty("email")
            private String email;

            @NotNull
            @JsonProperty("degree_of_studies")
            private DegreeOfStudiesEnum degreeOfStudies;

            @JsonProperty("course_of_study")
            private String courseOfStudy;

            @JsonProperty("note")
            private String note;

            @NotNull
            @JsonProperty("attachments")
            private List<Attachment> attachments;

            @NotNull
            @JsonProperty("residential_address")
            private  TeacherRequestV1.AddressRequest residentialAddress;

            @JsonProperty("domicile_address")
            private  TeacherRequestV1.AddressRequest domicileAddress;

            @JsonProperty("domicile_equals_residential")
            private Boolean domicileEqualsResidential;

            public LearnerRequestDtoV1() {
            }

            public LearnerRequestDtoV1(String id, @NotNull String name, @NotNull String surname, @NotNull String fiscalCode, @NotNull Date dateOfBirth, @NotNull String birthPlace, String phone, @NotNull String email, @NotNull DegreeOfStudiesEnum degreeOfStudies, String courseOfStudy, String note, @NotNull List<Attachment> attachments, @NotNull TeacherRequestV1.AddressRequest residentialAddress, TeacherRequestV1.AddressRequest domicileAddress, Boolean domicileEqualsResidential) {
                this.id = id;
                this.name = name;
                this.surname = surname;
                this.fiscalCode = fiscalCode;
                this.dateOfBirth = dateOfBirth;
                this.birthPlace = birthPlace;
                this.phone = phone;
                this.email = email;
                this.degreeOfStudies = degreeOfStudies;
                this.courseOfStudy = courseOfStudy;
                this.note = note;
                this.attachments = attachments;
                this.residentialAddress = residentialAddress;
                this.domicileAddress = domicileAddress;
                this.domicileEqualsResidential = domicileEqualsResidential;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
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

            public String getFiscalCode() {
                return fiscalCode;
            }

            public void setFiscalCode(String fiscalCode) {
                this.fiscalCode = fiscalCode;
            }

            public Date getDateOfBirth() {
                return dateOfBirth;
            }

            public void setDateOfBirth(Date dateOfBirth) {
                this.dateOfBirth = dateOfBirth;
            }

            public String getBirthPlace() {
                return birthPlace;
            }

            public void setBirthPlace(String birthPlace) {
                this.birthPlace = birthPlace;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public DegreeOfStudiesEnum getDegreeOfStudies() {
                return degreeOfStudies;
            }

            public void setDegreeOfStudies(DegreeOfStudiesEnum degreeOfStudies) {
                this.degreeOfStudies = degreeOfStudies;
            }

            public String getCourseOfStudy() {
                return courseOfStudy;
            }

            public void setCourseOfStudy(String courseOfStudy) {
                this.courseOfStudy = courseOfStudy;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public List<Attachment> getAttachments() {
                return attachments;
            }

            public void setAttachments(List<Attachment> attachments) {
                this.attachments = attachments;
            }

            public TeacherRequestV1.AddressRequest getResidentialAddress() {
                return residentialAddress;
            }

            public void setResidentialAddress(TeacherRequestV1.AddressRequest residentialAddress) {
                this.residentialAddress = residentialAddress;
            }

            public TeacherRequestV1.AddressRequest getDomicileAddress() {
                return domicileAddress;
            }

            public void setDomicileAddress(TeacherRequestV1.AddressRequest domicileAddress) {
                this.domicileAddress = domicileAddress;
            }

            public Boolean getDomicileEqualsResidential() {
                return domicileEqualsResidential;
            }

            public void setDomicileEqualsResidential(Boolean domicileEqualsResidential) {
                this.domicileEqualsResidential = domicileEqualsResidential;
            }

            @Override
            public String toString() {
                return "LearnerRequestDtoV1{" +
                        "id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        ", surname='" + surname + '\'' +
                        ", fiscalCode='" + fiscalCode + '\'' +
                        ", dateOfBirth=" + dateOfBirth +
                        ", birthPlace='" + birthPlace + '\'' +
                        ", phone='" + phone + '\'' +
                        ", email='" + email + '\'' +
                        ", degreeOfStudies=" + degreeOfStudies +
                        ", courseOfStudy='" + courseOfStudy + '\'' +
                        ", note='" + note + '\'' +
                        ", attachments=" + attachments +
                        ", residentialAddress=" + residentialAddress +
                        ", domicileAddress=" + domicileAddress +
                        ", domicileEqualsResidential=" + domicileEqualsResidential +
                        '}';
            }
        }

    }

    public static class PartnerCourseRequestV1{

        @JsonProperty("supplier")
        private PartnerRequestV1 supplier;

        @JsonProperty("supply_services")
        private List<SupplierServiceRequestV1> supplyServices;

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
        private List<SubSupplierRequestV1> subSupplierList;

        public static class SupplierServiceRequestV1{

            @JsonProperty("supplier_service")
            private SupplyServicePartnerCourseEnum supplierService;

            @JsonProperty("service_cost")
            private Double serviceCost;

            public SupplierServiceRequestV1() {
            }

            public SupplierServiceRequestV1(SupplyServicePartnerCourseEnum supplierService, Double serviceCost) {
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
                if (!(o instanceof SupplierServiceRequestV1)) return false;
                SupplierServiceRequestV1 that = (SupplierServiceRequestV1) o;
                return supplierService == that.supplierService &&
                        Objects.equals(serviceCost, that.serviceCost);
            }

            @Override
            public int hashCode() {
                return Objects.hash(supplierService, serviceCost);
            }
        }

        public static class SubSupplierRequestV1{

            @JsonProperty("sub_supplier")
            private PartnerRequestV1 subSupplier;

            @JsonProperty("sub_suplly_service")
            private List<SupplyServicePartnerCourseEnum> subSupplierService;

            public SubSupplierRequestV1() {
            }

            public SubSupplierRequestV1(PartnerRequestV1 subSupplier, List<SupplyServicePartnerCourseEnum> subSupplierService) {
                this.subSupplier = subSupplier;
                this.subSupplierService = subSupplierService;
            }

            public PartnerRequestV1 getSubSupplier() {
                return subSupplier;
            }

            public void setSubSupplier(PartnerRequestV1 subSupplier) {
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

        public PartnerCourseRequestV1() {
        }

        public PartnerCourseRequestV1(PartnerRequestV1 supplier, List<SupplierServiceRequestV1> supplyServices, Date firstPaymentDate, Double amountFirstPaymen, Date secondPaymentDate, Double amountSecondPaymen, Date thirdPaymentDate, Double amountThirdPaymen, List<SubSupplierRequestV1> subSupplierList) {
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

        public PartnerRequestV1 getSupplier() {
            return supplier;
        }

        public void setSupplier(PartnerRequestV1 supplier) {
            this.supplier = supplier;
        }

        public List<SupplierServiceRequestV1> getSupplyServices() {
            return supplyServices;
        }

        public void setSupplyServices(List<SupplierServiceRequestV1> supplyServices) {
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

        public List<SubSupplierRequestV1> getSubSupplierList() {
            return subSupplierList;
        }

        public void setSubSupplierList(List<SubSupplierRequestV1> subSupplierList) {
            this.subSupplierList = subSupplierList;
        }

        @Override
        public String toString() {
            return "PartnerCourseRequestV1{" +
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

    public static class TeacherCourseRequestV1{

        @JsonProperty("teacher")
        private TeacherRequestDtoV1 teacher;

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
        private UploadRequestV1 tradeUninoTeachingLetter;

        @JsonProperty("learner_judgement_form")
        private UploadRequestV1 learnerJudgementForm;

        @JsonProperty("acronym_trade_union")
        private AcronymTradeUninoEnum acronymTradeUnion;

        @JsonProperty("payment_modality_trade_union_teaching")
        private PaymentModalityTradeUnionEnum paymentModalityTradeUnion;


        public TeacherCourseRequestV1() {
        }

        public TeacherCourseRequestV1(TeacherRequestDtoV1 teacher, RoleTeacherCourseEnum role, WorkingPositionEnum workingPosition, Double grossHourlyCost, PaymentModalityEnum paymentModality, Double netHourlyCost, Boolean main, Double hoursTeachingLetterAssignment, Double totalHoursPerformed, UploadRequestV1 tradeUninoTeachingLetter, UploadRequestV1 learnerJudgementForm, AcronymTradeUninoEnum acronymTradeUnion, PaymentModalityTradeUnionEnum paymentModalityTradeUnion) {
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
            this.acronymTradeUnion = acronymTradeUnion;
            this.paymentModalityTradeUnion = paymentModalityTradeUnion;
        }

        public TeacherRequestDtoV1 getTeacher() {
            return teacher;
        }

        public void setTeacher(TeacherRequestDtoV1 teacher) {
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

        public UploadRequestV1 getTradeUninoTeachingLetter() {
            return tradeUninoTeachingLetter;
        }

        public void setTradeUninoTeachingLetter(UploadRequestV1 tradeUninoTeachingLetter) {
            this.tradeUninoTeachingLetter = tradeUninoTeachingLetter;
        }

        public UploadRequestV1 getLearnerJudgementForm() {
            return learnerJudgementForm;
        }

        public void setLearnerJudgementForm(UploadRequestV1 learnerJudgementForm) {
            this.learnerJudgementForm = learnerJudgementForm;
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

        public AcronymTradeUninoEnum getAcronymTradeUnion() {
            return acronymTradeUnion;
        }

        public void setAcronymTradeUnion(AcronymTradeUninoEnum acronymTradeUnion) {
            this.acronymTradeUnion = acronymTradeUnion;
        }

        @Override
        public String toString() {
            return "TeacherCourseRequestV1{" +
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
                    ", acronymTradeUnion='" + acronymTradeUnion + '\'' +
                    ", paymentModalityTradeUnion=" + paymentModalityTradeUnion +
                    '}';
        }

        public static class TeacherRequestDtoV1{

            @JsonProperty("id")
            private String id;

            @NotNull
            @JsonProperty("name")
            private String name;

            @NotNull
            @JsonProperty("surname")
            private String surname;

            @NotNull
            @JsonProperty("fiscal_code")
            private String fiscalCode;

            @NotNull
            @JsonProperty("date_of_birth")
            @JsonFormat(pattern = "yyyy-MM-dd")
            private Date dateOfBirth;

            @NotNull
            @JsonProperty("birth_place")
            private  String birthPlace;

            @JsonProperty("phone")
            private  String phone;

            @NotNull
            @JsonProperty("email")
            private String email;

            //DA CONSIDERARE COME EVENTUALE CLASSE ENUMERATA O RIFERIMENTO A TABELLA ESTERNA
            @NotNull
            @JsonProperty("professional_area")
            private String professionalArea;

            @JsonProperty("public_employee")
            private Boolean publicEmployee;

            @JsonProperty("accredited_ft")
            private Boolean accreditedFt;

            @JsonProperty("accredited_ft_code")
            private String accreditedFtCode;

            @JsonProperty("authorized")
            private Boolean authorized;

            @JsonProperty("professional_order_registration")
            private Boolean professionalOrderRegistration;

            @JsonProperty("register")
            private String register;

            @JsonProperty("vat_holder")
            private Boolean vatHolder;

            @JsonProperty("vat_number")
            private String vatNumber;

            @JsonProperty("sector")
            private String sector;

            @JsonProperty("note")
            private String note;

            @NotNull
            @JsonProperty("curriculum_vitae")
            private Attachment curriculum;

            @NotNull
            @JsonProperty("residential_address")
            private  TeacherRequestV1.AddressRequest residentialAddress;

            @JsonProperty("domicile_address")
            private  TeacherRequestV1.AddressRequest domicileAddress;

            @JsonProperty("domicile_equals_residential")
            private Boolean domicileEqualsResidential;

            public TeacherRequestDtoV1() {
            }

            public TeacherRequestDtoV1(String id, @NotNull String name, @NotNull String surname, @NotNull String fiscalCode, @NotNull Date dateOfBirth, @NotNull String birthPlace, String phone, @NotNull String email, @NotNull String professionalArea, Boolean publicEmployee, Boolean accreditedFt, String accreditedFtCode, Boolean authorized, Boolean professionalOrderRegistration, String register, Boolean vatHolder, String vatNumber, String sector, String note, @NotNull Attachment curriculum, @NotNull TeacherRequestV1.AddressRequest residentialAddress, TeacherRequestV1.AddressRequest domicileAddress, Boolean domicileEqualsResidential) {
                this.id = id;
                this.name = name;
                this.surname = surname;
                this.fiscalCode = fiscalCode;
                this.dateOfBirth = dateOfBirth;
                this.birthPlace = birthPlace;
                this.phone = phone;
                this.email = email;
                this.professionalArea = professionalArea;
                this.publicEmployee = publicEmployee;
                this.accreditedFt = accreditedFt;
                this.accreditedFtCode = accreditedFtCode;
                this.authorized = authorized;
                this.professionalOrderRegistration = professionalOrderRegistration;
                this.register = register;
                this.vatHolder = vatHolder;
                this.vatNumber = vatNumber;
                this.sector = sector;
                this.note = note;
                this.curriculum = curriculum;
                this.residentialAddress = residentialAddress;
                this.domicileAddress = domicileAddress;
                this.domicileEqualsResidential = domicileEqualsResidential;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
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

            public String getFiscalCode() {
                return fiscalCode;
            }

            public void setFiscalCode(String fiscalCode) {
                this.fiscalCode = fiscalCode;
            }

            public Date getDateOfBirth() {
                return dateOfBirth;
            }

            public void setDateOfBirth(Date dateOfBirth) {
                this.dateOfBirth = dateOfBirth;
            }

            public String getBirthPlace() {
                return birthPlace;
            }

            public void setBirthPlace(String birthPlace) {
                this.birthPlace = birthPlace;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getProfessionalArea() {
                return professionalArea;
            }

            public void setProfessionalArea(String professionalArea) {
                this.professionalArea = professionalArea;
            }

            public Boolean getPublicEmployee() {
                return publicEmployee;
            }

            public void setPublicEmployee(Boolean publicEmployee) {
                this.publicEmployee = publicEmployee;
            }

            public Boolean getAccreditedFt() {
                return accreditedFt;
            }

            public void setAccreditedFt(Boolean accreditedFt) {
                this.accreditedFt = accreditedFt;
            }

            public String getAccreditedFtCode() {
                return accreditedFtCode;
            }

            public void setAccreditedFtCode(String accreditedFtCode) {
                this.accreditedFtCode = accreditedFtCode;
            }

            public Boolean getAuthorized() {
                return authorized;
            }

            public void setAuthorized(Boolean authorized) {
                this.authorized = authorized;
            }

            public Boolean getProfessionalOrderRegistration() {
                return professionalOrderRegistration;
            }

            public void setProfessionalOrderRegistration(Boolean professionalOrderRegistration) {
                this.professionalOrderRegistration = professionalOrderRegistration;
            }

            public String getRegister() {
                return register;
            }

            public void setRegister(String register) {
                this.register = register;
            }

            public Boolean getVatHolder() {
                return vatHolder;
            }

            public void setVatHolder(Boolean vatHolder) {
                this.vatHolder = vatHolder;
            }

            public String getVatNumber() {
                return vatNumber;
            }

            public void setVatNumber(String vatNumber) {
                this.vatNumber = vatNumber;
            }

            public String getSector() {
                return sector;
            }

            public void setSector(String sector) {
                this.sector = sector;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public Attachment getCurriculum() {
                return curriculum;
            }

            public void setCurriculum(Attachment curriculum) {
                this.curriculum = curriculum;
            }

            public TeacherRequestV1.AddressRequest getResidentialAddress() {
                return residentialAddress;
            }

            public void setResidentialAddress(TeacherRequestV1.AddressRequest residentialAddress) {
                this.residentialAddress = residentialAddress;
            }

            public TeacherRequestV1.AddressRequest getDomicileAddress() {
                return domicileAddress;
            }

            public void setDomicileAddress(TeacherRequestV1.AddressRequest domicileAddress) {
                this.domicileAddress = domicileAddress;
            }

            public Boolean getDomicileEqualsResidential() {
                return domicileEqualsResidential;
            }

            public void setDomicileEqualsResidential(Boolean domicileEqualsResidential) {
                this.domicileEqualsResidential = domicileEqualsResidential;
            }

            @Override
            public String toString() {
                return "TeacherRequestDtoV1{" +
                        "id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        ", surname='" + surname + '\'' +
                        ", fiscalCode='" + fiscalCode + '\'' +
                        ", dateOfBirth=" + dateOfBirth +
                        ", birthPlace='" + birthPlace + '\'' +
                        ", phone='" + phone + '\'' +
                        ", email='" + email + '\'' +
                        ", professionalArea='" + professionalArea + '\'' +
                        ", publicEmployee=" + publicEmployee +
                        ", accreditedFt=" + accreditedFt +
                        ", accreditedFtCode='" + accreditedFtCode + '\'' +
                        ", authorized=" + authorized +
                        ", professionalOrderRegistration=" + professionalOrderRegistration +
                        ", register='" + register + '\'' +
                        ", vatHolder=" + vatHolder +
                        ", vatNumber='" + vatNumber + '\'' +
                        ", sector='" + sector + '\'' +
                        ", note='" + note + '\'' +
                        ", curriculum=" + curriculum +
                        ", residentialAddress=" + residentialAddress +
                        ", domicileAddress=" + domicileAddress +
                        ", domicileEqualsResidential=" + domicileEqualsResidential +
                        '}';
            }
        }
    }

    public static class PlacementCourseRequestV1{

        @JsonProperty("learner")
        private RecipientManagmentCourseRequestV1.LearnerRequestDtoV1 learner;

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

        public PlacementCourseRequestV1() {
        }

        public PlacementCourseRequestV1(RecipientManagmentCourseRequestV1.LearnerRequestDtoV1 learner, Date hiringDate, Double missionHours, Double bonusAmount, Date expiredPlacementDate, Date sendedEletronicPlacementDate, Boolean coherence, String note) {
            this.learner = learner;
            this.hiringDate = hiringDate;
            this.missionHours = missionHours;
            this.bonusAmount = bonusAmount;
            this.expiredPlacementDate = expiredPlacementDate;
            this.sendedEletronicPlacementDate = sendedEletronicPlacementDate;
            this.coherence = coherence;
            this.note = note;
        }

        public RecipientManagmentCourseRequestV1.LearnerRequestDtoV1 getLearner() {
            return learner;
        }

        public void setLearner(RecipientManagmentCourseRequestV1.LearnerRequestDtoV1 learner) {
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

    public CourseRequestV1() {
    }

    public CourseRequestV1(@NotNull String courseTitle, CourseStatusEnum status, @NotNull ContentsAreaCourseEnum contentsArea, LearnerTypeCourseEnum learnerType, SupplyModalityCourseEnum supplyModality, PaymentModalityEnum paymentModality, Double costs, @NotNull FoundsTypeCourseEnum foundsTypeCourse, String educationalTargetDescription, String courseDescription, @NotNull Date courseStartDate, @NotNull Date courseEndDate, Double theoryHours, Double practiceHours, Double coachingHours, Double visitHours, Double skilsAnalysisHours, Double totalHours, Double totalHoursTraining, Double dailyHours, CertificateTypeCourseEnum certificateTypeCourse, Integer minimumNumericOfParticipants, Boolean disabled, UploadRequestV1 courseLogo, PartFullTimeCourseEnum partFullTimeCourse, Date morningStartHour, Date morningEndHour, Date afternoonStartHour, Date afternoonEndHour, PartnerRequestV1 actuatorSubject, @NotNull String courseCode, String businessName, String businessEmail, String externalReferenceCode, @NotNull CourseTypeEnum courseType, Date sendedProjectDate, Date receiptFTConfirmationDate, Date sendedCanceledProjectDate, Date autProgetctFTRealizedDate, Date sendedLearnersFTDate, Double entourageHours, Double orenatationHours, SpecialInitiativesCourseEnum specialInitiatives, Boolean tradeUnionTeachingRequest, String note, RecipientTypeLearnerCourseEnum recipient, Boolean issueTicket, Double ticketAmount, Integer numberOfTickets, Boolean attendanceBenefits, Double amountAttendanceBenefits, Double amountReportFT, Double amountFinSecurityCapital, Date amountAutorizedFTDate, Double amountAutorizedFT, Double totalPartnerCost, Double totalPartnerCostOnPercent, Double totalAmountWithoutFS, Date sendedPaperReportingDate, Date sendedEletronicReportingDate, Date expiredReportingDate, Date invoiceAuthorizationDate, Date deliveryDateInAdministration, Date commercialTaxableCommunicationDate, String reportNote, Boolean invoiceAuthorization, String accountingCode, String dailyRegister, List<AddressCourseRequestV1> headquatersCourse, List<CandidateCourseRequestV1> candidateList, List<RecipientManagmentCourseRequestV1> recipientManagment, List<PartnerCourseRequestV1> partnerList, List<TeacherCourseRequestV1> teacherList, List<PlacementCourseRequestV1> placementList, String documentAttachment) {
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

    public UploadRequestV1 getCourseLogo() {
        return courseLogo;
    }

    public void setCourseLogo(UploadRequestV1 courseLogo) {
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

    public PartnerRequestV1 getActuatorSubject() {
        return actuatorSubject;
    }

    public void setActuatorSubject(PartnerRequestV1 actuatorSubject) {
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

    public List<AddressCourseRequestV1> getHeadquatersCourse() {
        return headquatersCourse;
    }

    public void setHeadquatersCourse(List<AddressCourseRequestV1> headquatersCourse) {
        this.headquatersCourse = headquatersCourse;
    }

    public List<CandidateCourseRequestV1> getCandidateList() {
        return candidateList;
    }

    public void setCandidateList(List<CandidateCourseRequestV1> candidateList) {
        this.candidateList = candidateList;
    }

    public List<RecipientManagmentCourseRequestV1> getRecipientManagment() {
        return recipientManagment;
    }

    public void setRecipientManagment(List<RecipientManagmentCourseRequestV1> recipientManagment) {
        this.recipientManagment = recipientManagment;
    }

    public List<PartnerCourseRequestV1> getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(List<PartnerCourseRequestV1> partnerList) {
        this.partnerList = partnerList;
    }

    public List<TeacherCourseRequestV1> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<TeacherCourseRequestV1> teacherList) {
        this.teacherList = teacherList;
    }

    public List<PlacementCourseRequestV1> getPlacementList() {
        return placementList;
    }

    public void setPlacementList(List<PlacementCourseRequestV1> placementList) {
        this.placementList = placementList;
    }

    public String getDocumentAttachment() {
        return documentAttachment;
    }

    public void setDocumentAttachment(String documentAttachment) {
        this.documentAttachment = documentAttachment;
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

    @Override
    public String toString() {
        return "CourseRequestV1{" +
                "courseTitle='" + courseTitle + '\'' +
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
                ", courseLogo='" + courseLogo + '\'' +
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
                ", documentAttachment='" + documentAttachment + '\'' +
                '}';
    }
}
