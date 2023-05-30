package com.market;

import javax.persistence.*;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    private int quantity;
    private double total;

  public CartItem(){

  }
    public CartItem(Long id, int quantity, double total) {
        this.id = id;
        this.quantity = quantity;
        this.total = total;

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUser(User user) {
    }

    public void setProduct(Product product) {
    }

    public double getTotal() {
        return total;
    }
}

