package com.cydeo.repository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    //----------------JPQL QUERIES--------------------//


    //Write a JPQL query that returns a user read by email
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> fetchByEmailJPQL(@Param("email")String email);

    //Write a JPQL query that returns a user read by username?
    @Query("SELECT u FROM  User u WHERE u.username = ?1")
    Optional<User> fetchByUsernameJPQL(@Param("username") String username);

    // Write a JPQL query that returns all users
    @Query("SELECT u FROM User u ")
    List<User> fetchAllUsers();


    //--------------------Native Queries --------------------//

    //Write a native query that returns all users that contain a specific name
    @Query(value = " SELECT * FROM user_account u JOIN account_details ad " +
            " ON ad.id =u.account_detail_id WHERE ad.name ILIKE concat ('%',?1,'%')", nativeQuery = true)
    List<User> retrieveAllByName(@Param("name") String name);

    //Write a native query that returns all users?
    @Query(value = "SELECT * FROM user_account ", nativeQuery = true)
    List<User> retrieveAllUsers();

    //Write a native query that returns all users in that range of ages?
    @Query(value = "SELECT * FROM user_account u JOIN account_details ad " +
            " ON ad.id = u.account_details_id WHERE ad.age BETWEEN ?2 AND ?2", nativeQuery = true)
    List<User> retrieveBetweenAgeRange(@Param("age1") Integer age1, @Param("age2") Integer age2);

    //Write a native query to read  user by email?
    @Query(value = "SELECT * FROM user_accout  WHERE email =?1" , nativeQuery = true)

    Optional<User> readByEmail(@Param("email") String email);





}
