package com.cydeo.lab07ormqueries.Entity;


import com.cydeo.lab07ormqueries.enums.DiscountType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Discount extends  BaseEntity{

    private String name;
    private BigDecimal discount;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;


}
