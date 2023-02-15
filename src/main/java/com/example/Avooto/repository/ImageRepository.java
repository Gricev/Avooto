package com.example.Avooto.repository;


import com.example.Avooto.model.Image;
import com.example.Avooto.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
//    Image findByProductIdAndPreviewImageIsTrue(Long id);
}
