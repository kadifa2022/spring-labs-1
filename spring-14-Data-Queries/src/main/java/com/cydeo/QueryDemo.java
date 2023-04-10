package com.cydeo;

import com.cydeo.repository.DepartmentRepository;
import com.cydeo.repository.EmployeeRepository;
import com.cydeo.repository.RegionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component//runner class
public class QueryDemo implements CommandLineRunner {


    private final RegionRepository regionRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public QueryDemo(RegionRepository regionRepository, DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.regionRepository = regionRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public void run(String... args) throws Exception {
                                    //using squel with methods that we created


        System.out.println("-----------------------------Region------------------------------");

        System.out.println("findByCountry: " + regionRepository.findByCountry("Canada"));
        System.out.println("getByCountry: " + regionRepository.getByCountry("Canada"));
        System.out.println("findByCountryContaining: " + regionRepository.findByCountryContaining("United"));
       System.out.println("findByCountryContainsOrderByRegionDesc:"+regionRepository.findByCountryContainsOrderByRegionDesc("United"));
        System.out.println("findByCountryContainsOrderByRegion:"+ regionRepository.findByCountryContainsOrderByRegion("United States"));
        System.out.println("findTopByCountry: " + regionRepository.findTopByCountry("United States"));
        System.out.println("findTop2ByCountry: " + regionRepository.findTop2ByCountry("United State"));


        System.out.println("---------------------------Departments---------------------------------");
        System.out.println("FindByDepartment:" + departmentRepository.findByDepartment("Furniture"));
        System.out.println("findByDivision" + departmentRepository.findByDivision("Health"));
        System.out.println("findByDivisionEndsWith:" + departmentRepository.findByDivisionEndsWith("ics") );
        System.out.println("findDistinctTop3ByDivisionContains:"+ departmentRepository.findDistinctTop3ByDivisionContains("Hea"));

        System.out.println("----------------------------Employees----------------------------------");

        System.out.println("findAllByFirstNameAndLastNameOrEmail:" +employeeRepository.findAllByFirstNameAndLastNameOrEmail("Jodi","Hook", "jhookd@booking.com"));
        System.out.println("findByEmail:" + employeeRepository.findByEmail("ssymonds2@hhs.gov"));
      //  System.out.println("findByFirstNameIsNot" + employeeRepository.findByFirstNameIsNot("''"));
        System.out.println("findByLastNameStartsWith:" + employeeRepository.findByLastNameStartsWith("Ja"));
        System.out.println("findBySalaryGreaterThan:" + employeeRepository.findBySalaryGreaterThan(150000));
        System.out.println("findBySalaryLessThan:" + employeeRepository.findBySalaryLessThan(60000));
      //  System.out.println("findByHireDateBetween:" + employeeRepository.findByHireDateBetween(2006-04-20,2011-11-13);
        System.out.println("findBySalaryGreaterThanEqualOrderBySalary:"+ employeeRepository.findBySalaryGreaterThanEqualOrderBySalary(12000));
        System.out.println("findDistinctTop3BySalaryLessThan:" + employeeRepository.findDistinctTop3BySalaryLessThan(90000));
        System.out.println("findByEmailIsNull:" + employeeRepository.findByEmailIsNull());


        //- JPQL Everything is related to entity
        System.out.println(employeeRepository.retrieveEmployeeDetails());
        System.out.println(employeeRepository.retrieveEmployeeSalary());



    }


    }
