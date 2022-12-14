package com.challenge.restservice.input;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReceiptInput {

    String retailer;
    LocalDate purchaseDate;
    LocalTime purchaseTime;
    BigDecimal total;

    ReceiptItemInput[] items;

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ReceiptItemInput[] getItems() {
        return items;
    }

    public void setItems(ReceiptItemInput[] items) {
        this.items = items;
    }
}
