package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.AddProductDTO;
import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.repository.CategoryRepository;
import com.spring.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@Transactional
public class ProductService {

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    public ProductService(@Autowired ProductRepository productRepository, @Autowired CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product addProduct(AddProductDTO productDTO, Long categoryId) {
        Product productToBeSaved = new Product();
        Category foundCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the order you want to update was not found"));
        productToBeSaved.setCategory(foundCategory);
        productToBeSaved.setDescription(productDTO.getDescription());
        productToBeSaved.setName(productDTO.getName());
        productToBeSaved.setPrice(productDTO.getPrice());
        return productRepository.save(productToBeSaved);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductsByCategory(Long categoryId) {
        Category foundedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the order you want to update was not found"));
        return productRepository.findAllByCategory(foundedCategory);
    }

    public Product updateProduct(AddProductDTO addProductDTO, Long productId) {
        Product foundedProduct = productRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the product was not found"));
        Category foundCategory = categoryRepository.findById(addProductDTO.getCategoryId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the category was not found"));
        foundedProduct.setName(addProductDTO.getName());
        foundedProduct.setDescription(addProductDTO.getDescription());
        foundedProduct.setPrice(addProductDTO.getPrice());
        foundedProduct.setCategory(foundCategory);
        return productRepository.save(foundedProduct);
    }

    public void deleteProduct(Long productId) {
        Product foundedProduct = productRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the product you want to update was not found"));
        productRepository.delete(foundedProduct);
    }
}