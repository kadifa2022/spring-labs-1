package com.cydeo.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {//Primary key


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
