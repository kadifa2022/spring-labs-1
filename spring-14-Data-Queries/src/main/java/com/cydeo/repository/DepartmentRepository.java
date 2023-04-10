package com.cydeo.repository;

import com.cydeo.entity.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, String> {

    //Display all departments in the furniture department
   List<Department> findByDepartment(String Department);
   //Display all departments in Health Division
    List<Department> findByDivision(String division);
    List <Department>findByDivisionIs(String division);//same as findByDivision
    List<Department>findByDivisionEquals(String division);//same as findByDivision
    //Display all departments wth division name ends with 'ics'
    List<Department>findByDivisionEndsWith(String patter);

    //display top 3 departments with division name includes 'Hea' without duplicates
    List<Department> findDistinctTop3ByDivisionContains(String patter);

    //native Query - value + pure sql and native query

    //how to get more divisions from diferent depa (IN) and list in parameter --positional parameter
    @Query("SELECT d FROM Department d WHERE d.division IN  ?1")
    List<Department> retrieveDepartmentDivision(List<String> division);







}
