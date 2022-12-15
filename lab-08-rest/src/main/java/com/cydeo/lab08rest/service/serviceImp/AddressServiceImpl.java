package com.cydeo.lab08rest.service.serviceImp;


import com.cydeo.lab08rest.dto.AddressDTO;
import com.cydeo.lab08rest.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Override
    public List<AddressDTO> readAll() {
        return null;
    }

    @Override
    public AddressDTO update(AddressDTO addressDTO) {
        return null;
    }

    @Override
    public AddressDTO create(AddressDTO addressDTO) {
        return null;
    }

    @Override
    public List<AddressDTO> readByStartsWith(String address) {
        return null;
    }

    @Override
    public List<AddressDTO> readAllByCustomerId(Long id) {
        return null;
    }

    @Override
    public List<AddressDTO> readByCustomerIdAndName(Long customerId, String name) {
        return null;
    }
}
