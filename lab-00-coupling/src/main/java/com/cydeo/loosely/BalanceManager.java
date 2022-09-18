package com.cydeo.loosely;

import java.math.BigDecimal;

public class BalanceManager {




    public boolean checkout(Balance balance, BigDecimal amount){
    BigDecimal balanceAmount = balance.getAmount();

        return balanceAmount.subtract(amount)
            .compareTo(BigDecimal.ZERO) > 0;
            // add method parameters


        // implement checkout business


    }
}
