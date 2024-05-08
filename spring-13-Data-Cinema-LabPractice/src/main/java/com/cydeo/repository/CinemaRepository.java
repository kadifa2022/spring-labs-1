package com.cydeo.repository;

import com.cydeo.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long>{

    //-----------Derived Queries ----------------//

    //Write a derived query  to get  cinema with specific name
    Optional<Cinema> findByName(String name); //to avoid nullPointerException

    //Write a derived query to read sorted the top 3 cinemas that contains a specific sponsored name
    List<Cinema>  findTop3BySponsoredNameContainingOrderBySponsoredName(String sponsoredName);

    //Write a derived query to list all cinemas in a specific country
    List<Cinema> findAllByLocationCountry(String country);

    //Write a derived query to list all cinemas with a specific name or sponsor name
    List<Cinema> findAllByNameOrSponsoredName(String name,String sponsoredName);



}
