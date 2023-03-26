package com.cydeo.lab08apipractice.service.serviceImp;

import com.cydeo.lab08apipractice.dto.DiscountDTO;
import com.cydeo.lab08apipractice.entity.Customer;
import com.cydeo.lab08apipractice.mapper.MapperUtil;
import com.cydeo.lab08apipractice.repository.CustomerRepository;
import com.cydeo.lab08apipractice.repository.DiscountRepository;
import com.cydeo.lab08apipractice.service.CustomerService;
import com.cydeo.lab08apipractice.dto.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
                .map(customer -> mapperUtil.convert(customer, new CustomerDTO()))
                .findFirst().orElseThrow();
    }

    @Override
    public List<CustomerDTO> readAll() {
        return customerRepository.findAll().stream()
                .map(customer -> mapperUtil.convert(customer, new CustomerDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        Customer customer = customerRepository.findByUserName(customerDTO.getUserName());
        customer.setEmail(customerDTO.getEmail());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        Customer savedCustomer= customerRepository.save(customer);
        return mapperUtil.convert(savedCustomer, new CustomerDTO());
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        Customer customer = customerRepository.save(mapperUtil.convert(customerDTO, new Customer()));
        return mapperUtil.convert(customer, new CustomerDTO());
    }

    @Override
    public CustomerDTO readByEmail(String email) {
        return mapperUtil.convert(customerRepository.retrieveByCustomerEmail(email), new CustomerDTO());
    }
}














