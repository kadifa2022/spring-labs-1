package com.cydeo.dto;

import com.cydeo.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value ={"city", "age", "postalCode"})// to ignore more than one field
@JsonIgnoreProperties(ignoreUnknown = true) //
public class UserDTO {
    @JsonIgnore // to not show this field in responseEntity Json
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//This
    private String password;

    private String username;

   // @Enumerated(EnumType.STRING)
    private UserRole role;
    @JsonManagedReference//(parent) this field will be serialized // will see in JSON
    private AccountDTO account;
}
