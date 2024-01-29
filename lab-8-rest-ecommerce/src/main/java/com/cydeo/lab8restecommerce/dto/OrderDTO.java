package com.cydeo.lab8restecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderDTO {

    @Min(1)
    @NotNull(message = "cartId can not be null")
    private Long cartId;

    @NotNull(message = "price can not be null")
    @Positive(message ="price shouldn't be negative")
    @DecimalMax(value = "100000", message = "price can not be grater than 1000000")
    @DecimalMin(value = "0.1", message ="price can not be less than 0.1")
    private BigDecimal paidPrice;

    @NotNull(message = "price can not be null")
    @Positive(message ="price shouldn't be negative")
    @DecimalMax(value = "100000", message = "price can not be grater than 1000000")
    @DecimalMin(value = "0.1", message ="price can not be less than 0.1")
    private BigDecimal totalPrice;
    @Min(1)
    @NotNull(message = "cartId can not be null")
    private Long customerId;

    @Min(1)
    @NotNull(message = "cartId can not be null")
    private Long paymentId;

    private Long id;
}

