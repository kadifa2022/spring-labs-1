package com.cydeo.lab06ormecommerce.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;


@Getter
@Setter
@NoArgsConstructor// NoArgsConstructor is needed > will Jpa structure will create default constructor
@Entity
//@Table(name="customers") // -> if we need to change table name
public class Customer extends BaseEntity{ // this is one part

    private String email;
    private String firstName;
    private String lastName;
    private String userName;
}
