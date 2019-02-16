package it.tcgroup.vilear.dummy.service;

import it.tcgroup.vilear.dummy.controller.payload.request.TeacherRequestV1.*;
import it.tcgroup.vilear.dummy.entity.AddressEntity;

import java.util.UUID;

public interface AddressService {

    void insertAddress(AddressEntity address);
    AddressEntity updateAddress(AddressRequest addressRequest, UUID addressId);
    AddressEntity updateAddress(AddressEntity addressRequest, UUID addressId);
    String formatAddress(AddressEntity address);
    void deleteAddress(UUID addressId);

}
