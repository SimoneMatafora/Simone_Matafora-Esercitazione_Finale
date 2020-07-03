package it.tcgroup.vilear.coursemanager.controller.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class AddressRequestV1{

    @JsonProperty("nation")
    private String nation;

    @JsonProperty("region")
    private String region;

    @NotNull
    @JsonProperty("province")
    private String province;

    @NotNull
    @JsonProperty("city")
    private String city;

    @JsonProperty("street")
    private  String street;

    @JsonProperty("number")
    private String number;

    @NotNull
    @JsonProperty("zip_code")
    private String zipCode;

    public AddressRequestV1() {
    }

    public AddressRequestV1(String nation, String region, String province, String city, String street, String number, String zipCode) {
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
