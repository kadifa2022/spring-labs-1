package com.cydeo.entity;

import com.cydeo.enums.UserRole;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data  // if we have @Data ToString method() is calling all the field from the class that's why we are using Mapped by
@Table(name="account_details")
public class Account extends BaseEntity{

    private String name;
    private String address;
    private String country;
    private String state;
    private String city;
    private Integer age;
    private String postalCode;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @OneToOne(mappedBy = "account")//to drop foreign key in account table
    private User user;

}
