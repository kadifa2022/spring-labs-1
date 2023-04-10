package com.cydeo.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Cinema extends BaseEntity{//we don't put constructor if we don't work with the object

    private String name;
    private String sponsoredName;
    private Location location;
}
