package com.spring.ecommerce.service;
//

import com.spring.ecommerce.dto.AddAndDeleteToWishlistDTO;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.model.Wishlist;
import com.spring.ecommerce.model.WishlistItem;
import com.spring.ecommerce.repository.ProductRepository;
import com.spring.ecommerce.repository.UserRepository;
import com.spring.ecommerce.repository.WishlistItemRepository;
import com.spring.ecommerce.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class WishlistItemService {

    private ProductRepository productRepository;
    private UserRepository userRepository;
    private WishlistItemRepository wishlistItemRepository;

    private WishlistRepository wishlistRepository;

    @Autowired
    public WishlistItemService(ProductRepository productRepository, UserRepository userRepository, WishlistRepository wishlistRepository, WishlistItemRepository wishlistItemRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.wishlistItemRepository = wishlistItemRepository;
        this.wishlistRepository = wishlistRepository;
    }

    public Wishlist addItemToWishlist(AddAndDeleteToWishlistDTO addAndDeleteToWishlistDTO) {
        Product foundProduct = productRepository.findById(addAndDeleteToWishlistDTO.getProductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
        User foundUser = userRepository.findById(addAndDeleteToWishlistDTO.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        Wishlist foundWishlist = foundUser.getWishlist();
        foundWishlist.setUser(foundUser);
        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setProduct(foundProduct);
        wishlistItem.setWishlist(foundWishlist);
        foundWishlist.getWishlistItems().add(wishlistItem);
        return wishlistRepository.save(foundWishlist);
    }

    public List<WishlistItem> getAllWishlistItems(Long userId) {
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        return wishlistItemRepository.findWishlistItemByWishlist_User(foundUser);
    }

    public void deleteWishListItemFromWishlistOfUser(AddAndDeleteToWishlistDTO addAndDeleteToWishlistDTO) {
        Product foundedProduct = productRepository.findById(addAndDeleteToWishlistDTO.getProductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
        User foundUser = userRepository.findById(addAndDeleteToWishlistDTO.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        WishlistItem wishlistItemToDelete = wishlistItemRepository.findWishlistItemByWishlist_UserAndProduct(foundUser, foundedProduct); //orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "wishlistItem not found"));
        wishlistItemRepository.delete(wishlistItemToDelete);
        foundUser.getWishlist().getWishlistItems().remove(wishlistItemToDelete);
        wishlistRepository.save(foundUser.getWishlist());
    }

//    public Integer getHowManyUsersHaveTheProductInWishlist(Long productId) {
//        Product foundedProduct = productRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
//        return wishlistItemRepository.findWishlistItemByWishlistIsContainingAndProduct_Id(foundedProduct);
//    }

}