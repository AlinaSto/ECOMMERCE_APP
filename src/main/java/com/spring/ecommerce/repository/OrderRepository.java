package com.spring.ecommerce.repository;

import com.spring.ecommerce.model.Order;
import com.spring.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findAllByUserOrderByCreatedDateDesc(User user);
}
