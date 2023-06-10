package com.market;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Cart {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();
    private double totalAmount;

    @ElementCollection
    @CollectionTable(name = "cart_items", joinColumns = @JoinColumn(name = "cart_id"))
    @Column(name = "cart_item_ids")
    private List<Long> cartItemIds;



    @Transient
    private CartService cartService;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart(CartService cartService) {
        this.cartService = cartService;
    }



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

    public List<Long> getProducts() {
        Cart cart = getCart();
        List<Long> productIds = cart.getCartItemIds();
        return cartService.getProductsByIds(productIds);
    }
    public List<Product> getProducts(ProductService productService) {
        List<Long> productIds = new ArrayList<>();
        for (CartItem item : items) {
            Object productId = CartItem.getProductId(item);
            productIds.add((Long) productId);
        }

        return productService.getProductsByIds(productIds);
    }


    public List<Long> getCartItemIds() {
        if (cartItemIds == null) {
            cartItemIds = cartService.getCartItemIds();
        }
        return cartItemIds;
    }

    public Cart getCart() {
        return this;
    }
}
