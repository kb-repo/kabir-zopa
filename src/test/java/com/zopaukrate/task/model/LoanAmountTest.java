package com.zopaukrate.task.model;

import com.zopaukrate.task.exception.InvalidAmountRangeException;
import com.zopaukrate.task.exception.InvalidIncrementException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class LoanAmountTest {

    @Test
    public void shouldValidateIncrementOf100() {
        assertThat(
                catchThrowable(() -> LoanAmount.of(10))
        )
        .isInstanceOf(InvalidIncrementException.class)
        .hasMessage("The requested amount must be a multiple of 100");
    }

    @Test
    public void shouldFailWhenRequestedAmountIsLessThanMinimum() {
        assertThat(
                catchThrowable(() -> LoanAmount.of(600))
        )
        .isInstanceOf(InvalidAmountRangeException.class)
        .hasMessage("The requested amount must between 1000 and 15000");
    }

    @Test
    public void shouldFailWhenRequestedAmountIsGreaterThanMaximum() {
        assertThat(
                catchThrowable(() -> LoanAmount.of(15100))
        )
        .isInstanceOf(InvalidAmountRangeException.class)
        .hasMessage("The requested amount must between 1000 and 15000");
    }
}