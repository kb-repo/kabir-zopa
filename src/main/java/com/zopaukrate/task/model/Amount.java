package com.zopaukrate.task.model;


import java.util.Objects;

public class Amount {

    private final int value;

    public Amount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Amount subtract(Amount amount) {
        return new Amount(this.value - amount.getValue());
    }

    public boolean isZero() {
        return this.value == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Amount amount = (Amount) o;
        return value == amount.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}