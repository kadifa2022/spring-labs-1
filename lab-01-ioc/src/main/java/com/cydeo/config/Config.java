package com.cydeo.config;

import com.cydeo.Currency;
import com.cydeo.account.Current;
import com.cydeo.account.Saving;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.UUID;

@Configuration
public class Config {
    @Bean
    public Saving savingBean(){
        Saving saving = new Saving();
        saving.setAccountId(UUID.randomUUID());
        saving.setAmount(new BigDecimal("500"));
        saving.setCurrency(new Currency("DLR","Dollar"));//currency  object
        return saving;
    }
    @Bean
    public Currency currencyBean(){
        return  new Currency("DLR", "Dollar");
    }
    @Bean
    public Current currentBean(Currency currency){
        Current current= new Current();
        current.setAccountId(UUID.randomUUID());
        current.setAmount(new BigDecimal("500"));
        current.setCurrency(currency);
        return current;


    }

}
