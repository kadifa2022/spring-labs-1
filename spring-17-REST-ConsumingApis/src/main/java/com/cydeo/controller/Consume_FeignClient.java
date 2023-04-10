package com.cydeo.controller;

import com.cydeo.client.EmployeeClient;
import com.cydeo.client.UserClient;
import com.cydeo.dto.ResponseWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Consume_FeignClient {
    private final UserClient userClient;

    private final EmployeeClient employeeClient;

    public Consume_FeignClient(UserClient userClient, EmployeeClient employeeClient) {
        this.userClient = userClient;
        this.employeeClient = employeeClient;
    }


    @GetMapping("/api/v1/users")  //DI to call UserClient to get  methode getUsers()   --will create my endpoint
    public ResponseEntity<ResponseWrapper> getUsers(){
        return ResponseEntity.ok(new ResponseWrapper
                ("UserList Retrieved",userClient.getUsers()));

    }
    @GetMapping("/api/v1/employee")
    public ResponseEntity<ResponseWrapper> getEmployee(){//create own api
        return ResponseEntity.ok(new ResponseWrapper("Employee Retrieved",employeeClient.getEmployee("6298ebfecd0551211fce37a6")));

    }


}
