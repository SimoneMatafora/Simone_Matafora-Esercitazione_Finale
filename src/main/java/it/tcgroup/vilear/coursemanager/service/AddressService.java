package it.tcgroup.vilear.coursemanager.service;

import it.tcgroup.vilear.coursemanager.entity.jsonb.Address;

public interface AddressService {

    Address patchAddress(Address addressToBePatch , Address addressPatch);
}
