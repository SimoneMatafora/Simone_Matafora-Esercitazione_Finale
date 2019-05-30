package it.tcgroup.vilear.coursemanager.entity.jsonb.partner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.dto.AddressDto;
import it.tcgroup.vilear.coursemanager.entity.enumerated.TypeAddressPartnerEnum;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressPartner  implements Serializable {

    @JsonProperty("address")
    private AddressDto address;

    @JsonProperty("is_legal_address")
    private Boolean isLegalAddress;

    public AddressPartner() {
    }

    public AddressPartner(AddressDto address, Boolean isLegalAddress) {
        this.address = address;
        this.isLegalAddress = isLegalAddress;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public Boolean getLegalAddress() {
        return isLegalAddress;
    }

    public void setLegalAddress(Boolean legalAddress) {
        isLegalAddress = legalAddress;
    }

    @Override
    public String toString() {
        return "AddressPartner{" +
                "address=" + address +
                ", isLegalAddress=" + isLegalAddress +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressPartner)) return false;
        AddressPartner that = (AddressPartner) o;
        return Objects.equals(address, that.address) &&
                Objects.equals(isLegalAddress, that.isLegalAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, isLegalAddress);
    }
}
