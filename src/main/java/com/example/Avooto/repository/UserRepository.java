package com.example.Avooto.repository;

import com.example.Avooto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByActivationCode(String code);
}
