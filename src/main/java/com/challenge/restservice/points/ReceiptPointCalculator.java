package com.challenge.restservice.points;

import com.challenge.restservice.data.Receipt;

public interface ReceiptPointCalculator {
    public static class CalculationResult {
        String description;
        Integer amount;
    }
    CalculationResult calculatePoints(Receipt receipt);
}
