package com.cydeo.repository;

import com.cydeo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface CourseRepository extends JpaRepository<Course, Long> {

    //Find all courses by categories (select * everything where(by) category = ..)
    List<Course> findByCategory(String category);
    //Find all courses by category and order the entities by name
    List<Course> findByCategoryOrderByName(String category);

    //Check if a course with supplied name exists. Return true if exists, false if not

    boolean existsByName(String name);
    //Return the count of courses for the given category

    int countByCategory(String category);

    //Find all the courses that start with the given course name string
    List<Course> findByNameStartingWith(String name);
    //Find all courses by category and returns stream
    Stream<Course> streamAllByCategory(String category); //STREAM


       //with namew two parameter

    @Query("SELECT c FROM Course c WHERE c.category = : category AND c.rating > :rating")

    List<Course> findAllByCategoryAndRatingGreaterThan(@Param("category")String category, @Param("rating")int rating);


}
