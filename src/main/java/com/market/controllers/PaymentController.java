package com.market.controllers;

import com.market.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;
    private final ProductService productService;
    private final CurrencyRepository currencyRepository;

    public PaymentController(ProductService productService, CurrencyRepository currencyRepository) {
        this.productService = productService;
        this.currencyRepository = currencyRepository;
    }

    @GetMapping("/payment")
    public String getPayment(Model model) {
        List<Product> cartItems = productService.getCartItems();
        BigDecimal totalPrice = productService.calculateTotalPrice();
        List<Currency> currencies = currencyRepository.findAll();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("currencies", currencies);

        return "payment";
    }

    @GetMapping("/new")
    public String showPaymentForm(Model model) {
        model.addAttribute("payment", new Payment());
        return "payment-form";
    }


    @PostMapping("/payment")
    public String processPayment(@RequestParam Long currencyId) {
        productService.setCurrency(currencyId);
        productService.processPayment();

        return "redirect:/payment/success";
    }

    @GetMapping("/payment/success")
    public String getPaymentSuccess() {
        return "payment_success";
    }
}

