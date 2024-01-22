package com.cydeo.lab06ormecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity{

    private String name;
    private String street;
    private String zipCode;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
}
