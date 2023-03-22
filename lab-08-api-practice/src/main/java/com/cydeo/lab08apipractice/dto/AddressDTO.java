package com.cydeo.lab08apipractice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO{
    private Long id;
    private String name;
    private String zipCode;
    private String street;
    private Long customerId;
}
