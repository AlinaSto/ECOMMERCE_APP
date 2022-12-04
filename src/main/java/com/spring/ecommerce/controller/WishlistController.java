package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.AddAndDeleteToWishlistDTO;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.Wishlist;

import com.spring.ecommerce.model.WishlistItem;
import com.spring.ecommerce.service.WishlistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private WishlistItemService wishlistItemService;

    @Autowired
    public WishlistController(WishlistItemService wishlistItemService) {
        this.wishlistItemService = wishlistItemService;
    }

    @PostMapping("/add")
    public Wishlist addToWishlist(@RequestBody AddAndDeleteToWishlistDTO addAndDeleteToWishlistDTO) {
        return wishlistItemService.addItemToWishlist(addAndDeleteToWishlistDTO);
    }


//    @GetMapping("/{userId}")
//    public List<Product> getAllProductsByUser(@PathVariable Long userId) {
//        return wishlistItemService.getAllProductsByUser(userId);
//    }

    // Vedem produsele din wishlist-ul unui utilizator
    //Endpoint: /wishlist/{usedId}
    @GetMapping("/{userId}")
    public List<WishlistItem> getAllWishlistItem(@PathVariable Long userId) {
        return wishlistItemService.getAllWishlistItems(userId);
    }

    @DeleteMapping("/delete")
    public void deleteProductFromWishlist(@RequestBody AddAndDeleteToWishlistDTO addAndDeleteToWishlistDTO) {
        wishlistItemService.deleteWishListItemFromWishlistOfUser(addAndDeleteToWishlistDTO);
    }

//    @GetMapping("/how users/{productId}")
//    public Integer getHowManyUsersHaveTheProductInWishlist(@PathVariable Long productId) {
//        return wishlistItemService.getHowManyUsersHaveTheProductInWishlist(productId);
//    }


}