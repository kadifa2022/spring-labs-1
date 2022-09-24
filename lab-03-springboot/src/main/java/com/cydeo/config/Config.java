package com.cydeo.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration//
@ComponentScan(basePackages = "com.cydeo")//define base pacage
public class Config {
@Bean
    public Faker fakerBean(){//crated metho
        return new Faker();

    }

}
