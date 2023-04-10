package com.cydeo.repository;

import com.cydeo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
//JPQL
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //Display all employees with email address

    List<Employee> findByEmail(String email);

    //Display all employees with first name "" and last name"",
    //also show all employees with an email
    List <Employee> findAllByFirstNameAndLastNameOrEmail(String firstname, String lastname, String email);

    //Display all employees that first name is not ""
    List<Employee> findByFirstNameIsNot(String firstname);

    //Display all employees where last name starts with ""
    List<Employee> findByLastNameStartsWith(String patten);

    //Display all employees with salary higher than ""
    List<Employee> findBySalaryGreaterThan(Integer salary);


    //Display all employees with salary less than ""
    List<Employee> findBySalaryLessThan(Integer salary);

    //Display all employees that has been hired between "" and ""
//    List<Employee> findByHireDateBetween(LocalDate startDate, LocalDate endDate);

    //Display all employees where salaries greater and equal to "" in order-salary
    List<Employee> findBySalaryGreaterThanEqualOrderBySalary(Integer salary);

    //Display top unique 3 employees that is making less than ""

    List<Employee> findDistinctTop3BySalaryLessThan(Integer salary);

    //Display all employees that do not have email address

    List<Employee> findByEmailIsNull();


        //JPQL -Everything is related to Entity
        @Query("SELECT employee FROM Employee employee WHERE employee.email='acurwood6@1und1.de' ")
        Employee retrieveEmployeeDetails();
        @Query("SELECT e.salary FROM Employee e WHERE e.email='acurwood6@1und1.de'")
       Integer retrieveEmployeeSalary();

        //NOT EQUaEL
    @Query("SELECT e FROM Employee e WHERE e.salary<>?1")//not equal <>
    List<Employee> retrieveEmployeeSalaryNotEqual(int salary);

    //Like / contains /startswiths / endswith
    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE ?1")
    List<Employee>retrieveEmployeeFirstNameLike(String pattern);

    //Less than
    @Query("SELECT e.firstName FROM Employee  e WHERE e.salary< ?1")
    List<String>retrieveEmployeeSalaryLessThan(int salary);

    @Query("SELECT e FROM Employee  e WHERE e.salary>?1")
    List<Employee>retrieveEmployeeSalaryGreaterThan(int salary);
   // BETWEEN

    @Query("SELECT e FROM Employee  e WHERE e.salary BETWEEN ?1 AND ?2")
    List<Employee>retrieveEmployeeSalaryBetween(int salary1, int salary2);
    //BEFORE
    @Query("SELECT e FROM Employee  e WHERE e.hireDate >?1")
    List<Employee> retrieveEmployeeHireDateBefore(LocalDate date);

    //NULL
    @Query("SELECT e FROM Employee  e WHERE e.email IS NULL ")
    List<Employee> retrieveEmployeeEmailIsNull();

    @Query("SELECT e FROM Employee  e WHERE e.email IS NOT NULL ")
    List<Employee> retrieveEmployeeEmailIsNotNull();
    //SortinG in asc  AND DESC order
    @Query("SELECT e FROM Employee e ORDER BY e.salary")
    List<Employee> retrieveEmployeeSalaryOrderAsc();
    @Query("SELECT e FROM Employee e ORDER BY e.salary DESC")
    List<Employee> retrieveEmployeeSalaryOrderAscDesc();

    //Native Query  first write @query (value pure sql query) and native query
    @Query(value="SELECT * FROM Employee   WHERE  salary = ?1", nativeQuery = true)
    List<Employee> retrieveEmployeeDetailBySalary(int salary);

    //Named Parameter--based on name positional

    @Query("SELECT e FROM Employee e WHERE e.salary=:salary")
    List<Employee> retrieveEmployeeSalary(@Param("salary") int salary);
    //passing parameter
    //@Query("?3")
   // List<Employee> retrieveEmployeeDetailBySalary2(int salary1, int salary2, int salary3);




    }

