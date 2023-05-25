package com.market;

import exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import exceptions.ProductNotFoundException;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "com.market")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Controller
    public class HomeController {

        @GetMapping("/")
        public String home() {
            return "index";
        }
    }

    @Controller
    public class ProductController {

        @Autowired
        private ProductService productService;

        @GetMapping("/products")
        public String getAllProducts(Model model) {
            List<Product> products = productService.getAllProducts();
            model.addAttribute("products", products);
            return "product";
        }

        @GetMapping("/product/{id}")
        public String getProductById(@PathVariable(value = "id") Long id, Model model) throws ProductNotFoundException {
            Product product = productService.getProductById(id);
            model.addAttribute("product", product);
            return "product_form";
        }
    }

    @Controller
    public class AuthenticationController {

        @Autowired
        private UserService userService;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @GetMapping("/login")
        public String login() {
            return "login";
        }

        @GetMapping("/register")
        public String register(Model model) {
            model.addAttribute("user", new User());
            return "register";
        }

        @PostMapping("/register")
        public String registerUser(@ModelAttribute("user") User user, Model model) {
            try {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userService.save(user);
                return "redirect:/login";
            } catch (UserAlreadyExistsException e) {
                model.addAttribute("error", "com.market.User with email " + user.getEmail() + " already exists!");
                return "register";
            }
        }
    }

}
