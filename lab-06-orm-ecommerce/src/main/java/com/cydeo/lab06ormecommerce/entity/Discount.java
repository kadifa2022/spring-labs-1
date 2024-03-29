package com.cydeo.lab06ormecommerce.entity;

import com.cydeo.lab06ormecommerce.enums.DiscountType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Discount extends BaseEntity{

    private String name;

    private BigDecimal discount;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType; // to sava in DB



}
