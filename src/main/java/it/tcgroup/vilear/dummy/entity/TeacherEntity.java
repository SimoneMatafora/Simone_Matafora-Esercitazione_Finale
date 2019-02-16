package it.tcgroup.vilear.dummy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "hibernate_lazy_initializer", "handler"})
@Table(name = "teacher")
public class TeacherEntity implements Serializable {

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

    //DA CONSIDERARE COME EVENTUALE CLASSE ENUMERATA O RIFERIMENTO A TABELLA ESTERNA
    @Column(name = "professional_area")
    private String professionalArea;

    @Column(name = "public_employee")
    private Boolean publicEmployee;

    @Column(name = "accredited_ft")
    private Boolean accreditedFt;

    @Column(name = "accredited_ft_code")
    private String accreditedFtCode;

    @Column(name = "authorized")
    private Boolean authorized;

    @Column(name = "professional_order_registration")
    private Boolean professionalOrderRegistration;

    @Column(name = "register")
    private String register;

    @Column(name = "vat_holder")
    private Boolean vatHolder;

    @Column(name = "vat_number")
    private String vatNumber;

    @Column(name = "sector")
    private String sector;

    @Column(name = "note")
    private String note;

    @Column(name = "curriculum_vitae")
    private String curriculumVitae;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private AddressEntity address;

    public TeacherEntity() {
    }

    public TeacherEntity(UUID id, String username, String name, String surname, String fiscalCode, Date dateOfBirth, String birthPlace, String phone, String email, String professionalArea, Boolean publicEmployee, Boolean accreditedFt, String accreditedFtCode, Boolean authorized, Boolean professionalOrderRegistration, String register, Boolean vatHolder, String vatNumber, String sector, String note, String curriculumVitae, AddressEntity address) {
        this.id = id;
        this.username = username;
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
        return "TeacherEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
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
                ", curriculumVitae='" + curriculumVitae + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherEntity that = (TeacherEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(fiscalCode, that.fiscalCode) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(birthPlace, that.birthPlace) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(professionalArea, that.professionalArea) &&
                Objects.equals(publicEmployee, that.publicEmployee) &&
                Objects.equals(accreditedFt, that.accreditedFt) &&
                Objects.equals(accreditedFtCode, that.accreditedFtCode) &&
                Objects.equals(authorized, that.authorized) &&
                Objects.equals(professionalOrderRegistration, that.professionalOrderRegistration) &&
                Objects.equals(register, that.register) &&
                Objects.equals(vatHolder, that.vatHolder) &&
                Objects.equals(vatNumber, that.vatNumber) &&
                Objects.equals(sector, that.sector) &&
                Objects.equals(note, that.note) &&
                Objects.equals(curriculumVitae, that.curriculumVitae) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, name, surname, fiscalCode, dateOfBirth, birthPlace, phone, email, professionalArea, publicEmployee, accreditedFt, accreditedFtCode, authorized, professionalOrderRegistration, register, vatHolder, vatNumber, sector, note, curriculumVitae, address);
    }
}

