package com.cydeo.lab08apipractice.controller;

import com.cydeo.lab08apipractice.service.CustomerService;
import com.cydeo.lab08apipractice.dto.CustomerDTO;

import com.cydeo.lab08apipractice.model.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }




    @GetMapping
    public ResponseEntity<ResponseWrapper> listAllCustomers(){
        return ResponseEntity.ok(new ResponseWrapper("Costumers are retrieved "
        , customerService.readAll(), HttpStatus.OK));

    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateCostumer(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(new ResponseWrapper("Customer is updated. "
                , customerService.update(customerDTO), HttpStatus.OK ));
    }
    @PostMapping
    public ResponseEntity<ResponseWrapper> createCustomer(@RequestBody CustomerDTO customerDTO){
    return ResponseEntity.ok(new ResponseWrapper("Customer is created"
    , customerService.create(customerDTO), HttpStatus.OK));
    }

    @GetMapping("/{email}")
    public ResponseEntity<ResponseWrapper> getCustomerByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(new ResponseWrapper("Customer is retrieved. "
        , customerService.readByEmail(email), HttpStatus.OK));
    }



}