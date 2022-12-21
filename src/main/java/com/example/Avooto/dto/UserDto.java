package com.example.Avooto.dto;

import com.example.Avooto.model.Image;
import com.example.Avooto.model.Product;
import com.example.Avooto.model.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.*;

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
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date dateOfCreated;
    private String forgetPasswordNumb;
}