
//
//    @PostMapping("/create")
//    public Category createCategory(@RequestBody Category category) {
//        //pasul 1: exista categoria in baza de date?
//        //pasul 2.1: daca nu exista o salvam
//        //pasul 2.2: daca exista aruncam exceptie
//        return categoryService.createCategory(category);
//    }
package com.spring.ecommerce.controller;

import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(@Autowired CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping("/")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @PutMapping("/update/{categoryId}")
    public Category updateCategory(@RequestBody Category category, @PathVariable Long categoryId) {
        return categoryService.updateCategory(category, categoryId);
    }

    @DeleteMapping("/delete/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }


}