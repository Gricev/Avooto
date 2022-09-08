package com.example.Avooto.dto;


import com.example.Avooto.model.Image;
import com.example.Avooto.model.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDto {

    private Long id;
    private String title;
    private String description;
    private int price;
    private String city;
    private String category;
    private List<Image> images = new ArrayList<>();
    private Long previewImageId;
    private User user;
    private LocalDateTime dateOfCreated;
}
