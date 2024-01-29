package com.cydeo.lab8restecommerce.service.impl;

import com.cydeo.lab8restecommerce.repository.CartRepository;
import com.cydeo.lab8restecommerce.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public boolean existById(Long id) {
        return cartRepository.existsById(id);
    }
}
