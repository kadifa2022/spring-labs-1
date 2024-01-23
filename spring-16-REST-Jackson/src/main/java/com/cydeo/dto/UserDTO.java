package com.cydeo.dto;

import com.cydeo.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String email;
    private String password;
    private String username;
   // @Enumerated(EnumType.STRING)
    private UserRole role;
    @JsonManagedReference//(parent) this field will be serialized // will see in JSON
    private AccountDTO account;
}
