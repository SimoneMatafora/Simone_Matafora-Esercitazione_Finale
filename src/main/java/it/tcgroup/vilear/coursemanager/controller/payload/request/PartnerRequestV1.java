package it.tcgroup.vilear.coursemanager.controller.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.enumerated.StatusTeacherPartnerEnum;
import it.tcgroup.vilear.coursemanager.entity.enumerated.TypeAddressPartnerEnum;

import java.util.List;

public class PartnerRequestV1 {

    @JsonProperty("id")
    private String id;

    @JsonProperty( "business_name")
    private String businessName;

    @JsonProperty( "vat_number")
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
    private List<TeacherPartnerRequestV1> teacherList;

    @JsonProperty( "address_list")
    private List<AddressPartnerRequestV1> addressList;

    public static class TeacherPartnerRequestV1{

        @JsonProperty("teacher")
        private TeacherRequestV1 teacher;

        @JsonProperty("status")
        private StatusTeacherPartnerEnum status;

        public TeacherPartnerRequestV1() {
        }

        public TeacherPartnerRequestV1(TeacherRequestV1 teacher, StatusTeacherPartnerEnum status) {
            this.teacher = teacher;
            this.status = status;
        }

        public TeacherRequestV1 getTeacher() {
            return teacher;
        }

        public void setTeacher(TeacherRequestV1 teacher) {
            this.teacher = teacher;
        }

        public StatusTeacherPartnerEnum getStatus() {
            return status;
        }

        public void setStatus(StatusTeacherPartnerEnum status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "TeacerPartnerRequestV1{" +
                    "teacher=" + teacher +
                    ", status=" + status +
                    '}';
        }
    }

    public static class AddressPartnerRequestV1{

        @JsonProperty("address")
        private TeacherRequestV1.AddressRequest address;

        @JsonProperty("type")
        private TypeAddressPartnerEnum type;

        public AddressPartnerRequestV1() {
        }

        public AddressPartnerRequestV1(TeacherRequestV1.AddressRequest address, TypeAddressPartnerEnum type) {
            this.address = address;
            this.type = type;
        }

        public TeacherRequestV1.AddressRequest getAddress() {
            return address;
        }

        public void setAddress(TeacherRequestV1.AddressRequest address) {
            this.address = address;
        }

        public TypeAddressPartnerEnum getType() {
            return type;
        }

        public void setType(TypeAddressPartnerEnum type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "AddressPartnerRequestV1{" +
                    "address=" + address +
                    ", type=" + type +
                    '}';
        }
    }

    public PartnerRequestV1() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<TeacherPartnerRequestV1> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<TeacherPartnerRequestV1> teacherList) {
        this.teacherList = teacherList;
    }

    public List<AddressPartnerRequestV1> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressPartnerRequestV1> addressList) {
        this.addressList = addressList;
    }

    @Override
    public String toString() {
        return "PartnerRequestV1{" +
                "id='" + id + '\'' +
                ", businessName='" + businessName + '\'' +
                ", vatNumber='" + vatNumber + '\'' +
                ", company='" + company + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", managerName='" + managerName + '\'' +
                ", managerNumber='" + managerNumber + '\'' +
                ", accreditedFt=" + accreditedFt +
                ", accreditedFtCode='" + accreditedFtCode + '\'' +
                ", costElement='" + costElement + '\'' +
                ", note='" + note + '\'' +
                ", teacherList=" + teacherList +
                ", addressList=" + addressList +
                '}';
    }
}
