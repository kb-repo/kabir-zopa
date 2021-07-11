package com.zopaukrate.task.model;

public class Lender implements Comparable<Lender> {

    private String lender;
    private double rate;
    private int availableAmount;

    public Lender(String name, double rate, int availableAmount) {
        this.lender = name;
        this.rate = rate;
        this.availableAmount = availableAmount;
    }

    public double getRate() {
        return rate;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    @Override
    public int compareTo(Lender offer) {
        if (this.getRate() == offer.getRate())
            return 0;
        else {
            return this.getRate() > offer.getRate() ? 1 : -1;
        }
    }
}
