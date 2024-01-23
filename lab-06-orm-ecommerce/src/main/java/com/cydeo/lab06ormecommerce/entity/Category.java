package com.cydeo.lab06ormecommerce.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;


@NoArgsConstructor
@Entity
@Setter
@Getter
public class Category extends BaseEntity {

    private String name;

}
