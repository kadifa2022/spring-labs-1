package com.cydeo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;


@Entity
@Table(name = "departments")
@NoArgsConstructor
@Data
public class Department {
    @Id
    private String department;
    private String division;
}
