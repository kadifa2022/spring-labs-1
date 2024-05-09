package com.cydeo.repository;

import com.cydeo.entity.MovieCinema;
import org.springframework.data.jpa.repository.JpaRepository;


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


}