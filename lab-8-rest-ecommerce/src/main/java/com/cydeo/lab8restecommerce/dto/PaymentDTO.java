package com.cydeo.lab8restecommerce.dto;


import com.cydeo.lab8restecommerce.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDTO {
    private Long id;
    private BigDecimal paidPrice;
    private PaymentMethod paymentMethod;
}
