package com.example.Avooto.repository;

import com.example.Avooto.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.security.Principal;
import java.util.List;
@Repository

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitleContainsAndCityAndCategoryOrderByDateOfCreatedDesc(String title, String city, String category);
    List<Product> findByTitleContainsAndCityAndCategoryAndPriceBetweenOrderByDateOfCreatedDesc(String title, String city, String category,
                                                                       Integer minPrice, Integer maxPrice);
    List<Product> findByCityAndCategoryAndPriceBetween(String city, String category, Integer minPrice, Integer maxPrice);
    List<Product> findByCityAndCategory(String city, String category);
    List<Product> findByCategory(String category);
    List<Product> findByCity(String city);
    List<Product> findAllByOrderByDateOfCreatedDesc();
}
