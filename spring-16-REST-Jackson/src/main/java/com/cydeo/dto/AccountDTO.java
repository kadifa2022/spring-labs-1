package com.cydeo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private String name;
    private String address;
    private String country;
    private String state;
    private String city;
    private String postalCode;
    private Integer age;

    @JsonBackReference() // this field will not be serialized // will hide this field
    private UserDTO user; // we cut the circle structure that was causing StackOverFlow error

}
