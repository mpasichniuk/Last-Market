package com.market.controllers;

import com.market.*;
import exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    private CartService cartService;
    private ProductService productService;

    public AuthController(UserService userService, ProductService productService, CartService cartService) {
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Invalid username and/or password.");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        User existingUser = userService.findUserByUsername(user.getUsername());
        if (existingUser != null) {
            bindingResult.rejectValue("username", "error.user", "Username is already taken.");
        }
        if (!bindingResult.hasErrors()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            model.addAttribute("message", "Registration successful. Please log in.");
            return "login";
        }
        return "register";
    }
            @GetMapping("/cart")
            public String viewCart(Model model, Principal principal) {
                User user = userService.findUserByUsername(principal.getName());
                List<CartItem> cartItems = cartService.getCartItemsForUser(user);
                model.addAttribute("cartItems", cartItems);
                return "cart";
            }

            @PostMapping("/cart/add")
            public String addToCart(@RequestParam("productId") Long productId, @RequestParam("quantity") int quantity, Principal principal) throws ProductNotFoundException {
                User user = userService.findUserByUsername(principal.getName());
                Product product = productService.getProductById(productId);
                cartService.addToCart(user, product, quantity);
                return "redirect:/cart";
            }

            @PostMapping("/cart/remove/{cartItemId}")
            public String removeFromCart(@PathVariable("cartItemId") Long cartItemId) {
                cartService.removeFromCart(cartItemId);
                return "redirect:/cart";
            }


        }




