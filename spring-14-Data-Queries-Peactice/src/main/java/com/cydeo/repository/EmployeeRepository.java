package com.cydeo.repository;

import com.cydeo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    //jpql query
    @Query("SELECT employee FROM Employee employee WHERE employee.email= 'bmanueau0@dion.ne.jp'")
    Employee retrieveEmployeeDetail();
    // NOt Equal
    @Query("SELECT e FROM Employee  e WHERE e.salary <> ? 1")
    List<Employee> retrieveEmployeeSalaryNotEqual(int salary);


    //Like /Contains/startWith/EndsWith
    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE ?1")//positional parameter
    List<Employee> retrieveEmployeeFirstNameLike(String pattern);

    //Les than
    @Query("SELECT e.firstName FROM Employee  e WHERE e.salary < ?1")
    List<String> retrieveEmployeeSalaryLessThan(int salary);//return only one name than return String
    //Greater than
    @Query("SELECT e FROM Employee  e WHERE e.salary > ?1")
    List<Employee> retrieveEmployeeSalaryGreaterThan(int salary);

    //between
    @Query("SELECT e FROM Employee e WHERE e.salary BETWEEN ?1 AND ?2")

    List<Employee> retrieveEmployeeSalaryBetween(int salary1, int salary2);

    //BEFORE
    @Query("SELECT  e FROM Employee e WHERE e.hireDate > ?1")
    List<Employee> retrieveEmployeeHireDateBefore(LocalDate date);
}
