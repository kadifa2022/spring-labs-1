package com.cydeo.lab06ormecommerce.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Id // I want my DB to manage my primary key Auto increment /decrement
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto-generating PK
    private Long id;


}
