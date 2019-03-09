package it.tcgroup.vilear.coursemanager.controller.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.controller.payload.request.TeacherRequestV1;
import it.tcgroup.vilear.coursemanager.entity.enumerated.StatusTeacherPartnerEnum;
import it.tcgroup.vilear.coursemanager.entity.enumerated.TypeAddressPartnerEnum;
import it.tcgroup.vilear.coursemanager.entity.jsonb.partner.AddressPartner;
import it.tcgroup.vilear.coursemanager.entity.jsonb.partner.TeacherPartner;
import java.util.List;
import java.util.UUID;

public class PartnerResponseV1 {

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
    private List<TeacherPartnerResponseV1> teacherList;

    @JsonProperty( "address_list")
    private List<AddressPartnerResponseV1> addressList;

    public static class TeacherPartnerResponseV1{

        @JsonProperty("teacher")
        private TeacherResponseV1 teacher;

        @JsonProperty("status")
        private StatusTeacherPartnerEnum status;

        public TeacherPartnerResponseV1() {
        }

        public TeacherPartnerResponseV1(TeacherResponseV1 teacher, StatusTeacherPartnerEnum status) {
            this.teacher = teacher;
            this.status = status;
        }

        public TeacherResponseV1 getTeacher() {
            return teacher;
        }

        public void setTeacher(TeacherResponseV1 teacher) {
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

    public static class AddressPartnerResponseV1{

        @JsonProperty("address")
        private TeacherResponseV1.AddressResponse address;

        @JsonProperty("type")
        private TypeAddressPartnerEnum type;

        public AddressPartnerResponseV1() {
        }

        public AddressPartnerResponseV1(TeacherResponseV1.AddressResponse address, TypeAddressPartnerEnum type) {
            this.address = address;
            this.type = type;
        }

        public TeacherResponseV1.AddressResponse getAddress() {
            return address;
        }

        public void setAddress(TeacherResponseV1.AddressResponse address) {
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

    public PartnerResponseV1() {
    }

    public PartnerResponseV1(String id, String businessName, String vatNumber, String company, String email, String phone, String fax, String managerName, String managerNumber, Boolean accreditedFt, String accreditedFtCode, String costElement, String note, List<TeacherPartnerResponseV1> teacherList, List<AddressPartnerResponseV1> addressList) {
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

    public List<TeacherPartnerResponseV1> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<TeacherPartnerResponseV1> teacherList) {
        this.teacherList = teacherList;
    }

    public List<AddressPartnerResponseV1> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressPartnerResponseV1> addressList) {
        this.addressList = addressList;
    }

    @Override
    public String toString() {
        return "PartnerResponseV1{" +
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
