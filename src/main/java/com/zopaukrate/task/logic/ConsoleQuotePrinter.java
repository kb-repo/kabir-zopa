package com.zopaukrate.task.logic;

import com.zopaukrate.task.model.Quote;

public class ConsoleQuotePrinter implements QuotePrinter {

    @Override
    public void print(Quote quote) {
        System.out.println(String.format("Requested amount: GBP %s", quote.getLoanAmount()));
        System.out.println(new StringBuilder("Rate: ").append(quote.getAnnualInterestRate()).append("%"));
        System.out.println(String.format("Monthly repayment: GBP %s", quote.getMonthlyRepayment()));
        System.out.println(String.format("Total repayment: GBP %s", quote.getTotalRepayment()));
    }
}
