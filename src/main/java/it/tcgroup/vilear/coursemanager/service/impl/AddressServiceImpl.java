package it.tcgroup.vilear.coursemanager.service.impl;

import it.tcgroup.vilear.coursemanager.adapter.AddressAdapter;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Address;
import it.tcgroup.vilear.coursemanager.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressAdapter addressAdapter;

    @Override
    public Address patchAddress(Address addressToBePatch ,Address addressPatch){

        if(addressPatch.getCity() != null)
            addressToBePatch.setCity(addressPatch.getCity());

        if(addressPatch.getNation() != null)
            addressToBePatch.setNation(addressPatch.getNation());

        if(addressPatch.getNumber() != null)
            addressToBePatch.setNumber(addressPatch.getNumber());

        if(addressPatch.getProvince() != null)
            addressToBePatch.setProvince(addressPatch.getProvince());

        if(addressPatch.getRegion() != null)
            addressToBePatch.setRegion(addressPatch.getRegion());

        if(addressPatch.getStreet() != null)
            addressToBePatch.setStreet(addressPatch.getStreet());

        if(addressPatch.getZipCode() != null)
            addressToBePatch.setZipCode(addressPatch.getZipCode());

        addressToBePatch.setFormattedAddress(addressAdapter.formatAddress(addressToBePatch));

        return addressToBePatch;
    }

}
