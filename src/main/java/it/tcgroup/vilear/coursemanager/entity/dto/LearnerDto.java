package it.tcgroup.vilear.coursemanager.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.enumerated.DegreeOfStudiesEnum;

import java.io.Serializable;
import java.util.Date;

public class LearnerDto implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty( "username")
    private String username;

    @JsonProperty( "name")
    private String name;

    @JsonProperty( "surname")
    private String surname;

    @JsonProperty( "fiscal_code")
    private String fiscalCode;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty( "date_of_birth")
    private Date dateOfBirth;

    @JsonProperty( "birth_place")
    private  String birthPlace;

    @JsonProperty( "phone")
    private  String phone;

    @JsonProperty( "email")
    private String email;

    @JsonProperty( "degree_of_studies")
    private DegreeOfStudiesEnum degreeOfStudies;

    @JsonProperty( "course_of_study")
    private String courseOfStudy;

    @JsonProperty( "note")
    private String note;

    @JsonProperty( "curriculum_vitae")
    private String curriculumVitae;
    
    @JsonProperty( "address")
    private AddressDto address;

    public LearnerDto() {
    }

    public LearnerDto(String id, String username, String name, String surname, String fiscalCode, Date dateOfBirth, String birthPlace, String phone, String email, DegreeOfStudiesEnum degreeOfStudies, String courseOfStudy, String note, String curriculumVitae, AddressDto address) {
        this.id = id;
        this.username = username;
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
        this.curriculumVitae = curriculumVitae;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getCurriculumVitae() {
        return curriculumVitae;
    }

    public void setCurriculumVitae(String curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "LearnerDto{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
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
                ", curriculumVitae='" + curriculumVitae + '\'' +
                ", address=" + address +
                '}';
    }
}