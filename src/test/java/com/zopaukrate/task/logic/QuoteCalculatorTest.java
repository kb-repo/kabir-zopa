package com.zopaukrate.task.logic;

import com.zopaukrate.task.model.Lender;
import com.zopaukrate.task.model.LoanAmount;
import com.zopaukrate.task.model.Quote;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class QuoteCalculatorTest {

    private static final List<Lender> LENDERS = Arrays.asList(
            new Lender("Fred", 0.071, 540),
            new Lender("Mary", 0.104, 170),
            new Lender("John", 0.081, 320),
            new Lender("Bob", 0.075, 640),
            new Lender("Jane", 0.069, 480),
            new Lender("Dave", 0.074, 140),
            new Lender("Angela", 0.071, 60)
    );

    private final QuoteCalculator quoteCalculator = new QuoteCalculatorImpl(36);

    @Test
    void shouldGetCorrectQuote() {
        LoanAmount requestedAmount = LoanAmount.of(1000);

        Optional<Quote> quote = quoteCalculator.getQuote(requestedAmount, LENDERS);

        assertThat(quote)
                .isPresent();
        assertThat(quote.get())
                .usingRecursiveComparison()
                .isEqualTo(
                        new Quote(requestedAmount.getValue(), 0.07, 30.78, 1108.10)
                );
    }

    @Test
    void shouldReturnEmptyQuoteWhenThereAreEnoughOffers() {
        Optional<Quote> quote = quoteCalculator.getQuote(LoanAmount.of(15000), LENDERS);
        assertThat(quote)
                .isEmpty();
    }

}