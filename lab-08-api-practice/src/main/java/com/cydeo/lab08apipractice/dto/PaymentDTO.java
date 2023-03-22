package com.cydeo.lab08apipractice.dto;

import com.cydeo.lab08apipractice.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDTO {
    private BigDecimal paidPrice;
    private PaymentMethod paymentMethod;
}
