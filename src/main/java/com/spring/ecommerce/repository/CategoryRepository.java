package com.spring.ecommerce.repository;

import com.spring.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

     Category findByName(String name);

    void deleteCategoryById(Category category);
}
