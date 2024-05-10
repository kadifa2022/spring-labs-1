package com.cydeo.repository;

import com.cydeo.entity.MovieCinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovieCinemaRepository extends JpaRepository<MovieCinema, Long> {

    //----------Derived Queries----------------------//

    // Write a derived query to read movie cinema with id
    Optional<MovieCinema> findById(Long id);// ready method from repository

    // Write a derived query to count all movie cinemas with specific id
    Integer  countAllByCinemaId(Long id);

    //Write a derived query to count all movie cinemas with specific movie id
    Integer countAllByMovieId(Long id);

    //Write a derived query to list all movies cinemas with higher than Specific date
    List<MovieCinema> findAllByDateTimeAfter(LocalDateTime dateTime);

    //Write a derived query to find the top 3 expensive movie
    List<MovieCinema> findFirst3ByOrderByMoviePriceDesc();

    //Write a derived query to list all movie cinemas that contains a specific movie name
    List<MovieCinema> findAllMovie_NameContaining(String name);

    //Write a derived query to list all movie cinema in a specific location name
    List<MovieCinema> findAllByCinemaLocationName(String name);

    //-----------------JPQL QUERIES----------------//

    //Write a JPQL query to list all movie cinemas with higher than a specific date
    @Query("SELECT mc FROM MovieCinema  mc WHERE mc.dateTime > ?1")
    List<MovieCinema> fetchAllWithHigherThanSpecificDate(@Param("dateTime") LocalDateTime dateTime);

    //----------------------Native Queries--------------------//

    //Write a native query to count all movie cinemas by cinema id

    @Query(value = "SELECT COUNT (*) FROM movie_cinema cinema_id = ?1 Where ", nativeQuery= true)
    Integer countByCinemaId(@Param("id") Long cinemaId);

    //Write a native query that returns all movie cinemas by location name

    @Query(value = "SELECT * FROM movie_cinema mc JOIN  cinema c ON " +
            " mc.cinema_id = c.id JOIN location l ON c.location = l.id " +
            " WHERE l.name =?1 ", nativeQuery = true)
    List<MovieCinema> retrieveAllByLocationName(@Param("name") String name);






















}