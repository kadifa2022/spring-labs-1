package com.cydeo.lab08rest.dto;

import com.cydeo.lab08rest.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDTO {

  //  private Long id; //this method I added watching Jamal
    private BigDecimal paidPrice;
    private PaymentMethod paymentMethod;
}
