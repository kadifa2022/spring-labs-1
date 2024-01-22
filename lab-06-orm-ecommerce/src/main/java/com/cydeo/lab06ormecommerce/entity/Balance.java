package com.cydeo.lab06ormecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToOne;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Balance extends BaseEntity{

    private double amount;
    @OneToOne
    private Customer customer;
}
