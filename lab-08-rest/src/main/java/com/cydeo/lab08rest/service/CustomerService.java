package com.cydeo.lab08rest.service;

import com.cydeo.lab08rest.dto.CustomerDTO;

public interface CustomerService {
   CustomerDTO findById(Long customerId);
}
