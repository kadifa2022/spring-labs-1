package com.cydeo.lab08rest.controller;

import com.cydeo.lab08rest.dto.AddressDTO;


import com.cydeo.lab08rest.model.ResponseWrapper;
import com.cydeo.lab08rest.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class AddressController {


    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @GetMapping
    public ResponseEntity<ResponseWrapper> listAllAddresses() {
        return ResponseEntity.ok(new ResponseWrapper("Addresses are retrieved"
                , addressService.readAll(), HttpStatus.OK));


    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateAddress(@RequestBody AddressDTO addressDTO) {
        return ResponseEntity.ok(new ResponseWrapper("Address is updated. "
                , addressService.update(addressDTO), HttpStatus.OK));

    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createAddress(@RequestBody AddressDTO addressDTO) {
        return ResponseEntity.ok(new ResponseWrapper("Address is created."
                , addressService.create(addressDTO), HttpStatus.OK));

    }

    @GetMapping("/startsWith/{address}")
    public ResponseEntity<ResponseWrapper> getAddressByStartsWith(@PathVariable("address") String address) {
        return ResponseEntity.ok(new ResponseWrapper("Addresses are retrieved.",
                addressService.readByStartsWith(address), HttpStatus.OK));


    }
    @GetMapping("/customer/{id}")
    public ResponseEntity<ResponseWrapper> getAddressByCustomerId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new ResponseWrapper("Addresses are retrieved.",
                addressService.readAllByCustomerId(id), HttpStatus.OK));

    }
    @GetMapping("/customer/{cusomerId}/name/{name}")
    public ResponseEntity<ResponseWrapper> getAddressByCustomerIdAndName(@PathVariable("customerId") Long customerId,
                                                             @PathVariable("name") String name ) {
        return ResponseEntity.ok(new ResponseWrapper("Addresses are retrieved.",
                addressService.readByCustomerIdAndName(customerId, name), HttpStatus.OK));
    }
}