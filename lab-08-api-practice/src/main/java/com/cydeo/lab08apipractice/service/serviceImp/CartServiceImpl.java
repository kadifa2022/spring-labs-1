package com.cydeo.lab08apipractice.service.serviceImp;

import com.cydeo.lab08apipractice.repository.CartRepository;
import com.cydeo.lab08apipractice.dto.CartDTO;
import com.cydeo.lab08apipractice.mapper.MapperUtil;
import com.cydeo.lab08apipractice.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final MapperUtil mapperUtil;

    public CartServiceImpl(CartRepository cartRepository, MapperUtil mapperUtil) {
        this.cartRepository = cartRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public CartDTO findById(Long id) {
        return cartRepository.findById(id).stream()
                .map(cart ->mapperUtil.convert(cart, new CartDTO())).findFirst().orElseThrow();
    }
}
