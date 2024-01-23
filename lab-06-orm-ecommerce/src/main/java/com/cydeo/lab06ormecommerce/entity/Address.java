package com.cydeo.lab06ormecommerce.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Address extends BaseEntity{// address many part

    private String name;
    private String street;
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;// one part
}
