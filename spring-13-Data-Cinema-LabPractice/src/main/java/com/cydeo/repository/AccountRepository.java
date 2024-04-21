package com.cydeo.repository;

import com.cydeo.entity.Account;
import com.cydeo.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    //Derived Queries

    //Write a  derived query to list all accounts with a specific country or state
    List<Account> findAllByCountryOrState(String country,String name);

    //Write a derived query to list all accounts with age lower than equal to specific value
    List<Account> findAllByAgeLessThanEqual(Integer age);

    //Write a derived query to list all account with specific role
    List<Account> findByRole(UserRole role);

    //Write a derived query to list all accounts between a range of ages
    List<Account> findAllByAgeBetween();
}
