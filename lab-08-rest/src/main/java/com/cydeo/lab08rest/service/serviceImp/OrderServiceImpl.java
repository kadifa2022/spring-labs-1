package com.cydeo.lab08rest.service.serviceImp;

import com.cydeo.lab08rest.dto.OrderDTO;
import com.cydeo.lab08rest.entity.Cart;
import com.cydeo.lab08rest.entity.Customer;
import com.cydeo.lab08rest.entity.Order;
import com.cydeo.lab08rest.entity.Payment;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.OrderRepository;
import com.cydeo.lab08rest.service.CartService;
import com.cydeo.lab08rest.service.CustomerService;
import com.cydeo.lab08rest.service.OrderService;
import com.cydeo.lab08rest.service.PaymentService;
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
        return orderRepository.findAll()
                .stream().map(order -> mapperUtil.convert(order, new OrderDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
       Order order = mapperUtil.convert(orderDTO, new Order());
       order.setCustomer(mapperUtil.convert(customerService.findById(orderDTO.getCustomerId()),new Customer()));
       order.setPayment(mapperUtil.convert(paymentService.findById(orderDTO.getPaymentId()), new Payment()));
       order.setCart(mapperUtil.convert(cartService.findById(orderDTO.getCartId()), new Cart()));

       Order updateOrder = orderRepository.save(order);

        return mapperUtil.convert(updateOrder, new OrderDTO());
    }
}
