package com.cydeo.lab08rest.service.serviceImp;

import com.cydeo.lab08rest.dto.CustomerDTO;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.CustomerRepository;
import com.cydeo.lab08rest.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final MapperUtil mapperUtil;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(MapperUtil mapperUtil, CustomerRepository customerRepository) {
        this.mapperUtil = mapperUtil;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO findById(Long customerId) {
        return customerRepository.findById(customerId).stream()
                .map(customer->mapperUtil.convert(customer, new CustomerDTO())).findFirst().orElseThrow();
    }
}
