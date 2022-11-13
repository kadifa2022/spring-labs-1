package com.cydeo.lab07ormqueries.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends BaseEntity{


    private String firstName;
    private String lastName;
    private String userName;
    private String email;

}
