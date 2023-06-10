package com.market.controllers;

import com.market.Cart;
import com.market.CartService;
import com.market.CurrencyRepository;
import com.market.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
    private final ProductService productService;
    private final CurrencyRepository currencyRepository;
    private final CartService cartService;

    public CartController(CartService cartService, ProductService productService, CurrencyRepository currencyRepository) {
        this.cartService = cartService;
        this.productService = productService;
        this.currencyRepository = currencyRepository;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        Cart cart = cartService.getCart();
        model.addAttribute("cartItems", cart.getItems());
        model.addAttribute("totalAmount", cart.getTotalAmount());
        return "cart";
    }
    @PostMapping("/cart")
    public String addToCart(@RequestParam Long productId) {
        productService.addToCart(productId);
        return "redirect:/cart";
    }
}
