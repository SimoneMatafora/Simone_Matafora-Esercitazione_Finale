package it.tcgroup.vilear.coursemanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.tcgroup.vilear.coursemanager.entity.enumerated.AddressPartnerEntity;
import it.tcgroup.vilear.coursemanager.entity.enumerated.StatusTeacherPartnerEnum;
import it.tcgroup.vilear.coursemanager.entity.enumerated.TypeAddressPartnerEnum;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "hibernate_lazy_initializer", "handler"})
@Table(name = "partner")
public class PartnerEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "vat_number")
    private String vatNumber;

    @Column(name = "company")
    private String company;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "manager_number")
    private String managerNumber;

    @Column(name = "accredited_ft")
    private String accreditedFt;

    @Column(name = "accredited_ft_code")
    private String accreditedFtCode;

    @Column(name = "cost_element")
    private String costElement;

    @Column(name = "note")
    private String note;

    @OneToMany(
            mappedBy = "partnentEntityTeacher",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TeacherPartnerEntity> teacherPartnerList = new LinkedList<>();


    @OneToMany(
            mappedBy = "partnentEntityAddress",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AddressPartnerEntity> addressPartnerList = new LinkedList<>();

    public PartnerEntity() {
    }

    public PartnerEntity(UUID id, String businessName, String vatNumber, String company, String email, String phone, String fax, String managerName, String managerNumber, String accreditedFt, String accreditedFtCode, String costElement, String note, List<TeacherPartnerEntity> teacherPartnerList, List<AddressPartnerEntity> addressPartnerList) {
        this.id = id;
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
        this.teacherPartnerList = teacherPartnerList;
        this.addressPartnerList = addressPartnerList;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getAccreditedFt() {
        return accreditedFt;
    }

    public void setAccreditedFt(String accreditedFt) {
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

    @JsonIgnore
    public List<TeacherPartnerEntity> getTeacherPartnerList() {
        return teacherPartnerList;
    }

    public void setTeacherPartnerList(List<TeacherPartnerEntity> teacherPartnerList) {
        this.teacherPartnerList = teacherPartnerList;
    }

    @JsonIgnore
    public List<AddressPartnerEntity> getAddressPartnerList() {
        return addressPartnerList;
    }

    public void setAddressPartnerList(List<AddressPartnerEntity> addressPartnerList) {
        this.addressPartnerList = addressPartnerList;
    }

    public void addAddressOnPartner(AddressEntity address, TypeAddressPartnerEnum typeAddress){
        AddressPartnerEntity addressPartner = new AddressPartnerEntity(this,address);
        this.addressPartnerList.add(addressPartner);
        address.getAddressPartnerList().add(addressPartner);
    }

    public void deleteAddressOnPartner(AddressEntity address){

        Iterator<AddressPartnerEntity> iterator = this.addressPartnerList.iterator();
        while(iterator.hasNext()){

            AddressPartnerEntity addressPartner = iterator.next();
            if((addressPartner.getPartnerEntity().equals(this)) &&
                    addressPartner.getAddressEntity().equals(address) ){

                iterator.remove();
                addressPartner.getAddressEntity().getAddressPartnerList().remove(addressPartner);
                addressPartner.setPartnerEntity(null);
                addressPartner.setAddressEntity(null);

                break;
            }

        }

    }

    public void addTheacherOnPrtner(TeacherEntity teacher, StatusTeacherPartnerEnum status){
        TeacherPartnerEntity teacherPartner = new TeacherPartnerEntity(this, teacher);
        this.teacherPartnerList.add(teacherPartner);
        teacher.getTecherPartnerList().add(teacherPartner);
    }

    public void deleteTeacherOnPartner(TeacherEntity teacher){

        Iterator<TeacherPartnerEntity> iterator = this.teacherPartnerList.iterator();
        while(iterator.hasNext()){

            TeacherPartnerEntity teacherPartner = iterator.next();
            if((teacherPartner.getPartnerEntity().equals(this)) &&
                teacherPartner.getTeacherEntity().equals(teacher) ){

                iterator.remove();
                teacherPartner.getTeacherEntity().getTecherPartnerList().remove(teacherPartner);
                teacherPartner.setPartnerEntity(null);
                teacherPartner.setTeacherEntity(null);

                break;
            }

        }

    }

    @Override
    public String toString() {
        return "PartnerEntity{" +
                "id=" + id +
                ", businessName='" + businessName + '\'' +
                ", vatNumber='" + vatNumber + '\'' +
                ", company='" + company + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", managerName='" + managerName + '\'' +
                ", managerNumber='" + managerNumber + '\'' +
                ", accreditedFt='" + accreditedFt + '\'' +
                ", accreditedFtCode='" + accreditedFtCode + '\'' +
                ", costElement='" + costElement + '\'' +
                ", note='" + note + '\'' +
                ", teacherPartnerList=" + teacherPartnerList +
                ", addressPartnerList=" + addressPartnerList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartnerEntity that = (PartnerEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(businessName, that.businessName) &&
                Objects.equals(vatNumber, that.vatNumber) &&
                Objects.equals(company, that.company) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(fax, that.fax) &&
                Objects.equals(managerName, that.managerName) &&
                Objects.equals(managerNumber, that.managerNumber) &&
                Objects.equals(accreditedFt, that.accreditedFt) &&
                Objects.equals(accreditedFtCode, that.accreditedFtCode) &&
                Objects.equals(costElement, that.costElement) &&
                Objects.equals(note, that.note) &&
                Objects.equals(teacherPartnerList, that.teacherPartnerList) &&
                Objects.equals(addressPartnerList, that.addressPartnerList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, businessName, vatNumber, company, email, phone, fax, managerName, managerNumber, accreditedFt, accreditedFtCode, costElement, note, teacherPartnerList, addressPartnerList);
    }
}
