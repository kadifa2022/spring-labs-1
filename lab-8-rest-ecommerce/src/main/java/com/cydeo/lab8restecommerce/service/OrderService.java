package com.cydeo.lab8restecommerce.service;

import com.cydeo.lab8restecommerce.dto.OrderDTO;

import java.util.List;

public interface OrderService {


    List<OrderDTO> retrieveOrderList();
}
