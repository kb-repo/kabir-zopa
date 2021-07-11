package com.zopaukrate.task.model;


public class Quote {

    private int loanAmount;
    private double annualInterestRate;
    private double monthlyRepayment;
    private double totalRepayment;

    public Quote(int loanAmount, double annualInterestRate, double monthlyRepayment, double totalRepayment) {
        this.loanAmount = loanAmount;
        this.annualInterestRate = annualInterestRate;
        this.monthlyRepayment = monthlyRepayment;
        this.totalRepayment = totalRepayment;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public double getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public double getTotalRepayment() {
        return totalRepayment;
    }
}