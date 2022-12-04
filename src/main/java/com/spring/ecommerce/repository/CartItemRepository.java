package com.spring.ecommerce.repository;

import com.spring.ecommerce.model.CartItem;
import com.spring.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


    @Repository
    public interface CartItemRepository extends JpaRepository<CartItem,Long> {
        List<CartItem> findAllByUser(User user);

        List<CartItem> findAllByUser_Id(Long userId);

        void deleteByUser(User user);
        void deleteAllByUser_Id(Long userId);
//    @Modifying
//    @Query("delete from CartItem c where c.user=:user")
//    void deleteAllOfUser(@Param("user") User user);


    }

