package com.cydeo.lab08apipractice.service;

import com.cydeo.lab08apipractice.dto.OrderDTO;
import com.cydeo.lab08apipractice.enums.PaymentMethod;


import java.util.List;

public interface OrderService {
    List<OrderDTO> retrieveListOrder();

    OrderDTO updateOrder(OrderDTO orderDTO);

//    List<OrderDTO> retrieveListOrder();
//
//    OrderDTO updateOrder(OrderDTO orderDTO);
//
//    OrderDTO createOrder(OrderDTO orderDTO);
//
//    List<OrderDTO> retrieveOrderByPaymentMethod(PaymentMethod paymentMethod);
//
//    List<OrderDTO>retrieveOrderByEmail(String email);
}
