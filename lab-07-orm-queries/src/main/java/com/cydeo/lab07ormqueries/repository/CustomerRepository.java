package com.cydeo.lab07ormqueries.repository;

import com.cydeo.lab07ormqueries.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Long, String> {//
    //Write a derived query to get all customer by id

    Optional <Customer> findById(Long id);//optional prefered because of null pointer acceptation (if no customer)
    //Write a JPQL query to get customer by email
    @Query("SELECT  c FROM Customer c WHERE c.email=?1")
    Customer retrieveByCustomerEmail(String name);//


}
