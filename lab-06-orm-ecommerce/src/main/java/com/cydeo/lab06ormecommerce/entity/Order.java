package com.cydeo.lab06ormecommerce.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "orders") // we use orders table name because order is reserved word
public class Order extends BaseEntity {

    private BigDecimal paidPrice;
    private BigDecimal totalPrice;

    @OneToOne()
    private Cart cart;//fk

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;//fk

    @OneToOne
    private Payment payment;//fk
}
