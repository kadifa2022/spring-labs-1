package com.cydeo.lab08apipractice.service.serviceImp;

import com.cydeo.lab08apipractice.entity.Cart;
import com.cydeo.lab08apipractice.entity.Customer;
import com.cydeo.lab08apipractice.entity.Order;
import com.cydeo.lab08apipractice.entity.Payment;
import com.cydeo.lab08apipractice.enums.PaymentMethod;
import com.cydeo.lab08apipractice.repository.OrderRepository;
import com.cydeo.lab08apipractice.dto.OrderDTO;
import com.cydeo.lab08apipractice.mapper.MapperUtil;
import com.cydeo.lab08apipractice.service.CartService;
import com.cydeo.lab08apipractice.service.CustomerService;
import com.cydeo.lab08apipractice.service.OrderService;
import com.cydeo.lab08apipractice.service.PaymentService;
import org.springframework.stereotype.Service;
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
        return orderRepository.findAll().stream().map(order->mapperUtil.convert(order, new OrderDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        Order order = mapperUtil.convert(orderDTO, new Order());
        order.setCustomer(mapperUtil.convert(customerService.findById(orderDTO.getCustomerId()), new Customer()));
        order.setPayment(mapperUtil.convert(paymentService.findById(orderDTO.getPaymentId()), new Payment()));
        order.setCart(mapperUtil.convert(cartService.findById(orderDTO.getCartId()), new Cart()));
        order.setPaidPrice(orderDTO.getPaidPrice());
        order.setTotalPrice(orderDTO.getTotalPrice());
        Order updatedOrder = orderRepository.save(order);

        return mapperUtil.convert(updatedOrder, new OrderDTO());
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = mapperUtil.convert(orderDTO, new Order());
        order.setCustomer(mapperUtil.convert(customerService.findById(orderDTO.getCustomerId()), new Customer()));
        order.setPayment(mapperUtil.convert(paymentService.findById(orderDTO.getPaymentId()), new Payment()));
        order.setCart(mapperUtil.convert(cartService.findById(orderDTO.getCartId()), new Cart()));
        order.setPaidPrice(orderDTO.getPaidPrice());
        order.setTotalPrice(orderDTO.getTotalPrice());
        Order updatedOrder = orderRepository.save(order);
        return mapperUtil.convert(updatedOrder, new OrderDTO());
    }

    @Override
    public List<OrderDTO> retrieveOrderByPaymentMethod(PaymentMethod paymentMethod) {//enum
        return orderRepository.findAllByPayment_PaymentMethod(paymentMethod).stream()
                .map(order -> mapperUtil.convert(order, new OrderDTO())).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> retrieveOrderByEmail(String email) {
        return orderRepository.findAllByCustomer_Email(email).stream()
                .map(order -> mapperUtil.convert(order, new OrderDTO())).collect(Collectors.toList());
    }



}
