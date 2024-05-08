package com.cydeo.repository;

import com.cydeo.entity.Account;
import com.cydeo.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    List<Account> findAllByAgeBetween(Integer ageStart, Integer ageEnd);

    // Write a derived query to list all accounts where the beginning of the address contains keyword
    List<Account> findAllByAddressStartingWith(String keyword);

    List<Account> findAllByOrderByAgeDesc();

    //--------------JPQL QUERIES--Related to entity class not to table-------------------//
    //Object oriented independent query language
    //JPQL DB independent-> tomorrow if we switch to another DB we don't need to update anything.

    //Write a JPQL query to list all accounts with age
    @Query("SELECT a.age FROM Account a ")
    List<Account> fetchAllByAccountByAge();

    //Write a JPQL query to list all admin accounts
    @Query("SELECT a FROM Account a WHERE a.role = 'ADMIN' ")
    List<Account> fetchAllAdminAccounts();

    // Write a JPQL query to sort all accounts with age
    @Query("SELECT a.age  FROM Account a ORDER BY a.age DESC ")
    List<Account> fetchAllOrderByAge();

    //-----SQL -------------Native Queries------faster performance-than JPQL--------------

    //Write native queries to read all accounts with an age lower than  a specific value
    @Query(value = " SELECT * FROM account-details where age < ?1",nativeQuery = true)//age<:age
    List<Account> retrieveAllByAgeLowerThan( Integer age);// (@Param("age") Integer age

    //Write a native query to read all accounts tht a specific value can be containable in the name,
    // address, country, state city

    @Query(value = "SELECT  * FROM acount_details where name ILIKE concat('%',?1,'%')" +
            "OR address ILIKE concat('%',?1, '%')" +
            "OR country ILIKE concat ('%',?1,'%')" +
            "OR state ILIKE concat('%',?1,'%')" +
            "OR city ILIKE concat('%',?1,'%')", nativeQuery = true)
    List<Account> retrieveBySearchCriteria(@Param("pattern") String pattern);

    //Write a native query to read all accounts with an age higher than  specific
    // 2 ways of creating native query (both are the same
    @Query(value = "SELECT * FROM account-details WHERE age >: age", nativeQuery = true)
    List<Account> getAccountsGreaterThan(@Param("age") Integer age);

    @Query(value = " SELECT * FROM account-details where age > ?1",nativeQuery = true)//age<:age
    List<Account> retrieveAllByAgeGreaterThan( Integer age);// (@Param("age") Integer age
/*

 What is sql injection?
    @Param() is preventing sql injection = security reason,
    what ever we get the input from the user,
     (something from amazon for exp: javabook; DROP TABLE customs;)


 */























}
