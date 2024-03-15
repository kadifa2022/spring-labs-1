package com.cydeo.lab8restecommerce.client;


import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(url = "http://apilayer.net/api", name = "currencyApiClient")
public interface CurrencyApiClient {
   // http://apilayer.net/api/live?access_key=483b1ef51d63928d3aeb7079d3bd5557&currencies=BAM&source=USD&format=1
    //Base url= http://apilayer.net/api
    //End Point= /live
    // END of url=?
    //QueryParameters(4 different queryParameter)= & k, v (k is important, whoever accept this request, will accept based on key
    //access_key=483b1ef51d63928d3aeb7079d3bd5557
    //&currencies=BAM
    //&source=USD
    //&format=1

    // Object we put for return type  on the beginning and latter change after we create response in dto

    @GetMapping("/live")
    Map<String, Object> getCurrencyRates(@RequestParam("access_key") String accessKey,
                                         @RequestParam("currencies") String currencies,
                                         @RequestParam("source") String source,
                                         @RequestParam("format") int format);

    }






