package com.spring.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class WishlistItem {
    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="wishlist_id")
    private Wishlist wishlist;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="product_id")
    private Product product;

    public WishlistItem(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Wishlist getWishlist() {
        if(this.wishlist == null){
            wishlist = new Wishlist();
        }
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}