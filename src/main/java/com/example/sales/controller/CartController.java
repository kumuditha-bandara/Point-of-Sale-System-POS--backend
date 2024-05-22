package com.example.sales.controller;

import com.example.sales.model.CartItem;
import com.example.sales.model.Invoice;
import com.example.sales.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add")
    public CartItem addItemToCart(@RequestParam Integer itemId, @RequestParam int quantity) {
        return cartService.addItemToCart(itemId, quantity);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/checkout")
    public Invoice checkout() {
        return cartService.checkout();
    }
}