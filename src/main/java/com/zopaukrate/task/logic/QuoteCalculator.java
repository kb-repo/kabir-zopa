package com.zopaukrate.task.logic;

import com.zopaukrate.task.model.Lender;
import com.zopaukrate.task.model.LoanAmount;
import com.zopaukrate.task.model.Quote;

import java.util.List;
import java.util.Optional;

public interface QuoteCalculator {
    Optional<Quote> getQuote(final LoanAmount requestedAmount, final List<Lender> lenders);
}
