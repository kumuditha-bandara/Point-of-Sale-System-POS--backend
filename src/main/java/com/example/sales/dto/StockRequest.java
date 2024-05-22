package com.example.sales.dto;

public class StockRequest {
    private Integer item_id;
    private int quantity;

    // Getters and setters
    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
