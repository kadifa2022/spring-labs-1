package com.cydeo.lab08rest.service.serviceImp;

import com.cydeo.lab08rest.dto.OrderDTO;
import com.cydeo.lab08rest.dto.UpdateOrderDTO;
import com.cydeo.lab08rest.entity.Cart;
import com.cydeo.lab08rest.entity.Customer;
import com.cydeo.lab08rest.entity.Order;
import com.cydeo.lab08rest.entity.Payment;
import com.cydeo.lab08rest.enums.PaymentMethod;
import com.cydeo.lab08rest.exception.NotFoundException;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.OrderRepository;
import com.cydeo.lab08rest.service.CartService;
import com.cydeo.lab08rest.service.CustomerService;
import com.cydeo.lab08rest.service.OrderService;
import com.cydeo.lab08rest.service.PaymentService;
import org.springframework.stereotype.Service;

import java.io.NotActiveException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MapperUtil mapperUtil;
    private final CustomerService customerService;
    private final PaymentService paymentService;
    private final CartService cartService;


    public OrderServiceImpl(OrderRepository orderRepository, MapperUtil mapperUtil, CustomerService customerService, PaymentService paymentService, CartService cartService) {
        this.orderRepository = orderRepository;
        this.mapperUtil = mapperUtil;
        this.customerService = customerService;
        this.paymentService = paymentService;
        this.cartService = cartService;
    }

    @Override
    public List<OrderDTO> retrieveListOrder() {
        return orderRepository.findAll()
                .stream().map(order -> mapperUtil.convert(order, new OrderDTO()))
                .collect(Collectors.toList());
    }
     // another way for updateMethod Jamal
    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        // look for OrderId inside the DB and throw the exception
        Order order = orderRepository.findById(orderDTO.getId()).orElseThrow( // if order exist will return order
                () -> new NotFoundException("Order could not be Found"));
        //then we need to check if the Order fields exists or not and creating method
        validateRelatedFieldsAreExist(orderDTO);
        // if fields exists, then convert orderDTO to order and save it
        Order willBeUpdatedOrder = mapperUtil.convert(orderDTO, new Order());
        Order updatedOrder = orderRepository.save(willBeUpdatedOrder);
        //convert again the updated one and return it
        return mapperUtil.convert(updatedOrder, new OrderDTO());
    }

    private void validateRelatedFieldsAreExist(OrderDTO orderDTO) { //Jamal
        // in this method we have 3 different service and make sure they have those fields
        //we will create service and existById() method to verify
        // if id Doesn't exist (reversing business logic)
        if (!customerService.existById(orderDTO.getCustomerId())) {
            throw new NotFoundException("Customer could not found");
        }
        if (!paymentService.existById(orderDTO.getPaymentId())) {
            throw new NotFoundException("Payment could not found");
        }
        if (!cartService.existById(orderDTO.getCartId())) {
            throw new NotFoundException("Cart could not found");
        }


        }


    /*
        @Override
        public OrderDTO updateOrder(OrderDTO orderDTO) {
           Order order = mapperUtil.convert(orderDTO, new Order());
           order.setCustomer(mapperUtil.convert(customerService.findById(orderDTO.getCustomerId()),new Customer()));
           order.setPayment(mapperUtil.convert(paymentService.findById(orderDTO.getPaymentId()), new Payment()));
           order.setCart(mapperUtil.convert(cartService.findById(orderDTO.getCartId()), new Cart()));
           order.setPaidPrice(orderDTO.getPaidPrice());
           order.setTotalPrice(orderDTO.getTotalPrice());
           Order updatedOrder=orderRepository.save(order);


           return mapperUtil.convert(updatedOrder, new OrderDTO());
        }


     */
    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = mapperUtil.convert(orderDTO, new Order());
        order.setCustomer(mapperUtil.convert(customerService.findById(orderDTO.getCustomerId()),new Customer()));
        order.setPayment(mapperUtil.convert(paymentService.findById(orderDTO.getPaymentId()), new Payment()));
        order.setCart(mapperUtil.convert(cartService.findById(orderDTO.getCartId()), new Cart()));
        order.setPaidPrice(orderDTO.getPaidPrice());
        order.setTotalPrice(orderDTO.getTotalPrice());
        Order updatedOrder=orderRepository.save(order);
        return mapperUtil.convert(updatedOrder, new OrderDTO());
    }

    @Override
    public List<OrderDTO> retrieveOrderByPaymentMethod(PaymentMethod paymentMethod) {
        return orderRepository.findAllByPayment_PaymentMethod(paymentMethod).stream()
                .map(order -> mapperUtil.convert(order, new OrderDTO())).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> retrieveOrderByEmail(String email) {
        return orderRepository.findAllByCustomer_Email(email).stream()
                .map(order -> mapperUtil.convert(order, new OrderDTO())).collect(Collectors.toList());
    }

    @Override
    public OrderDTO updateOrderById(Long id, UpdateOrderDTO updateOrderDTO) { // Jamal
        Order order = orderRepository.findById(id).orElseThrow( // if order exist will return order
                () -> new NotFoundException("Order could not be Found"));
        //if we are getting the same value, it is not necessary to update the actual value

        // we are creating one boolean variable
        boolean changeDetected = false; // default

        if (!order.getPaidPrice().equals(updateOrderDTO.getPaidPrice())) {
            order.setPaidPrice(updateOrderDTO.getPaidPrice());
            changeDetected = true;
        }
        if (!order.getTotalPrice().equals(updateOrderDTO.getTotalPrice())) {
            order.setTotalPrice(updateOrderDTO.getTotalPrice());
            changeDetected = true;
        }
        // if there is any change update the order and return it
        if (changeDetected) {
            Order updateOrder = orderRepository.save(order);
            return mapperUtil.convert(updateOrder, new OrderDTO());
        } else { // if there is no change
            throw new NotFoundException("No changes detected");

        }

    }
}
