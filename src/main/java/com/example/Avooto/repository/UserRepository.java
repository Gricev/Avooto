package com.example.Avooto.repository;

import com.example.Avooto.dto.UserDto;
import com.example.Avooto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByEmailAndActivationCodeIsNull(String email) throws InternalAuthenticationServiceException;
    User findByActivationCode(String code);
//    void deleteById(Long id);
}
