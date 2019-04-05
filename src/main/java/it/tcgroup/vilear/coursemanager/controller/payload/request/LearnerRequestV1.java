package it.tcgroup.vilear.coursemanager.controller.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.controller.payload.request.TeacherRequestV1.*;
import it.tcgroup.vilear.coursemanager.entity.enumerated.DegreeOfStudiesEnum;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class LearnerRequestV1 {

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
    private List<UploadRequestV1> attachments;

    @NotNull
    @JsonProperty("address")
    private AddressRequest address;

    public LearnerRequestV1() {
    }

    public LearnerRequestV1(String id, @NotNull String name, @NotNull String surname, @NotNull String fiscalCode, @NotNull Date dateOfBirth, @NotNull String birthPlace, String phone, @NotNull String email, @NotNull DegreeOfStudiesEnum degreeOfStudies, String courseOfStudy, String note, @NotNull List<UploadRequestV1> attachments, @NotNull AddressRequest address) {
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
        this.address = address;
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

    public List<UploadRequestV1> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<UploadRequestV1> attachments) {
        this.attachments = attachments;
    }

    public AddressRequest getAddress() {
        return address;
    }

    public void setAddress(AddressRequest address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "LearnerRequestV1{" +
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
                ", address=" + address +
                '}';
    }
}
