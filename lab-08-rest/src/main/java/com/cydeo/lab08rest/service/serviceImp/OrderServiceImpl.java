package com.cydeo.lab08rest.service.serviceImp;

import com.cydeo.lab08rest.client.CurrencyApiClient;
import com.cydeo.lab08rest.dto.OrderDTO;
import com.cydeo.lab08rest.dto.UpdateOrderDTO;
import com.cydeo.lab08rest.entity.Cart;
import com.cydeo.lab08rest.entity.Customer;
import com.cydeo.lab08rest.entity.Order;
import com.cydeo.lab08rest.entity.Payment;
import com.cydeo.lab08rest.enums.Currency;
import com.cydeo.lab08rest.enums.PaymentMethod;
import com.cydeo.lab08rest.exception.CurrencyTypeNotFoundException;
import com.cydeo.lab08rest.exception.GlobalExceptionHandler;
import com.cydeo.lab08rest.exception.NotFoundException;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.OrderRepository;
import com.cydeo.lab08rest.service.CartService;
import com.cydeo.lab08rest.service.CustomerService;
import com.cydeo.lab08rest.service.OrderService;
import com.cydeo.lab08rest.service.PaymentService;
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

    @Value("${access_key}")
    private String accessKey;

    private final OrderRepository orderRepository;
    private final MapperUtil mapperUtil;
    private final CustomerService customerService;
    private final PaymentService paymentService;
    private final CartService cartService;
    private final CurrencyApiClient currencyApiClient;


    public OrderServiceImpl(OrderRepository orderRepository, MapperUtil mapperUtil, CustomerService customerService,
                            PaymentService paymentService, CartService cartService, CurrencyApiClient currencyApiClient) {
        this.orderRepository = orderRepository;
        this.mapperUtil = mapperUtil;
        this.customerService = customerService;
        this.paymentService = paymentService;
        this.cartService = cartService;
        this.currencyApiClient = currencyApiClient;
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
        order.setCustomer(mapperUtil.convert(customerService.findById(orderDTO.getCustomerId()), new Customer()));
        order.setPayment(mapperUtil.convert(paymentService.findById(orderDTO.getPaymentId()), new Payment()));
        order.setCart(mapperUtil.convert(cartService.findById(orderDTO.getCartId()), new Cart()));
        order.setPaidPrice(orderDTO.getPaidPrice());
        order.setTotalPrice(orderDTO.getTotalPrice());
        Order updatedOrder = orderRepository.save(order);
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

    @Override
    public OrderDTO retrieveOrderById(Long id, Optional<String> currency) {
        //Find the order based on id from repository
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Order could not be found."));

        // if we are getting currency value from the user
        currency.ifPresent(curr -> {
           // why we're creating (validateCurrency(curr))before sending api we validate currency, because with each api
            // request will cost for us we need to stop in my application before sending the request
            // check that if user currency input is valid(inside the currencies list)
            validateCurrency(curr); // if is invalid currency i will throw our custom exception

            // get the currency data based on currency type
            BigDecimal currencyRate = getCurrencyRate(curr);

            //do calculations and set new paidPrice and TotalPrice
            //these prices for just to give value to customers, we will not update the DB based on other currencies
            BigDecimal newPaidPrice = order.getPaidPrice().multiply(currencyRate).setScale(2, RoundingMode.HALF_UP);
            BigDecimal newTotalPrice = order.getTotalPrice().multiply(currencyRate).setScale(2, RoundingMode.HALF_UP);

            //set the value to order that we retrieved
            order.setPaidPrice(newPaidPrice);
            order.setTotalPrice(newTotalPrice);

        });
        //convert and return it
        return mapperUtil.convert(order, new OrderDTO());
    }

    private void validateCurrency(String curr) { // this method will NOT return anything
        // this method will check if the currency is valid currency
        List<String> currencies = Stream.of(Currency.values())
                .map(currency ->currency.value)
                .collect(Collectors.toList());
       boolean isCurrencyValid= currencies.contains(curr);

       if(!isCurrencyValid){
           throw new CurrencyTypeNotFoundException("Currency type for " + curr +"could not be found.");

       }


    }

    //returning
    private BigDecimal getCurrencyRate(String currency) {

        // consume -> accessKey we manage in one common place app.properties
        // (if we have any change just change in one place)
        // source and format can be manage also in app.properties
        // we save response inside the quotes map
        Map<String, Double> quotes = (Map<String, Double>) currencyApiClient.getCurrencyRates(accessKey, currency,
                "USD", 1).get("quotes");
        // after request what we can do -> check if success is true, then retrieve value (feignClient fold back)

        Boolean isSuccess = (Boolean) currencyApiClient.getCurrencyRates(accessKey,currency,"USD",1).get("success");
        if(!isSuccess){
            throw new RuntimeException("API IS DOWN");
        }

        String expectedCurrency = "USD" + currency.toLowerCase();
        BigDecimal currencyRate = BigDecimal.valueOf(quotes.get(expectedCurrency));

        //before Currency rate check if currency rate is valid(feignClient fold back)

        return currencyRate;


    }
}
