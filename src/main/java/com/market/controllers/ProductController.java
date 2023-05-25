package com.market.controllers;

import com.market.Product;
import com.market.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.ProductNotFoundException;

@Controller
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id, Model model) throws ProductNotFoundException {
        productService.getProductById(id).ifPresent(model.addAttribute("product"));
        return "product";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/save")
    public String saveProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/product/" + product.getId();
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) throws ProductNotFoundException {
        productService.getProductById(id).ifPresent(model.addAttribute("product"));
        return "editProduct";
    }

    @PostMapping("/update")
    public String updateProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/product/" + product.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
}
