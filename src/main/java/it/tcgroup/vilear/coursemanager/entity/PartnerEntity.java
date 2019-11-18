package it.tcgroup.vilear.coursemanager.entity;

import it.tcgroup.vilear.coursemanager.entity.jsonb.dataType.JsonDataAddressPartnerType;
import it.tcgroup.vilear.coursemanager.entity.jsonb.partner.AddressPartner;
import it.tcgroup.vilear.coursemanager.entity.jsonb.partner.TeacherPartner;
import it.tcgroup.vilear.coursemanager.entity.jsonb.dataType.JsonDataTeacherPartnerType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "partner")
@TypeDefs({
        @TypeDef(name = "JsonDataTeacherPartnerType", typeClass = JsonDataTeacherPartnerType.class),
        @TypeDef(name = "JsonDataAddressPartnerType", typeClass = JsonDataAddressPartnerType.class),
})
public class PartnerEntity implements Serializable {

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
    private Boolean company;

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
    private Boolean accreditedFt;

    @Column(name = "accredited_ft_code")
    private String accreditedFtCode;

    @Column(name = "cost_element")
    private Double costElement;

    @Column(name = "note")
    private String note;

    @Type(type = "JsonDataTeacherPartnerType")
    @Column(name = "teacher_list")
    private List<TeacherPartner> teacherList = new LinkedList<>();

    @Type(type = "JsonDataAddressPartnerType")
    @Column(name = "address")
    private List<AddressPartner> addressList = new LinkedList<>();

    public PartnerEntity() {
    }

    public PartnerEntity(UUID id, String businessName, String vatNumber, Boolean company, String email, String phone, String fax, String managerName, String managerNumber, Boolean accreditedFt, String accreditedFtCode, Double costElement, String note, List<TeacherPartner> teacherList, List<AddressPartner> addressList) {
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
        this.teacherList = teacherList;
        this.addressList = addressList;
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

    public Boolean getCompany() {
        return company;
    }

    public void setCompany(Boolean company) {
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

    public Double getCostElement() {
        return costElement;
    }

    public void setCostElement(Double costElement) {
        this.costElement = costElement;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<TeacherPartner> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<TeacherPartner> teacherList) {
        this.teacherList = teacherList;
    }

    public List<AddressPartner> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressPartner> addressList) {
        this.addressList = addressList;
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
                ", teacherList=" + teacherList +
                ", addressList=" + addressList +
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
                Objects.equals(teacherList, that.teacherList) &&
                Objects.equals(addressList, that.addressList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, businessName, vatNumber, company, email, phone, fax, managerName, managerNumber, accreditedFt, accreditedFtCode, costElement, note, teacherList, addressList);
    }
}
