package com.spring.ecommerce.repository;

import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long > {
    WishlistItem findWishlistItemByWishlist_UserAndProduct(User user, Product product);

    List<WishlistItem> findWishlistItemByWishlist_User(User user);

   //  Integer findWishlistItemByWishlistContainingProduct_Id(Product product);
}
