package com.cydeo.lab8restecommerce.controller;

import com.cydeo.lab8restecommerce.model.ResponseWrapper;
import com.cydeo.lab8restecommerce.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public ResponseEntity<ResponseWrapper> retrieveOrderList(){
        return ResponseEntity.ok(new ResponseWrapper("Orders are successfully retrieved",
                orderService.retrieveOrderList(), HttpStatus.OK));
    }
}
