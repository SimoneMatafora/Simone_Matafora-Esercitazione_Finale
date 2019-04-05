package it.tcgroup.vilear.coursemanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.tcgroup.vilear.coursemanager.entity.enumerated.DegreeOfStudiesEnum;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Address;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;
import it.tcgroup.vilear.coursemanager.entity.jsonb.dataType.JsonDataAddresType;
import it.tcgroup.vilear.coursemanager.entity.jsonb.dataType.JsonDataAttachmentListType;
import it.tcgroup.vilear.coursemanager.entity.jsonb.dataType.JsonDataAttachmentType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "hibernate_lazy_initializer", "handler"})
@Table(name = "learner")
@TypeDefs({
        @TypeDef(name = "JsonDataAddressType", typeClass = JsonDataAddresType.class),
        @TypeDef(name = "JsonDataAttachmentListType", typeClass = JsonDataAttachmentListType.class)
})
public class LearnerEntity implements Serializable {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "fiscal_code")
    private String fiscalCode;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "date_of_birth", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date dateOfBirth;

    @Column(name = "birth_place")
    private  String birthPlace;

    @Column(name = "phone")
    private  String phone;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "degree_of_studies")
    private DegreeOfStudiesEnum degreeOfStudies;

    @Column(name = "course_of_study")
    private String courseOfStudy;

    @Column(name = "note")
    private String note;

    @Type(type = "JsonDataAttachmentListType")
    @Column(name = "attachments")
    private List<Attachment> attachments;

    @Type(type = "JsonDataAddressType")
    @Column(name = "address")
    private Address address;

    public LearnerEntity() {
    }

    public LearnerEntity(UUID id, String name, String surname, String fiscalCode, Date dateOfBirth, String birthPlace, String phone, String email, DegreeOfStudiesEnum degreeOfStudies, String courseOfStudy, String note, List<Attachment> attachments, Address address) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "LearnerEntity{" +
                "id=" + id +
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
                Objects.equals(attachments, that.attachments) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, fiscalCode, dateOfBirth, birthPlace, phone, email, degreeOfStudies, courseOfStudy, note, attachments, address);
    }
}
