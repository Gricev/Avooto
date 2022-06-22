package com.example.Avooto.dto;

import com.example.Avooto.models.Image;
import com.example.Avooto.models.Product;
import com.example.Avooto.models.Role;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
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