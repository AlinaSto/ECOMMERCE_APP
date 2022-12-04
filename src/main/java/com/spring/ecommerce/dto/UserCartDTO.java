package com.spring.ecommerce.dto;

import com.spring.ecommerce.model.CartItem;

import java.util.List;

public class UserCartDTO {

    public UserCartDTO(List<CartItem> cartItemList, Double totalPrice) {
        this.cartItemList = cartItemList;
        this.totalPrice = totalPrice;
    }

    private List<CartItem> cartItemList;
    private Double totalPrice;

    public UserCartDTO() {
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}