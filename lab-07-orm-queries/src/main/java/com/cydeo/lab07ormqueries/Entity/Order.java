package com.cydeo.lab07ormqueries.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="orders")//changed because keyword reserved by sql
public class Order extends BaseEntity{

    private BigDecimal paidPrice;
    private BigDecimal totalPrice;
    @ManyToOne
    private Customer customer;
    @OneToOne
    private Cart cart;
    @OneToOne
    private Payment payment;
}
