package com.cydeo.repository;

import com.cydeo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //Display all employees with email address
    List<Employee> findByEmail(String email);

    //Display all employees with first name "", and last name"",
    //also show all employees with an email address

    List<Employee> findByFirstNameAndLastNameOrEmail(String firstname, String lastname, String email);

    //Display all employees that first name is not ""

    List<Employee> findByFirstNameIsNot(String firstname);

    //Display all employees where lastname starts with""
    List<Employee>  findByLastNameStartingWith(String pattern);

    //Display all employees with salary less than ""
    List<Employee> findBySalaryLessThan(Integer salary);

    //Display all employee with salary higher than ""
    List<Employee> findBySalaryGreaterThan(Integer salary);

    //Display all employees that has been hired between " " and ""
    List<Employee> findByHireDateBetween(LocalDate startDate, LocalDate endDate);

    //Display all employee where salary greater than equal to "" in order salary
    List<Employee> findBySalaryGreaterThanEqualOrderBySalary(Integer salary);

    //Display top unique 3 employees that is making lest than ""
    List<Employee> findDistinctTop3BySalaryLessThan(Integer salary);

    //Display all employees that do not have email address
    List<Employee> findByEmailIsNull();


}
