package com.market;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;
    private double totalAmount;

    public Cart() {
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void addItem(CartItem item) {
        items.add(item);
        updateTotalAmount();
    }

    public void removeItem(CartItem item) {
        items.remove(item);
        updateTotalAmount();
    }

    private void updateTotalAmount() {
        double amount = 0.0;
        for (CartItem item : items) {
            amount += item.getTotal();
        }
        this.totalAmount = amount;
    }
}
