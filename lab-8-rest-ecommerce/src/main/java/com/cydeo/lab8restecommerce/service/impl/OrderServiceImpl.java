package com.cydeo.lab8restecommerce.service.impl;

import com.cydeo.lab8restecommerce.client.CurrencyApiClient;
import com.cydeo.lab8restecommerce.dto.CurrencyApiResponse;
import com.cydeo.lab8restecommerce.dto.OrderDTO;
import com.cydeo.lab8restecommerce.dto.UpdateOrderDTO;
import com.cydeo.lab8restecommerce.entity.Order;
import com.cydeo.lab8restecommerce.enums.Currency;
import com.cydeo.lab8restecommerce.exception.CurrencyTypeNotFoundException;
import com.cydeo.lab8restecommerce.exception.NotFoundException;
import com.cydeo.lab8restecommerce.mapper.MapperUtil;
import com.cydeo.lab8restecommerce.repository.OrderRepository;
import com.cydeo.lab8restecommerce.service.CartService;
import com.cydeo.lab8restecommerce.service.CustomerService;
import com.cydeo.lab8restecommerce.service.OrderService;
import com.cydeo.lab8restecommerce.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderServiceImpl implements OrderService {
    @Value("${access_key}")  // will be saved in application.properties
    private String accessKey;
    private final OrderRepository orderRepository;
    private final MapperUtil mapperUtil;
    private final CartService cartService;
    private final PaymentService paymentService;
    private final CustomerService customerService;
    private final CurrencyApiClient currencyApiClient;


    public OrderServiceImpl(OrderRepository orderRepository, MapperUtil mapperUtil, CartService cartService, PaymentService paymentService, CustomerService customerService, CurrencyApiClient currencyApiClient) {
        this.orderRepository = orderRepository;
        this.mapperUtil = mapperUtil;
        this.cartService = cartService;
        this.paymentService = paymentService;
        this.customerService = customerService;
        this.currencyApiClient = currencyApiClient;
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
        if (!order.getPaidPrice().equals(updateOrderDTO.getPaidPrice())) { // comparing if there are equal there is no change
            // if there recognize change i will set new price to order
            order.setPaidPrice(updateOrderDTO.getPaidPrice());
            changeDetected = true;
        }
        if (!order.getTotalPrice().equals(updateOrderDTO.getTotalPrice())) {
            order.setTotalPrice(updateOrderDTO.getTotalPrice());
            changeDetected = true;
        }
        // if there is any change, update the order and return it
        if (changeDetected) {
            Order updateOrder = orderRepository.save(order);
            return mapperUtil.convert(order, new OrderDTO());
        } else {
            throw new NotFoundException("No changes detected");
        }
    }

    @Override
    public OrderDTO retrieveOrderDetailById(Long id, Optional<String> currency) {
        //find the order based on id, converted and return it
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Order could not be found."));
        //optional will be here// if i have currency this line will be included otherwise will be skiped
        //if we are getting currency from the user than this line will execute
        currency.ifPresent(curr -> {// consume api
            //check that if user currency input is valid(inside our currencies list
            validateCurrency(curr);// before using api i  validate currency because of cost
            
            //get the currency data based on currency type
            BigDecimal currencyRate = getCurrencyRate(curr);// created private method to accept currency but will return double
            // do calculations and set new paidPrice and totalPrice
            //these prices for just to give value to customer, we will not update the DB bases on other currencies
            BigDecimal newPaidPrice = order.getPaidPrice().multiply(currencyRate).setScale(2, RoundingMode.HALF_UP);
            BigDecimal newTotalPrice = order.getTotalPrice().multiply(currencyRate).setScale(2,RoundingMode.HALF_UP);
            //set the value to order that we retrieved
            order.setPaidPrice(newPaidPrice);
            order.setTotalPrice(newTotalPrice);
        });

        // convert and return
        return mapperUtil.convert(order, new OrderDTO());
    }

    private void validateCurrency(String curr) { // method to validate currency before sending api because of cost
        //check if the currency is valid currency and use this method before consuming api
        //converting enums to list and checking if the currency is valid currency
        List<String> currencies = Stream.of(Currency.values())
                .map(currency -> currency.value)
                .collect(Collectors.toList());

        boolean  isCurrencyValid = currencies.contains(curr);//checking if the currency is valid currency
        if(!isCurrencyValid){
            throw new CurrencyTypeNotFoundException("Currency type for" + curr + "could not found.");

        }
    }
    /*
    user input
    we sent request to 3rd party api(using feign client)
    success false, error message
     */


    // converted to BigDecimal as return type original was Double
    private BigDecimal getCurrencyRate(String currency) { // all consuming related will be inside this method
        //consume the api  // request part
        // wwe saved response inside the quotes
        Map<String, Double> quotes = (Map<String, Double>) currencyApiClient.getCurrencyRates(accessKey, currency, "USD", 1).get("quotes");
        Boolean isSuccess = (Boolean) currencyApiClient.getCurrencyRates(accessKey, currency, "USD", 1).get("success");
        // check if success, if truer than retrieve
        if (isSuccess) {
            throw new RuntimeException("API IS DOWN");

        }
            String expectedCurrency = "USD" + currency.toUpperCase(); // currency is coming whoever is using this method
            BigDecimal currencyRate = BigDecimal.valueOf(quotes.get(expectedCurrency)); // key that will be use inside the map
            return currencyRate; // converted to BigDecimal

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