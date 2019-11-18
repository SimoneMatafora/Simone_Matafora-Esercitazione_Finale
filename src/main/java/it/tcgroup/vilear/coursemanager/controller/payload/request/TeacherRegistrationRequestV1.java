package it.tcgroup.vilear.coursemanager.controller.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.controller.payload.request.TeacherRequestV1.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class TeacherRegistrationRequestV1 {

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
    private UploadRequestV1 curriculum;

    @NotNull
    @JsonProperty("residential_address")
    private AddressRequest residentialAddress;

    @JsonProperty("domicile_address")
    private AddressRequest domicileAddress;

    @JsonProperty("domicile_equals_residential")
    private Boolean domicileEqualsResidential;

    public TeacherRegistrationRequestV1() {
    }

    public TeacherRegistrationRequestV1(String id, @NotNull String name, @NotNull String surname, @NotNull String fiscalCode, @NotNull Date dateOfBirth, @NotNull String birthPlace, String phone, @NotNull String email, @NotNull String professionalArea, Boolean publicEmployee, Boolean accreditedFt, String accreditedFtCode, Boolean authorized, Boolean professionalOrderRegistration, String register, Boolean vatHolder, String vatNumber, String sector, String note, @NotNull UploadRequestV1 curriculum, @NotNull TeacherRequestV1.AddressRequest residentialAddress, TeacherRequestV1.AddressRequest domicileAddress, Boolean domicileEqualsResidential) {
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

    public UploadRequestV1 getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(UploadRequestV1 curriculum) {
        this.curriculum = curriculum;
    }

    public AddressRequest getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(AddressRequest residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public AddressRequest getDomicileAddress() {
        return domicileAddress;
    }

    public void setDomicileAddress(AddressRequest domicileAddress) {
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
        return "TeacherRequestV1{" +
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
