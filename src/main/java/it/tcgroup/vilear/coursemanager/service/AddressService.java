package it.tcgroup.vilear.coursemanager.service;

import it.tcgroup.vilear.coursemanager.controller.payload.request.TeacherRequestV1.*;
import it.tcgroup.vilear.coursemanager.entity.AddressEntity;
import it.tcgroup.vilear.coursemanager.entity.dto.AddressDto;

import java.util.UUID;

public interface AddressService {

    void insertAddress(AddressEntity address);
    AddressEntity updateAddress(AddressRequest addressRequest, UUID addressId);
    AddressEntity updateAddress(AddressEntity addressRequest, UUID addressId);
    String formatAddress(AddressEntity address);
    String formatAddress(AddressDto address);
    void deleteAddress(UUID addressId);

}
