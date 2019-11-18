package it.tcgroup.vilear.coursemanager.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class AddressDto implements Serializable {

    @JsonProperty( "nation")
    private String nation;

    @JsonProperty( "region")
    private String region;

    @JsonProperty( "province")
    private String province;

    @JsonProperty( "city")
    private String city;

    @JsonProperty( "street")
    private  String street;

    @JsonProperty( "number")
    private String number;

    @JsonProperty( "zip_code")
    private String zipCode;

    @JsonProperty( "formatted_address")
    private String formattedAddress;

    public AddressDto(String nation, String region, String province, String city, String street, String number, String zipCode, String formattedAddress) {
        this.nation = nation;
        this.region = region;
        this.province = province;
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.formattedAddress = formattedAddress;
    }

    public AddressDto() {
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

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    @Override
    public String toString() {
        return "AddressDto{" +
                "nation='" + nation + '\'' +
                ", region='" + region + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", formattedAddress='" + formattedAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDto that = (AddressDto) o;
        return Objects.equals(nation, that.nation) &&
                Objects.equals(region, that.region) &&
                Objects.equals(province, that.province) &&
                Objects.equals(city, that.city) &&
                Objects.equals(street, that.street) &&
                Objects.equals(number, that.number) &&
                Objects.equals(zipCode, that.zipCode) &&
                Objects.equals(formattedAddress, that.formattedAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nation, region, province, city, street, number, zipCode, formattedAddress);
    }
}
