package com.market.controllers;

import com.market.Currency;
import com.market.CurrencyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CurrencyController {
    private final CurrencyRepository currencyRepository;

    public CurrencyController(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @GetMapping("/currencies")
    public String getCurrencies(Model model) {
        List<Currency> currencies = currencyRepository.findAll();
        model.addAttribute("currencies", currencies);
        return "currencies";
    }

}
