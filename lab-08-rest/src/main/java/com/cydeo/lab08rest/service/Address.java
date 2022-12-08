package com.cydeo.lab08rest.service;

import com.cydeo.lab08rest.dto.AddressDTO;

public interface Address {

    AddressDTO getAddressList();
    AddressDTO createAddress();
    AddressDTO updateAddress();
    AddressDTO deleteAddress();
    AddressDTO getAddressByCustomerId();
    AddressDTO getAddressListByStartsWithAddress();
    AddressDTO getAddressListByCustomerAndName();

}
