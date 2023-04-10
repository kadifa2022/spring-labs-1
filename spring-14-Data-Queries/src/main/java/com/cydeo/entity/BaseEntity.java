package com.cydeo.entity;


import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass//no table just inheritance
public class BaseEntity {

    @Id
    private Integer id;
}
