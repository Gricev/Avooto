package com.example.Avooto.repositories;

import com.example.Avooto.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.Principal;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByActivationCode(String code);


}
