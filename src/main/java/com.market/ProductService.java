package com.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

        private CartService cartService;

        @Autowired
        public void setCartService(CartService cartService) {
            this.cartService = cartService;
        }

    private final ProductRepository productRepository;
    private final CurrencyRepository currencyRepository;
    private final CartItemRepository cartItemRepository;


    public ProductService(ProductRepository productRepository, CurrencyRepository currencyRepository, CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.currencyRepository = currencyRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
    public BigDecimal convertCurrency(BigDecimal amount, Currency fromCurrency, Currency toCurrency) {
        BigDecimal exchangeRate = toCurrency.getExchangeRate().divide(fromCurrency.getExchangeRate(), RoundingMode.HALF_UP);
        return amount.multiply(exchangeRate);
    }

    public void addToCart(Long productId) {
    }

    public List<Product> getCartItems() {
            List<Long> cartItemIds = cartService.getCartItemIds();
            List<Product> cartItems = productRepository.findByIdIn(cartItemIds);
            return cartItems;
    }

    public BigDecimal calculateTotalPrice() {
            List<Product> products = productRepository.findAll();
            BigDecimal totalPrice = BigDecimal.ZERO;

            for (Product product : products) {
                BigDecimal itemPrice = product.getPrice();
                totalPrice = totalPrice.add(itemPrice);
            }

            return totalPrice;


    }

    public void setCurrency(Long currencyId) {
    }

    public void processPayment() {
    }


    public List<Product> getProductsByIds(List<Long> productIds) {
        return productRepository.findAllById(productIds);
    }


    }

