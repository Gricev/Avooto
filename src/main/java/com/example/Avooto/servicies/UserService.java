package com.example.Avooto.servicies;

import com.example.Avooto.dto.UserDto;
import com.example.Avooto.models.Image;
import com.example.Avooto.models.PrincipalUser;
import com.example.Avooto.models.Role;
import com.example.Avooto.models.User;
import com.example.Avooto.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private EmailServiceImpl mailService;

    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) {
            return false;
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        user.setActivationCode(UUID.randomUUID().toString());
        log.info("Saving new User with email: {}", email);
        userRepository.save(user);
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Avooto, please, visit next link: http://localhost:8112/activate/%s",
                    user.getEmail(),
                    user.getActivationCode()
            );
            mailService.sendSimpleMessage(user.getEmail(), "Activation code", message);
        }
        return true;
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}", user.getId(), user.getEmail());
            }
        }
        userRepository.save(user);
    }

    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public User getUserByPrincipaly(PrincipalUser principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getUsername());
    }


    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);

        userRepository.save(user);
        return true;
    }

    public void changeUserInfo(Principal principal, UserDto userBeforeUpdate, MultipartFile file) throws IOException {
        User userAfterUpdate = getUserByPrincipal(principal);
        Image avatar;
        if (file.getSize() != 0) {
            avatar = toImageEntity(file);
            avatar.setPreviewImage(true);
            userAfterUpdate.addAvatarToUser(avatar);
        }
        userAfterUpdate.setEmail(userBeforeUpdate.getEmail());
        userAfterUpdate.setName(userBeforeUpdate.getName());
        userAfterUpdate.setPassword(passwordEncoder.encode(userBeforeUpdate.getPassword()));
        userAfterUpdate.setPhoneNumber(userBeforeUpdate.getPhoneNumber());
        userRepository.save(userAfterUpdate);
        log.info("Saving changes in Repo. Email: {}; password: {}; name: {}; phoneNumber: {};",
                userAfterUpdate.getEmail(), passwordEncoder.encode(userAfterUpdate.getPassword()),
                userAfterUpdate.getName(), userAfterUpdate.getPhoneNumber());
        userAfterUpdate.setPreviewImageId(userAfterUpdate.getAvatars().get(0).getId());
        userRepository.save(userAfterUpdate);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
}



