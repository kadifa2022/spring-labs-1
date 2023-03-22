package com.cydeo.lab08apipractice.dto;

import com.cydeo.lab08apipractice.enums.CartState;
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
