package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.AddProductDTO;
import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    public ProductController(@Autowired ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/create")
    public Product addProduct(@RequestBody AddProductDTO productDTO) {
        Long categoryId = productDTO.getCategoryId();
        return productService.addProduct(productDTO, categoryId);
    }

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{categoryId}")
    public List<Product> getAllProductsByCategory(@PathVariable Long categoryId) {
        return productService.getAllProductsByCategory(categoryId);
    }

    @PutMapping("/update/{productId}")
    public Product updateProduct(@RequestBody AddProductDTO addProductDTO, @PathVariable Long productId) {
        return productService.updateProduct(addProductDTO, productId);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProductFromCategory(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }



}