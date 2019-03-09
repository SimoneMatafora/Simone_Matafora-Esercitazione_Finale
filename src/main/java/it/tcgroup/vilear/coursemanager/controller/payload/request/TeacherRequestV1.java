package it.tcgroup.vilear.coursemanager.controller.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class TeacherRequestV1 {

    @JsonProperty("id")
    private String id;

    @JsonProperty("username")
    private String username;

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
    private  String birthPlace;

    @JsonProperty("phone")
    private  String phone;

    @JsonProperty("email")
    private String email;

    //DA CONSIDERARE COME EVENTUALE CLASSE ENUMERATA O RIFERIMENTO A TABELLA ESTERNA
    @JsonProperty("professional_area")
    private String professionalArea;

    @JsonProperty("public_employee")
    private Boolean publicEmployee;

    @JsonProperty("accredited_ft")
    private Boolean accreditedFt;

    @JsonProperty("accredited_ft_code")
    private String accreditedFtCode;

    @JsonProperty("authorized")
    private Boolean authorized;

    @JsonProperty("professional_order_registration")
    private Boolean professionalOrderRegistration;

    @JsonProperty("register")
    private String register;

    @JsonProperty("vat_holder")
    private Boolean vatHolder;

    @JsonProperty("vat_number")
    private String vatNumber;

    @JsonProperty("sector")
    private String sector;

    @JsonProperty("note")
    private String note;

    @JsonProperty("curriculum_vitae")
    private String curriculumVitae;

    @JsonProperty("address")
    private AddressRequest address;

    public TeacherRequestV1() {
    }

    public TeacherRequestV1(String id, String username, String name, String surname, String fiscalCode, Date dateOfBirth, String birthPlace, String phone, String email, String professionalArea, Boolean publicEmployee, Boolean accreditedFt, String accreditedFtCode, Boolean authorized, Boolean professionalOrderRegistration, String register, Boolean vatHolder, String vatNumber, String sector, String note, String curriculumVitae, AddressRequest address) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public AddressRequest getAddress() {
        return address;
    }

    public void setAddress(AddressRequest address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "TeacherRequestV1{" +
                "id='" + id + '\'' +
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

    public static class AddressRequest{

        @JsonProperty("nation")
        private String nation;

        @JsonProperty("region")
        private String region;

        @JsonProperty("province")
        private String province;

        @JsonProperty("city")
        private String city;

        @JsonProperty("street")
        private  String street;

        @JsonProperty("number")
        private String number;

        @JsonProperty("zip_code")
        private String zipCode;

        public AddressRequest() {
        }

        public AddressRequest(String nation, String region, String province, String city, String street, String number, String zipCode) {
            this.nation = nation;
            this.region = region;
            this.province = province;
            this.city = city;
            this.street = street;
            this.number = number;
            this.zipCode = zipCode;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        @Override
        public String toString() {
            return "AddressRequest{" +
                    "nation='" + nation + '\'' +
                    ", region='" + region + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", street='" + street + '\'' +
                    ", number='" + number + '\'' +
                    ", zipCode='" + zipCode + '\'' +
                    '}';
        }
    }

}
