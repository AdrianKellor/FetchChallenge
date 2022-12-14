package com.challenge.restservice.input;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReceiptItemInput {
    String shortDescription;
    BigDecimal price;

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
