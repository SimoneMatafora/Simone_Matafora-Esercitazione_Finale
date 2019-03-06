package it.tcgroup.vilear.coursemanager.entity.jsonb.partner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.AddressEntity;
import it.tcgroup.vilear.coursemanager.entity.dto.AddressDto;
import it.tcgroup.vilear.coursemanager.entity.enumerated.TypeAddressPartnerEnum;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressPartner  implements Serializable {

    @JsonProperty("address")
    private AddressDto address;

    @JsonProperty("type")
    private TypeAddressPartnerEnum type;

    public AddressPartner() {
    }

    public AddressPartner(AddressDto address, TypeAddressPartnerEnum type) {
        this.address = address;
        this.type = type;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
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
        return "AddressPartner{" +
                "address=" + address +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressPartner that = (AddressPartner) o;
        return Objects.equals(address, that.address) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, type);
    }
}
