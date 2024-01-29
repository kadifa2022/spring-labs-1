package com.cydeo.lab8restecommerce.controller;

import com.cydeo.lab8restecommerce.dto.OrderDTO;
import com.cydeo.lab8restecommerce.dto.UpdateOrderDTO;
import com.cydeo.lab8restecommerce.model.ResponseWrapper;
import com.cydeo.lab8restecommerce.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
   //responseWrapper-customize body json
    // (to add success code inside the json,add success message, response message in addition to fields..)
    //responseEntity - custom headers and status code-> we can add additional headers/ can accept different body
    // (we can change the status code, actual status code not inside the body that field)
    // response wrapper we are using with responseEntity, because it allows us to pass different JSON body
    // ok -200 and accepts body only (we need to create object new ResponseWrapper)
    @GetMapping
    public ResponseEntity<ResponseWrapper> retrieveOrderList(){
        return ResponseEntity.ok(new ResponseWrapper("Orders are successfully retrieved",
                orderService.retrieveOrderList(), HttpStatus.OK));// this ok we will see inside JSON
    }

    @PutMapping // update something that already exist
    public ResponseEntity<ResponseWrapper> updateOrder(@RequestBody OrderDTO orderDTO){// will not take id inside my json
        return ResponseEntity.ok(new ResponseWrapper("Orders are successfully updated",
                orderService.updateOrder(orderDTO), HttpStatus.OK ));
    }

    // will not take id inside my json
    @PutMapping("/{id}") // I will accept as id in my @PathVariable           // validation will be considered  from updateOrderDTO
    public ResponseEntity<ResponseWrapper> updateOrder(@PathVariable("id") Long id, @Valid @RequestBody UpdateOrderDTO updateOrderDTO){
    return ResponseEntity.ok(new ResponseWrapper("Order is successfully updated",
            orderService.updateOrderById(id, updateOrderDTO), HttpStatus.OK));
    }






}
