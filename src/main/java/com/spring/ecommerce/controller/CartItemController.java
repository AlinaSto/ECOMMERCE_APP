package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.AddToCartDTO;
import com.spring.ecommerce.dto.UserCartDTO;
import com.spring.ecommerce.model.CartItem;
import com.spring.ecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartItemController {

    private CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/add")
    public CartItem addToCart(@RequestBody AddToCartDTO addToCartDTO){
        return cartItemService.addToCart(addToCartDTO);
    }
    @GetMapping("/{userId}")
    public UserCartDTO viewCart(@PathVariable Long userId){
        return cartItemService.viewCart(userId);
    }
    @PutMapping("/update/{cartItemId}")
    public CartItem updateCartItem( @RequestBody AddToCartDTO addToCartDTO,@PathVariable Long cartItemId){
        return cartItemService.updateCartItem(addToCartDTO,cartItemId);
    }
    @DeleteMapping("/delete/{cartItemId}")
    public void deleteCartItem(@PathVariable Long cartItemId){
        cartItemService.deleteCartItem(cartItemId);
    }

    @DeleteMapping("/delete/user/{userId}")
    public void deleteUserCartItems(@PathVariable Long userId){
        cartItemService.deleteAllUserCartItems(userId);
    }



}