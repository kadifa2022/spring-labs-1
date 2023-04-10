package com.cydeo.repository;

import com.cydeo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;//jpa inter. covers everything
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface CourseRepository extends JpaRepository<Course, Long> {
    //find all courses by category select * from courses where category
    //follow naming convention find(select) by(where) -reed select
    List<Course> findByCategory(String category);
    //Find all courses by category and order the entities by name
    List<Course> findByCategoryOrderByName(String category);
    //checks if a course with the supplies name exist. return true if exists, false otherwise
    boolean existsByName(String name);

    //Returns the count of courses for given category
    int countByCategory(String category);
    //finds all the courses that start with the given course name string
    List<Course> findByNameStartingWith(String name);

    //Find all courses by category and returns a STREAM
    Stream<Course>streamAllByCategory(String category);


    //named parameter
    @Query("SELECT c FROM Course c WHERE  c.category =:category AND c.rating > :rating" )

    List<Course>retrieveAllByCategoryAndRatingGreaterThan(@Param("category") String category, @Param("rating") int rating);



}