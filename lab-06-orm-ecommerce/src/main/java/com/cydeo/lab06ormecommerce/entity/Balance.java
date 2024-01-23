package com.cydeo.lab06ormecommerce.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.math.BigDecimal;


@Setter
@Getter
@NoArgsConstructor
@Entity
public class Balance extends BaseEntity{

    private BigDecimal amount;
    @OneToOne (fetch = FetchType.LAZY) // I don't want to load customer information in balance table only when is needed
    private Customer customer; //Uni direction Customer is FK in Balance table
}
