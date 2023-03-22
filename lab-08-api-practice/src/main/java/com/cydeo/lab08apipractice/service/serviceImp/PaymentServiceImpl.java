package com.cydeo.lab08apipractice.service.serviceImp;
import com.cydeo.lab08apipractice.mapper.MapperUtil;
import com.cydeo.lab08apipractice.repository.PaymentRepository;
import com.cydeo.lab08apipractice.service.PaymentService;
import com.cydeo.lab08apipractice.dto.PaymentDTO;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final MapperUtil mapperUtil;

    public PaymentServiceImpl(PaymentRepository paymentRepository, MapperUtil mapperUtil) {
        this.paymentRepository = paymentRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public PaymentDTO findById(Long id) {
        return mapperUtil.convert(paymentRepository.findById(id), new PaymentDTO());
    }
}
