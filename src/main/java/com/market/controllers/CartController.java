package com.market.controllers;

import com.market.Cart;
import com.market.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        Cart cart = cartService.getCart();
        model.addAttribute("cartItems", cart.getItems());
        model.addAttribute("totalAmount", cart.getTotalAmount());
        return "cart";
    }
}
