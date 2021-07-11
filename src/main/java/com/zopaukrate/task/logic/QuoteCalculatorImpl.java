package com.zopaukrate.task.logic;

import com.zopaukrate.task.model.*;

import java.util.*;

public class QuoteCalculatorImpl implements QuoteCalculator {

    public final int loanDuration;

    public QuoteCalculatorImpl(int loanDuration) {
        this.loanDuration = loanDuration;
    }

    @Override
    public Optional<Quote> getQuote(final LoanAmount loanAmount, final List<Lender> lenders) {
        if(loanAmount.getValue() > getAvailableAmount(lenders).getValue()){
            return Optional.empty();
        }
        double totalInterest = computeTotalInterest(loanAmount, lenders);
        double annualInterestRate = computeAnnualInterestRate(loanAmount, totalInterest);
        double monthlyPayment = computeMonthlyPayment(loanAmount, annualInterestRate);

        return Optional.of(
                new Quote(
                        loanAmount.getValue(),
                        ScaledAmount.from(annualInterestRate),
                        ScaledAmount.from(monthlyPayment),
                        ScaledAmount.from(monthlyPayment * loanDuration)
                )
        );
    }

    private double computeTotalInterest(LoanAmount loanAmount, List<Lender> lenders) {
        Collections.sort(lenders);

        double totalInterest = 0;
        Amount remainingAmount = loanAmount.toAmount();

        for (Lender lender : lenders) {
            if (remainingAmount.isZero()) {
                break;
            }
            Amount borrowedAmount = new Amount(Math.min(remainingAmount.getValue(), lender.getAvailableAmount()));
            double interestOfBorrowedAmount = lender.getRate() * borrowedAmount.getValue();
            totalInterest = totalInterest + interestOfBorrowedAmount;
            remainingAmount = remainingAmount.subtract(borrowedAmount);
        }

        return totalInterest;
    }

    private double computeAnnualInterestRate(LoanAmount loanAmount, double totalInterest) {
        return totalInterest / loanAmount.getValue();
    }

    private double computeMonthlyPayment(LoanAmount loanAmount, double annualInterestRate) {
        double monthlyInterestRate = Math.pow((1 + annualInterestRate), (1.0 / 12)) - 1;
        return (monthlyInterestRate * loanAmount.getValue()) /
                (1 - (Math.pow(1 + monthlyInterestRate, - loanDuration)));
    }

    private Amount getAvailableAmount(List<Lender> lenders) {
            return new Amount(
                    lenders.stream()
                            .map(Lender::getAvailableAmount)
                            .mapToInt(amount -> amount)
                            .sum()
            );
    }
}