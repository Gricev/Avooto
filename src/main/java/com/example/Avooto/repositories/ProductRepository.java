package com.example.Avooto.repositories;

import com.example.Avooto.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.util.List;
@Repository

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitle(String title);
    List<Product> findByCategory(String category);
    List<Product> findByCity(String city);
    List<Product> findByPrice(int price);
//    List<Product> findBy(ResultSet title);
}
