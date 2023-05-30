package com.market.controllers;

import com.market.Payment;
import com.market.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/new")
    public String showPaymentForm(Model model) {
        model.addAttribute("payment", new Payment());
        return "payment-form";
    }

    @PostMapping("/save")
    public String savePayment(Payment payment) {
        paymentRepository.save(payment);
        return "redirect:/payment/success";
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "payment-success";
    }
}

