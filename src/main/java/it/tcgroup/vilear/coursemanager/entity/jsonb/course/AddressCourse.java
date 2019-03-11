package it.tcgroup.vilear.coursemanager.entity.jsonb.course;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.dto.AddressDto;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressCourse implements Serializable {

    @JsonProperty("address")
    private AddressDto address;

    @JsonProperty("main")
    private Boolean main;

    public AddressCourse() {
    }

    public AddressCourse(AddressDto address, Boolean main) {
        this.address = address;
        this.main = main;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
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
