package cydeo.client;

import cydeo.dto.country.CountryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url= "https://restcountries.com/v3.1", name = "COUNTRY-CLIENT")
public interface CountryClient {
    //https://restcountries.com/v3.1/name/france


    @GetMapping("/name/{countryName}")
    List<CountryDTO> getCountryInfo(@PathVariable("countryName") String CountryName);


    }

