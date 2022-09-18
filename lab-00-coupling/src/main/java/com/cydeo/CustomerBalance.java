package com.cydeo;

import com.cydeo.loosely.Balance;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter


public class CustomerBalance extends Balance {

    private UUID userId;
    private BigDecimal amount;

    public CustomerBalance(UUID userId, BigDecimal amount) {
        this.userId = userId;
        this.amount = amount;
    }
    @Override
    public BigDecimal addBalance(BigDecimal amount) {
        setAmount(this.amount.add(amount));
        return this.amount;
    }
}
