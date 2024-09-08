package com.cydeo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="user_account")
public class User extends BaseEntity {//primary key base entity

    private String email;
    private String password;
    private String username;
    @OneToOne(fetch= FetchType.LAZY) // load  Account only when is needed
    @JoinColumn(name ="account_details_id")
    private Account account;

    @Override
    public String toString() {// we create costume toString () withOut Account, because we don't need to call all the time
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
