package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.AddToCartDTO;
import com.spring.ecommerce.dto.UserCartDTO;
import com.spring.ecommerce.model.CartItem;
import com.spring.ecommerce.model.Order;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.repository.CartItemRepository;
import com.spring.ecommerce.repository.ProductRepository;
import com.spring.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartItemService {

    private ProductRepository productRepository;
    private UserRepository userRepository;
    private CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(ProductRepository productRepository, UserRepository userRepository, CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
    }


    public CartItem addToCart(AddToCartDTO addToCartDTO) {
        Product foundProduct = productRepository.findById(addToCartDTO.getProductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User foundUser = userRepository.findUserByUsername(userDetails.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        CartItem cartItem = new CartItem();
        cartItem.setProduct(foundProduct);
        cartItem.setUser(foundUser);
        cartItem.setQuantity(addToCartDTO.getQuantity());
        return cartItemRepository.save(cartItem);
    }

    public UserCartDTO viewCart(Long userId) {
        List<CartItem> cartItems = cartItemRepository.findAllByUser_Id(userId);
        UserCartDTO userCartDTO = new UserCartDTO();
        userCartDTO.setCartItemList(cartItems);
        userCartDTO.setTotalPrice(computeTotalPrice(cartItems));
        return userCartDTO;
    }

    public Double computeTotalPrice(List<CartItem> cartItems) {
        Optional<Double> totalPrice = cartItems.stream()
                .map(cartItem -> cartItem.getQuantity() * cartItem.getProduct().getPrice())
                .reduce(Double::sum);
                //reduce(Double::sum);

        return totalPrice.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "total price could not be calculated"));
    }

    public CartItem updateCartItem(AddToCartDTO addToCartDTO, Long cartItemId) {
        CartItem foundCartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the cart item you want to update does not exist"));
        Product foundProduct = productRepository.findById(addToCartDTO.getProductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User foundUser = userRepository.findUserByUsername(userDetails.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        foundCartItem.setQuantity(addToCartDTO.getQuantity());
        foundCartItem.setProduct(foundProduct);
        foundCartItem.setUser(foundUser);
        return cartItemRepository.save(foundCartItem);
    }

    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the cart item you want to delete does not exist"));
        cartItemRepository.delete(cartItem);
    }

    public void deleteAllUserCartItems(Long userId) {
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        cartItemRepository.deleteByUser(foundUser);
    }


}

