package it.tcgroup.vilear.coursemanager.service.impl;

import it.tcgroup.vilear.coursemanager.adapter.AddressAdapter;
import it.tcgroup.vilear.coursemanager.common.exception.NotFoundException;
import it.tcgroup.vilear.coursemanager.controller.payload.request.TeacherRequestV1.*;
import it.tcgroup.vilear.coursemanager.entity.AddressEntity;
import it.tcgroup.vilear.coursemanager.repository.AddressRepository;
import it.tcgroup.vilear.coursemanager.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository indirizzoRepository;

    @Autowired
    private AddressAdapter addressAdapter;

    @Override
    public void insertAddress(AddressEntity address){
        indirizzoRepository.save(address);
    }

    @Modifying
    @Override
    public AddressEntity updateAddress(AddressRequest addressRequest, UUID addressId){

        Optional<AddressEntity> optAddress = indirizzoRepository.findById(addressId);
        if(!optAddress.isPresent()){
            throw new NotFoundException("Address with id " + addressId + " not found");
        }

        AddressEntity address = optAddress.get();

        address.setNumber(addressRequest.getNumber());
        address.setNation(addressRequest.getNation());
        address.setStreet(addressRequest.getStreet());
        address.setRegion(addressRequest.getRegion());
        address.setCity(addressRequest.getCity());
        address.setProvince(addressRequest.getProvince());
        address.setZipCode(addressRequest.getZipCode());

        address.setFormattedAddress(this.formatAddress(address));

        indirizzoRepository.save(address);

        return address;
    }

    @Modifying
    @Override
    public AddressEntity updateAddress(AddressEntity addressRequest, UUID addressId){

        Optional<AddressEntity> optAddress = indirizzoRepository.findById(addressId);
        if(!optAddress.isPresent()){
            throw new NotFoundException("Indirizzo with id " + addressId + " not found");
        }

        AddressEntity address = optAddress.get();

        address.setNumber(addressRequest.getNumber());
        address.setNation(addressRequest.getNation());
        address.setStreet(addressRequest.getStreet());
        address.setRegion(addressRequest.getRegion());
        address.setCity(addressRequest.getCity());
        address.setProvince(addressRequest.getProvince());
        address.setZipCode(addressRequest.getZipCode());
        address.setFormattedAddress(this.formatAddress(addressRequest));

        indirizzoRepository.save(address);

        return address;
    }

    @Override
    public String formatAddress(AddressEntity address){

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

    @Override
    public void deleteAddress(UUID idIndirizzo){

        indirizzoRepository.deleteById(idIndirizzo);

    }

}
