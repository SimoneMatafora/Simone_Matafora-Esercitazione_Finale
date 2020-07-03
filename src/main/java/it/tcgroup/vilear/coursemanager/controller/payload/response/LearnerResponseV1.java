package it.tcgroup.vilear.coursemanager.controller.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.enumerated.DegreeOfStudiesEnum;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Address;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class LearnerResponseV1  implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("fiscal_code")
    private String fiscalCode;

    @JsonProperty("date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;


    @JsonProperty("birth_place")
    private String birthPlace;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("email")
    private String email;


    @JsonProperty( "degree_of_studies")
    @Enumerated(EnumType.STRING)
    private DegreeOfStudiesEnum degreeOfStudies;


    @JsonProperty("course_of_study")
    private String courseOfStudy;

    @JsonProperty("note")
    private String note;

    @Type(type="JsonDataAddressType")
    @Column(name="residential_address")
    private AddressResponseV1 residentialAddress;

    @Type(type="JsonDataAddressType")
    @Column(name="domicile_address")
    private AddressResponseV1 domicileAddress;


/*-----------------------------------------------------------------*/


    public LearnerResponseV1(String id, String name, String surname, String fiscalCode, Date dateOfBirth, String birthPlace, String phone, String email, DegreeOfStudiesEnum degreeOfStudies, String courseOfStudy, String note, AddressResponseV1 residentialAddress, AddressResponseV1 domicileAddress) {
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
        this.residentialAddress = residentialAddress;
        this.domicileAddress = domicileAddress;
    }

    public LearnerResponseV1() {

    }
    /*---------------------------------------------------------------------------*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public AddressResponseV1 getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(AddressResponseV1 residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public AddressResponseV1 getDomicileAddress() {
        return domicileAddress;
    }

    public void setDomicileAddress(AddressResponseV1 domicileAddress) {
        this.domicileAddress = domicileAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*---------------------------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LearnerResponseV1 that = (LearnerResponseV1) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(fiscalCode, that.fiscalCode) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(birthPlace, that.birthPlace) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                degreeOfStudies == that.degreeOfStudies &&
                Objects.equals(courseOfStudy, that.courseOfStudy) &&
                Objects.equals(note, that.note) &&
                Objects.equals(residentialAddress, that.residentialAddress) &&
                Objects.equals(domicileAddress, that.domicileAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, fiscalCode, dateOfBirth, birthPlace, phone, email, degreeOfStudies, courseOfStudy, note, residentialAddress, domicileAddress);
    }

    @Override
    public String toString() {
        return "LearnerResponseV1{" +
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
                ", residentialAddress=" + residentialAddress +
                ", domicileAddress=" + domicileAddress +
                '}';
    }
}
