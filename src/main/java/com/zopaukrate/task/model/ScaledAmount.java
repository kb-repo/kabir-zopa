package com.zopaukrate.task.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ScaledAmount {

    private double value;
    private static final int SCALE = 2;

    public ScaledAmount(double value) {
        this.value = BigDecimal.valueOf(value)
                .setScale(SCALE, RoundingMode.HALF_UP).doubleValue();
    }

    public static double from(double value) {
        return new ScaledAmount(value).getValue();
    }

    public double getValue() {
        return value;
    }
}
