package com.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CartService {

    private ProductService productService;
    private final CartItemRepository cartItemRepository;
    private List<Product> recentlyViewedProducts;



    @Autowired
    public CartService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
        this.recentlyViewedProducts = new ArrayList<>();
    }

    private final Cart cart = new Cart();
    private final List<Long> cartItemIds = new ArrayList<>();




    public List<CartItem> getCartItemsForUser(User user) {
        return cartItemRepository.findByUser(user);
    }

    public void addToCart(User user, Product product, int quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    public void addRecentlyViewedProduct(Product product) {
        if (!recentlyViewedProducts.contains(product)) {
            recentlyViewedProducts.add(product);
        }
    }
    public List<Product> getRecentlyViewedProducts() {
        return recentlyViewedProducts;
    }

    public void removeFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public Cart getCart() {
        return cart;
    }

    public List<Long> getCartItemIds() {
            return Collections.unmodifiableList(cartItemIds);
        }

    public List<Long> getProductsByIds(List<Long> productIds) {
            return productIds;
        }
    }



