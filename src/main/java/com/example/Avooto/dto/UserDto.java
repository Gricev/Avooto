package com.example.Avooto.dto;

import com.example.Avooto.model.Image;
import com.example.Avooto.model.Product;
import com.example.Avooto.model.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserDto {

    private String email;
    private String phoneNumber;
    private String name;
    private boolean active;
    private Image avatar;
    private String password;
    private String activationCode;
    private Set<Role> roles = new HashSet<>();
    private List<Product> products = new ArrayList<>();
}