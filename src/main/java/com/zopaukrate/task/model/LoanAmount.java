package com.zopaukrate.task.model;

import com.zopaukrate.task.exception.InvalidAmountRangeException;
import com.zopaukrate.task.exception.InvalidIncrementException;

public class LoanAmount {

    private int value;
    private final int MINIMUM_AMOUNT = 1000;
    private final int MAXIMUM_AMOUNT = 15000;

    private LoanAmount(int amount) {
        checkMultipleOfOneHundred(amount);
        checkAmountLimits(amount);
        this.value = amount;
    }

    public static LoanAmount of(int amount) {
        return new LoanAmount(amount);
    }

    public int getValue() {
        return value;
    }

    public Amount toAmount() {
        return new Amount(this.getValue());
    }

    private void checkAmountLimits(int amount) {
        if (amount < MINIMUM_AMOUNT || amount > MAXIMUM_AMOUNT) {
            throw new InvalidAmountRangeException("The requested amount must between 1000 and 15000");
        }
    }

    private void checkMultipleOfOneHundred(int value) {
        if (value % 100 != 0) {
            throw new InvalidIncrementException("The requested amount must be a multiple of 100");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoanAmount amount = (LoanAmount) o;
        return value == amount.value;
    }
}

