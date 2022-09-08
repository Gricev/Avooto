package com.example.Avooto.repository;

import com.example.Avooto.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitle(String title);
    List<Product> findByCategory(String category);
    List<Product> findByCity(String city);
    List<Product> findByPrice(int price);
}
