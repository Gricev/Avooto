package com.example.Avooto.service;

import com.example.Avooto.dto.UserDto;
import com.example.Avooto.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface UserService {

    boolean createUser(User user);
    void toBanUser(Long id);
    void changeUserRoles(User user, Map<String, String> form);
    User getUserByPrincipal(Principal principal);
    boolean activateUser(String code);
    void changeUserName(Principal principal, UserDto userBeforeUpdate);
    void changeUserPhone(Principal principal, UserDto userBeforeUpdate);
//    void changeUserPassword(Principal principal, UserDto userBeforeUpdate, String password);
    boolean changeUserPassword(Principal principal, UserDto userBeforeUpdate, String password, String passwordRepeat);
    void changeUserAvatar(Principal principal, MultipartFile file) throws IOException;
    void deleteAvatar(Principal principal);
    boolean deleteUser(Principal principal, String email, String password);
    List<User> findUserById(Long id);
    void deleteUserFromAdminPanel(Long id);
}
