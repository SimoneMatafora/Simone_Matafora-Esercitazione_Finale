package it.tcgroup.vilear.coursemanager.adapter;

import it.tcgroup.vilear.coursemanager.controller.payload.request.TeacherRequestV1.*;
import it.tcgroup.vilear.coursemanager.controller.payload.response.TeacherResponseV1.*;
import it.tcgroup.vilear.coursemanager.entity.AddressEntity;
import it.tcgroup.vilear.coursemanager.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressAdapter {

    @Autowired
    private AddressService addressService;

    public AddressEntity adptAddressRequestToAddress(AddressRequest addressRequest){

        AddressEntity address = new AddressEntity();

        address.setZipCode(addressRequest.getZipCode());
        address.setCity(addressRequest.getCity());
        address.setProvince(addressRequest.getProvince());
        address.setRegion(addressRequest.getRegion());
        address.setStreet(addressRequest.getStreet());
        address.setNation(addressRequest.getNation());
        address.setNumber(addressRequest.getNumber());

        address.setFormattedAddress(addressService.formatAddress(address));

        return address;
    }

    public AddressResponse adptAddressToAddressResponse(AddressEntity address){


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
