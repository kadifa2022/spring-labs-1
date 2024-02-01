package com.cydeo.lab8restecommerce.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class CurrencyApiResponse { // this is my custom POJO for currencies

    private Boolean success;
    private String terms;
    private String privacy;
    private Long timestamp;
    private String source;
    private Map<String, Double> quotes; // after I create currency structure, go to client and change return type

}
