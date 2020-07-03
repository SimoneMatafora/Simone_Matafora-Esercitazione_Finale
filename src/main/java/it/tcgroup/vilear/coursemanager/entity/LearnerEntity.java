package it.tcgroup.vilear.coursemanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.tcgroup.vilear.coursemanager.entity.enumerated.DegreeOfStudiesEnum;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Address;
import it.tcgroup.vilear.coursemanager.entity.jsonb.dataType.JsonDataAddresType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;




@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "hibernate_lazy_initializer", "handler"})
@Table(name = "learner")
@TypeDefs({
        @TypeDef(name = "JsonDataAddressType", typeClass = JsonDataAddresType.class)
        /* @TypeDef(name = "JsonDataDateType", typeClass = JsonDataDateType.class)*/
})
public class LearnerEntity implements Serializable{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )

    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "fiscal_code")
    private String fiscalCode;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;


    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "degree_of_studies")
    @Enumerated(EnumType.STRING)
    private DegreeOfStudiesEnum degreeOfStudies;


    @Column(name = "course_of_study")
    private String courseOfStudy;

    @Column(name = "note")
    private String note;

    @Type(type="JsonDataAddressType")
    @Column(name="residential_address")
    private Address residentialAddress;

    @Type(type="JsonDataAddressType")
    @Column(name="domicile_address")
    private Address domicileAddress;

    @Column(name = "domicile_equals_residential")
    private Boolean domicileEqualsResidential;

    /*-------------------------------Autogenearated method--------------------------*/

    public LearnerEntity(UUID id, String name, String surname, String fiscalCode, Date dateOfBirth, String birthPlace, String phone, String email, DegreeOfStudiesEnum degreeOfStudies, String courseOfStudy, String note, Address residentialAddress, Address domicileAddress, Boolean domicileEqualsResidential) {
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
        this.domicileEqualsResidential = domicileEqualsResidential;
    }

    public LearnerEntity() {
    }

    public DegreeOfStudiesEnum getDegreeOfStudies() {
        return degreeOfStudies;
    }

    public void setDegreeOfStudies(DegreeOfStudiesEnum degreeOfStudies) {
        this.degreeOfStudies = degreeOfStudies;
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

    public Address getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(Address residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public Address getDomicileAddress() {
        return domicileAddress;
    }

    public void setDomicileAddress(Address domicileAddress) {
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
        return "LearnerEntity{" +
                "DegreeOfStudiesEnum=" + degreeOfStudies +
                ", id=" + id +
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
                ", domicileEqualsResidential=" + domicileEqualsResidential +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LearnerEntity that = (LearnerEntity) o;
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
                Objects.equals(domicileAddress, that.domicileAddress) &&
                Objects.equals(domicileEqualsResidential, that.domicileEqualsResidential);
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, name, surname, fiscalCode, dateOfBirth, birthPlace, phone, email, degreeOfStudies, courseOfStudy, note, residentialAddress, domicileAddress, domicileEqualsResidential);
    }


}

