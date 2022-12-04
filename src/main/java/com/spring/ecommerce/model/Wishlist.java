package com.spring.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wishlist {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private int quantity;

    //Un wishlist are mai multe wishlistitems (deci one-to-many intre wishlist si whishlistitem)
    @OneToMany(mappedBy = "wishlist", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    List<WishlistItem> wishlistItems;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    public Wishlist(){}

    public Wishlist(Long id, int quantity, List<WishlistItem> wishlistItems) {
        this.id = id;
        this.quantity = quantity;
        this.wishlistItems = wishlistItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<WishlistItem> getWishlistItems() {
        if(this.wishlistItems == null){
            this.wishlistItems = new ArrayList<>();
        }

        return wishlistItems;
    }

    public void setWishlistItems(List<WishlistItem> wishlistItem) {
        this.wishlistItems = wishlistItem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}