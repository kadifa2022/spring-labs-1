package com.cydeo.lab08apipractice.service;


import com.cydeo.lab08apipractice.dto.PaymentDTO;


public interface PaymentService {
    PaymentDTO findById(Long id);


}
