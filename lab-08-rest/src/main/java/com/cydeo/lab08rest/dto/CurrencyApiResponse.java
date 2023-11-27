package com.cydeo.lab08rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CurrencyApiResponse {

    // this is my custom POJO

    private Boolean success;
    private String terms;
    private String privacy;
    private Long timestamp;
    private String source;
    private Map<String, Double> quotes;

}
