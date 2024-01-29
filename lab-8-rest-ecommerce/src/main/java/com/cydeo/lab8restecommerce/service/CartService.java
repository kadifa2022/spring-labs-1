package com.cydeo.lab8restecommerce.service;

import com.cydeo.lab8restecommerce.repository.CartRepository;

public interface CartService {


    boolean existById(Long id);
}
