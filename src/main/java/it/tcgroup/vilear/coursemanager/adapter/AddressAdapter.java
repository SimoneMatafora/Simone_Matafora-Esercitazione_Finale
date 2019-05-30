package it.tcgroup.vilear.coursemanager.adapter;

import it.tcgroup.vilear.coursemanager.controller.payload.request.TeacherRequestV1.*;
import it.tcgroup.vilear.coursemanager.controller.payload.response.TeacherResponseV1.*;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Address;
import it.tcgroup.vilear.coursemanager.entity.dto.AddressDto;
import org.springframework.stereotype.Component;

@Component
public class AddressAdapter {

    public String formatAddress(Address address){

        if(address == null)
            return null;

        String formattedAddress = "";
        if(address.getStreet() != null){ formattedAddress += address.getStreet(); }
        if(address.getNumber() != null){ formattedAddress += " " + address.getNumber(); }
        if(address.getCity() != null){ formattedAddress += ", " + address.getCity(); }
        if(address.getProvince() != null){ formattedAddress += ", " + address.getProvince(); }
        if(address.getZipCode() != null){ formattedAddress += " " + address.getZipCode(); }
        if(address.getRegion() != null){ formattedAddress += ", " + address.getRegion(); }
        if(address.getNation() != null){ formattedAddress += ", " + address.getNation(); }

        return formattedAddress;
    }

    public String formatAddress(AddressDto address){

        if(address == null)
            return null;

        String formattedAddress = "";
        if(address.getStreet() != null){ formattedAddress += address.getStreet(); }
        if(address.getNumber() != null){ formattedAddress += " " + address.getNumber(); }
        if(address.getCity() != null){ formattedAddress += ", " + address.getCity(); }
        if(address.getProvince() != null){ formattedAddress += ", " + address.getProvince(); }
        if(address.getZipCode() != null){ formattedAddress += " " + address.getZipCode(); }
        if(address.getRegion() != null){ formattedAddress += ", " + address.getRegion(); }
        if(address.getNation() != null){ formattedAddress += ", " + address.getNation(); }

        return formattedAddress;
    }

    public Address adptAddressRequestToAddress(AddressRequest addressRequest){

        if(addressRequest == null)
            return null;

        Address address = new Address();

        address.setZipCode(addressRequest.getZipCode());
        address.setCity(addressRequest.getCity());
        address.setProvince(addressRequest.getProvince());
        address.setRegion(addressRequest.getRegion());
        address.setStreet(addressRequest.getStreet());
        address.setNation(addressRequest.getNation());
        address.setNumber(addressRequest.getNumber());

        address.setFormattedAddress(this.formatAddress(address));

        return address;
    }

    public AddressResponse adptAddressToAddressResponse(Address address){

        if(address == null)
            return null;

        AddressResponse indirizzoResponse = new AddressResponse();

        indirizzoResponse.setZipCode(address.getZipCode());
        indirizzoResponse.setCity(address.getCity());
        indirizzoResponse.setProvince(address.getProvince());
        indirizzoResponse.setRegion(address.getRegion());
        indirizzoResponse.setStreet(address.getStreet());
        indirizzoResponse.setNation(address.getNation());
        indirizzoResponse.setNumber(address.getNumber());
        indirizzoResponse.setFormattedAddress(address.getFormattedAddress());

        return indirizzoResponse;
    }

    public AddressDto adptAddressRequestToAddressDto(AddressRequest addressRequest) {

        if(addressRequest == null)
            return null;

        AddressDto address = new AddressDto();

        address.setZipCode(addressRequest.getZipCode());
        address.setCity(addressRequest.getCity());
        address.setProvince(addressRequest.getProvince());
        address.setRegion(addressRequest.getRegion());
        address.setStreet(addressRequest.getStreet());
        address.setNation(addressRequest.getNation());
        address.setNumber(addressRequest.getNumber());

        address.setFormattedAddress(this.formatAddress(address));

        return address;
    }

    public AddressDto adptAddressToAddressDto(Address address) {

        if(address == null)
            return null;

        AddressDto addressDto = new AddressDto();

        addressDto.setZipCode(address.getZipCode());
        addressDto.setCity(address.getCity());
        addressDto.setProvince(address.getProvince());
        addressDto.setRegion(address.getRegion());
        addressDto.setStreet(address.getStreet());
        addressDto.setNation(address.getNation());
        addressDto.setNumber(address.getNumber());

        addressDto.setFormattedAddress(this.formatAddress(address));

        return addressDto;
    }

    public AddressResponse adptAddressDtoToAddressResponse(AddressDto address){

        if(address == null)
            return null;

        AddressResponse indirizzoResponse = new AddressResponse();

        indirizzoResponse.setZipCode(address.getZipCode());
        indirizzoResponse.setCity(address.getCity());
        indirizzoResponse.setProvince(address.getProvince());
        indirizzoResponse.setRegion(address.getRegion());
        indirizzoResponse.setStreet(address.getStreet());
        indirizzoResponse.setNation(address.getNation());
        indirizzoResponse.setNumber(address.getNumber());
        indirizzoResponse.setFormattedAddress(address.getFormattedAddress());

        return indirizzoResponse;
    }

}
