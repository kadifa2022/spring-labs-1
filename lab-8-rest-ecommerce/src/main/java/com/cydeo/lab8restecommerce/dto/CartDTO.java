package com.cydeo.lab8restecommerce.dto;


import com.cydeo.lab8restecommerce.enums.CartState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {
    private Long id;
    private CustomerDTO customer;
    private DiscountDTO discount;
    private CartState cartState;

}
