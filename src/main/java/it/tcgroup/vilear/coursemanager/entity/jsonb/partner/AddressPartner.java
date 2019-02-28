package it.tcgroup.vilear.coursemanager.entity.jsonb.partner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.AddressEntity;
import it.tcgroup.vilear.coursemanager.entity.enumerated.TypeAddressPartnerEnum;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressPartner  implements Serializable {

    @JsonProperty("address")
    private AddressEntity addressEntity;

    @JsonProperty("type")
    private TypeAddressPartnerEnum type;

    public AddressPartner() {
    }

    public AddressPartner(AddressEntity addressEntity, TypeAddressPartnerEnum type) {
        this.addressEntity = addressEntity;
        this.type = type;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
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
                "addressEntity=" + addressEntity +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressPartner that = (AddressPartner) o;
        return Objects.equals(addressEntity, that.addressEntity) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressEntity, type);
    }
}
