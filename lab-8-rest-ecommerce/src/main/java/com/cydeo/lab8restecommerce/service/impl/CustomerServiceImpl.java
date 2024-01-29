package com.cydeo.lab8restecommerce.service.impl;


import com.cydeo.lab8restecommerce.repository.CustomerRepository;
import com.cydeo.lab8restecommerce.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean existById(Long customerId) {
        return customerRepository.existsById(customerId);
    }
}
