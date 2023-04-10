package com.cydeo.controller;

import com.cydeo.dto.User;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/cydeo")
public class Consume_RestTemplate {


    private final String URI = "https://jsonplaceholder.typicode.com/users";

    private final RestTemplate restTemplate;//DI To access

    public Consume_RestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<User[]>readAllUsers(){//if you have DTO
       return  restTemplate.getForEntity(URI, User[].class);//accepting []and output will be User[] and returning baseEntity

    }
    @GetMapping("{id}")
    public Object readUser(@PathVariable("id") Integer id){//Without DTO CLASS
        String URL = URI + "/{id}";//how we gona hit the end point
        return restTemplate.getForObject(URL, Object.class,id);//give me url i give you wholeDTO class

    }
    @GetMapping("/test")
    public ResponseEntity<Object> consumePostFromDummyApi(){
        HttpHeaders headers = new HttpHeaders();//Passing headers
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("app-id","6298ebfecd0551211fce37a6");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange("https://dummyapi.io/data/v1/user?limit=10",
                HttpMethod.GET,entity,Object.class);


    }
    }



