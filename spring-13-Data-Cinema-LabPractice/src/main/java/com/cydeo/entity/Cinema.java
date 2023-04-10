package com.cydeo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Cinema extends BaseEntity{//we don't put constructor if we don't work with the object

    private String name;
    private String sponsoredName;
    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;
}
