package it.tcgroup.vilear.coursemanager.entity.jsonb.course;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.AddressEntity;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressCourse implements Serializable {

    @JsonProperty("address")
    private AddressEntity address;

    @JsonProperty("main")
    private Boolean main;

    public AddressCourse() {
    }

    public AddressCourse(AddressEntity address, Boolean main) {
        this.address = address;
        this.main = main;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return "AddressCourse{" +
                "address=" + address +
                ", main=" + main +
                '}';
    }
}
