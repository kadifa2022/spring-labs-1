package com.cydeo.lab8restecommerce.service.impl;

import com.cydeo.lab8restecommerce.dto.OrderDTO;
import com.cydeo.lab8restecommerce.mapper.MapperUtil;
import com.cydeo.lab8restecommerce.repository.OrderRepository;
import com.cydeo.lab8restecommerce.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;
    private final MapperUtil mapperUtil;

    public OrderServiceImp(OrderRepository orderRepository, MapperUtil mapperUtil) {
        this.orderRepository = orderRepository;
        this.mapperUtil = mapperUtil;
    }


    @Override
    public List<OrderDTO> retrieveOrderList() {
        return orderRepository.findAll().stream().map(order -> mapperUtil.convert(order, new OrderDTO()))
                .collect(Collectors.toList());
    }
}
