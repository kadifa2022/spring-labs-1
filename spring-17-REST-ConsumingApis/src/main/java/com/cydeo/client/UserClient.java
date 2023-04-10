package com.cydeo.client;

import com.cydeo.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "https://jsonplaceholder.typicode.com", name="USER-CLIENT")
public interface UserClient {

    @GetMapping("/users")
    List<User> getUsers();//any time you call this methode will execute with end point(url/users)-with @GetMapping will convert to user through jackson


}
