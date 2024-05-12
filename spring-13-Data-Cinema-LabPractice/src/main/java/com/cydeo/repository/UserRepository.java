package com.cydeo.repository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //-----------------DERIVED QUERIES---------------//

    //Write a derived query to read a user with email?
    Optional<User> findByEmail(String email);

    //Write a derived query to read a user with a username?
    Optional<User> findByUsername(String username);

    //Write a derived query to list all users that contain a specific name?
    List<User> findAllByAccountNameContaining(String name);

    // Write a derived query to list users that contain a specific name in ignore case mode
    List<User> findAllByAccountNameContainingIgnoreCase(String name);
    //Write a derived query to list all users with age greater than a specific user
    List<User> findAllByAccountAgeGreaterThan(Integer age);



}
