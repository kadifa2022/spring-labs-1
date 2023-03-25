package com.cydeo.lab08apipractice.service;

import com.cydeo.lab08apipractice.dto.DiscountDTO;

import java.util.List;


public interface DiscountService {


    List<DiscountDTO> readAll();
    DiscountDTO update(DiscountDTO discountDTO);
    DiscountDTO create(DiscountDTO discountDTO);
    DiscountDTO readByName(String name);




}
