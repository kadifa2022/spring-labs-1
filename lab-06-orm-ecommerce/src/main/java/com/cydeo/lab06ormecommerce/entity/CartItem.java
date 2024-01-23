package com.cydeo.lab06ormecommerce.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class CartItem extends BaseEntity{


    private Integer quantity;

    @ManyToOne
    private Cart cart; //FK

    @ManyToOne
    private Product product;//FK


}
