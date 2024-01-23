package com.cydeo.lab06ormecommerce.entity;

import com.cydeo.lab06ormecommerce.enums.PaymentMethod;
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
public class Payment extends BaseEntity{

    private BigDecimal paidPrice;

    @Enumerated(EnumType.STRING)// in order not to save in DB ORDINAL we're providing String
    private PaymentMethod paymentMethod;
}
