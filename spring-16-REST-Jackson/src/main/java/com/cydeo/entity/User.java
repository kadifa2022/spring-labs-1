package com.cydeo.entity;


import com.cydeo.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_account")
public class User extends BaseEntity {

    private String email;
    private String password;
    private String username;

    @Enumerated(EnumType.STRING)
    private UserRole role =  UserRole.USER;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "account_details_id")
     private Account account;


}
