package it.tcgroup.vilear.coursemanager.entity.jsonb.course;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.PartnerEntity;
import it.tcgroup.vilear.coursemanager.entity.jsonb.partner.AddressPartner;
import it.tcgroup.vilear.coursemanager.entity.jsonb.partner.TeacherPartner;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties({"hibernateLazyInitializer", "hibernate_lazy_initializer", "handler"})
public class ActuatorSubjectCourse implements Serializable {

    private static final long serialVersionUID = -1400425835635903570L;

    @JsonProperty("business_name")
    private String businessName;

    @JsonProperty("vat_number")
    private String vatNumber;

    @JsonProperty( "company")
    private String company;

    @JsonProperty( "email")
    private String email;

    @JsonProperty( "phone")
    private String phone;

    @JsonProperty( "fax")
    private String fax;

    @JsonProperty( "manager_name")
    private String managerName;

    @JsonProperty( "manager_number")
    private String managerNumber;

    @JsonProperty( "accredited_ft")
    private Boolean accreditedFt;

    @JsonProperty( "accredited_ft_code")
    private String accreditedFtCode;

    @JsonProperty( "cost_element")
    private String costElement;

    @JsonProperty( "note")
    private String note;

    @JsonProperty( "teacher_list")
    private List<TeacherPartner> teacherList = new LinkedList<>();

    @JsonProperty( "address")
    private List<AddressPartner> addressList = new LinkedList<>();

    public ActuatorSubjectCourse() {
    }

    public ActuatorSubjectCourse( String businessName, String vatNumber, String company, String email, String phone, String fax, String managerName, String managerNumber, Boolean accreditedFt, String accreditedFtCode, String costElement, String note, List<TeacherPartner> teacherList, List<AddressPartner> addressList) {
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
        ActuatorSubjectCourse that = (ActuatorSubjectCourse) o;
        return  Objects.equals(businessName, that.businessName) &&
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
        return Objects.hash( businessName, vatNumber, company, email, phone, fax, managerName, managerNumber, accreditedFt, accreditedFtCode, costElement, note, teacherList, addressList);
    }

}
