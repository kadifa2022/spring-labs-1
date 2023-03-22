package com.cydeo.lab08apipractice.service;

import com.cydeo.lab08apipractice.dto.AddressDTO;

import java.util.List;

public interface AddressService {


    List<AddressDTO> readAll();

   AddressDTO update(AddressDTO addressDTO);

    AddressDTO create(AddressDTO addressDTO);

   List<AddressDTO>readByStartsWith(String address);

    List<AddressDTO> readAllByCustomerId(Long id);



    List<AddressDTO> readAllByCustomerIdAndName(Long customerId, String name);
}

