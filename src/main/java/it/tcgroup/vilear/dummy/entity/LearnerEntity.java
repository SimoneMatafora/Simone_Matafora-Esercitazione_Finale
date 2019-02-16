package it.tcgroup.vilear.dummy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.tcgroup.vilear.dummy.entity.enumerated.DegreeOfStudiesEnum;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "hibernate_lazy_initializer", "handler"})
@Table(name = "learner")
public class LearnerEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "username")
    private String username;

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

    @Column(name = "curriculum_vitae")
    private String curriculumVitae;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private AddressEntity address;

    public LearnerEntity() {
    }

    public LearnerEntity(UUID id, String username, String name, String surname, String fiscalCode, Date dateOfBirth, String birthPlace, String phone, String email, DegreeOfStudiesEnum degreeOfStudies, String courseOfStudy, String note, String curriculumVitae, AddressEntity address) {
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "LearnerEntity{" +
                "id=" + id +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LearnerEntity that = (LearnerEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
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
                Objects.equals(curriculumVitae, that.curriculumVitae) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, name, surname, fiscalCode, dateOfBirth, birthPlace, phone, email, degreeOfStudies, courseOfStudy, note, curriculumVitae, address);
    }
}
