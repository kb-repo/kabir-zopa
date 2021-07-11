package com.zopaukrate.task;

import com.zopaukrate.task.model.Lender;
import com.zopaukrate.task.model.LoanAmount;
import com.zopaukrate.task.model.Quote;
import com.zopaukrate.task.logic.QuotePrinter;
import com.zopaukrate.task.logic.QuoteCalculator;

import java.util.List;

public class QuoteEntryPoint {

    private final QuoteCalculator quoteService;
    private final QuotePrinter quotePrinter;
    private final UserInput userInput;

    public QuoteEntryPoint(UserInput userInput, QuoteCalculator quoteService, QuotePrinter quotePrinter) {
        this.userInput = userInput;
        this.quoteService = quoteService;
        this.quotePrinter = quotePrinter;
    }

    public void printQuote(List<Lender> lenders) {
        Quote quote = quoteService.getQuote(LoanAmount.of(userInput.getAmountInput()), lenders)
            .orElseThrow(
                    () -> new RuntimeException("It is not possible to provide a quote.")
            );
        quotePrinter.print(quote);
    }
}
