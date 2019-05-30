package it.tcgroup.vilear.coursemanager.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;

import java.io.Serializable;
import java.util.Date;

public class TeacherDto implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("fiscal_code")
    private String fiscalCode;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("birth_place")
    private  String birthPlace;

    @JsonProperty("phone")
    private  String phone;

    @JsonProperty("email")
    private String email;

    //DA CONSIDERARE COME EVENTUALE CLASSE ENUMERATA O RIFERIMENTO A TABELLA ESTERNA
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

    @JsonProperty("curriculum_vitae")
    private Attachment curriculumVitae;

    @JsonProperty("residential_address")
    private AddressDto residentialAddress;

    @JsonProperty("domicile_address")
    private AddressDto domicileAddress;

    @JsonProperty("domicile_equals_residential")
    private Boolean domicileEqualsResidential ;

    public TeacherDto() {
    }

    public TeacherDto(String id, String name, String surname, String fiscalCode, Date dateOfBirth, String birthPlace, String phone, String email, String professionalArea, Boolean publicEmployee, Boolean accreditedFt, String accreditedFtCode, Boolean authorized, Boolean professionalOrderRegistration, String register, Boolean vatHolder, String vatNumber, String sector, String note, Attachment curriculumVitae, AddressDto residentialAddress, AddressDto domicileAddress, Boolean domicileEqualsResidential) {
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
        this.curriculumVitae = curriculumVitae;
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

    public Attachment getCurriculumVitae() {
        return curriculumVitae;
    }

    public void setCurriculumVitae(Attachment curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
    }

    public AddressDto getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(AddressDto residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public AddressDto getDomicileAddress() {
        return domicileAddress;
    }

    public void setDomicileAddress(AddressDto domicileAddress) {
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
        return "TeacherDto{" +
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
                ", curriculumVitae=" + curriculumVitae +
                ", residentialAddress=" + residentialAddress +
                ", domicileAddress=" + domicileAddress +
                ", domicileEqualsResidential=" + domicileEqualsResidential +
                '}';
    }
}
