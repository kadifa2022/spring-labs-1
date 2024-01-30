package com.cydeo.lab8restecommerce.service.impl;

import com.cydeo.lab8restecommerce.dto.OrderDTO;
import com.cydeo.lab8restecommerce.dto.UpdateOrderDTO;
import com.cydeo.lab8restecommerce.entity.Order;
import com.cydeo.lab8restecommerce.exception.NotFoundException;
import com.cydeo.lab8restecommerce.mapper.MapperUtil;
import com.cydeo.lab8restecommerce.repository.OrderRepository;
import com.cydeo.lab8restecommerce.service.CartService;
import com.cydeo.lab8restecommerce.service.CustomerService;
import com.cydeo.lab8restecommerce.service.OrderService;
import com.cydeo.lab8restecommerce.service.PaymentService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final MapperUtil mapperUtil;
    private final CartService cartService;
    private final PaymentService paymentService;
    private final CustomerService customerService;

    public OrderServiceImpl(OrderRepository orderRepository, MapperUtil mapperUtil, CartService cartService, PaymentService paymentService, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.mapperUtil = mapperUtil;
        this.cartService = cartService;
        this.paymentService = paymentService;
        this.customerService = customerService;
    }


    @Override
    public List<OrderDTO> retrieveOrderList() {
        return orderRepository.findAll().stream().map(order -> mapperUtil.convert(order, new OrderDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        //find order from DB before anything else If is available, if not throw exception
        orderRepository.findById(orderDTO.getId()).orElseThrow(
                () -> new NotFoundException("Order could not be found."));
        //then we need to check if the order fields exists or not
        validateRelatedFieldsAreExists(orderDTO); //private method
        return null;
    }

    @Override
    public OrderDTO updateOrderById(Long id, UpdateOrderDTO updateOrderDTO) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Order could not be found."));
        //if we are getting same value, it is not necessary to update the actual value
        boolean changeDetected = false;  //  by default is false in the beginning
        if (!order.getPaidPrice().equals(updateOrderDTO.getPaidPrice())){ // comparing if there are equal there is no change
            // if there recognize change i will set new price to order
            order.setPaidPrice(updateOrderDTO.getPaidPrice());
        changeDetected = true;
        }
        if(!order.getTotalPrice().equals(updateOrderDTO.getTotalPrice())){
            order.setTotalPrice(updateOrderDTO.getTotalPrice());
            changeDetected = true;
        }
        // if there is any change, update the order and return it
        if(changeDetected){
            Order updateOrder = orderRepository.save(order);
            return mapperUtil.convert(order, new OrderDTO());
        }else{
            throw new NotFoundException("No changes detected");

        }


    }

    @Override
    public OrderDTO retrieveOrderDetailById(Long id) {
        //find the order based on id, converted and return it
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Order could not be found."));

        // convert and return
        return mapperUtil.convert(order, new OrderDTO()) ;
    }

    private void validateRelatedFieldsAreExists(OrderDTO orderDTO) {
        //in this method we have 3 different services and make sure they have those fields
        //we will create service and existById method and verify
        if (!customerService.existById(orderDTO.getCustomerId())) { // reversing  logic
            throw new NotFoundException("Customer could not found");
        }
        if (!paymentService.existById(orderDTO.getPaymentId())) { // reversing  logic
            throw new NotFoundException("Payment could not found");
        }
        if (!cartService.existById(orderDTO.getCartId())) { // reversing  logic
            throw new NotFoundException("Order could not found");
        }
    }
}