package com.cydeo.lab08rest.controller;

import com.cydeo.lab08rest.dto.OrderDTO;
import com.cydeo.lab08rest.dto.UpdateOrderDTO;
import com.cydeo.lab08rest.enums.PaymentMethod;
import com.cydeo.lab08rest.model.ResponseWrapper;
import com.cydeo.lab08rest.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> retrieveListOrder(){
        return ResponseEntity.ok(new ResponseWrapper("Orders are successfully retrieved"
        , orderService.retrieveListOrder(), HttpStatus.OK));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateOrder(@Valid @RequestBody OrderDTO orderDTO ){
        return ResponseEntity.ok(new ResponseWrapper("Order are successfully updated"
                , orderService.updateOrder(orderDTO),HttpStatus.OK));
    }

    @PutMapping("/{id}")//Jamal
    public ResponseEntity<ResponseWrapper> updateOrderById(@PathVariable ("id") Long id,@Valid @RequestBody UpdateOrderDTO updateOrderDTO ){
        return ResponseEntity.ok(new ResponseWrapper("Order are successfully updated"
                , orderService.updateOrderById(id,updateOrderDTO),HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper> getOrderById(@PathVariable("id") Long id,@RequestParam(required = false) Optional<String> currency){
        return ResponseEntity.ok(new ResponseWrapper("Order is successfully retrieved.",
                orderService.retrieveOrderById(id, currency), HttpStatus.OK));
    }


    @PostMapping
    public ResponseEntity<ResponseWrapper> createOrder(@RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(new ResponseWrapper("Order are successfully created"
        , orderService.createOrder(orderDTO),HttpStatus.OK));
    }
    @GetMapping("/paymentMethod/{paymentMethod}")
    public ResponseEntity<ResponseWrapper> retrieveListOrder(@PathVariable("paymentMethod") PaymentMethod paymentMethod){
        return ResponseEntity.ok(new ResponseWrapper("Orders are successfully retrieved"
        , orderService.retrieveOrderByPaymentMethod(paymentMethod), HttpStatus.OK));

    }
    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseWrapper> retrieveListOrder(@PathVariable("email") String email){
        return ResponseEntity.ok(new ResponseWrapper("Emails are successfully retrieved"
        , orderService.retrieveOrderByEmail(email), HttpStatus.OK));
    }


}
