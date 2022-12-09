package com.cydeo.lab08rest.service;

import com.cydeo.lab08rest.dto.OrderDTO;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.List;

public interface OrderService {

    List<OrderDTO> retrieveListOrder();

    OrderDTO updateOrder(OrderDTO orderDTO);
}
