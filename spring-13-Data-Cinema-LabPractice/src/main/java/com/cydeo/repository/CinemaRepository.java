package com.cydeo.repository;

import com.cydeo.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    //-----------------JPQL QUERIES----------------//
    //Write a JPQL query to read the cinema name with a specific id
    @Query("SELECT c.name FROM Cinema c WHERE c.id = ?1 ")
    Optional<String> fetchById(@Param("id") Long id);

    // -----------------Native QUERIES----------------//
    // Write a native query to read all cinema by location country

    @Query(value = " SELECT * FROM cinema c JOIN location l" +
            " ON c.location_id = l.id WHERE  l.country = ?1 ", nativeQuery = true)
    List<Cinema> retrieveAllByLocationsCountry(@Param("locationCountry") String locationCountry);

    //Write a native query to read all cinemas by name or sponsor name contains a specific pattern
    @Query(value = "SELECT * FROM cinema WHERE name ILIKE concat ('%',?1,'%')" +
            " OR sponsored_name ILIKE concat ('%',?1,'%') ", nativeQuery = true)
    List<Cinema> retrieveAllByNameOrSponsorName(@Param("pattern") String pattern);

//case-sensitive if we don't use ILIKE (LOWER(name) or UPPER(name or sponsored_name )

    // write a native query to sort all cinemas by name
    @Query(value = "SELECT * FROM cinema ORDER BY name", nativeQuery = true)
    List<Cinema> sortByName();

    //Write a native query to distinct all cinema by sponsored name
    @Query(value = "SELECT DISTINCT sponsored-name FROM cinema ", nativeQuery = true)
    List<Cinema> distinctBySponsoredName();











}
