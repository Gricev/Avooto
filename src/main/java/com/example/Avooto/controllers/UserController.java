package com.example.Avooto.controllers;

import com.example.Avooto.dto.UserDto;
import com.example.Avooto.models.User;
import com.example.Avooto.servicies.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/registration")
    public String registration(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "registration";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "Activated code is not found");
        }
        return "redirect:/login";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " +
                    user.getEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("user", user);
        model.addAttribute("userByPrincipal", userService.getUserByPrincipal(principal));
        model.addAttribute("products", user.getProducts());
        return "user-info";
    }

    @GetMapping("/profile/edit")
    public String editUserInfo(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "userEditProfile";
    }

    @GetMapping("/profile/edit/username")
    public String editUserProfileUserName(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("username", user.getName());
        return "userEditProfileUserName";
    }

    @PostMapping("/profile/edit/username")
    public String editUserProfileUserName(Principal principal, UserDto user) {
        userService.changeUserName(principal, user);
        return "redirect:/profile";
    }

    @GetMapping("/profile/edit/phone")
    public String editUserProfilePhoneNumber(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("phone", user.getPhoneNumber());
        return "userEditProfilePhoneNumber";
    }

    @PostMapping("/profile/edit/phone")
    public String editUserProfilePhoneNumber(Principal principal, UserDto user) {
        userService.changeUserPhone(principal, user);
        return "redirect:/profile";
    }

    @GetMapping("/profile/edit/password")
    public String editUserProfilePassword(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "userEditProfilePassword";
    }

    @PostMapping("/profile/edit/password")
    public String editUserProfilePassword(Principal principal, UserDto user) {
        userService.changeUserPassword(principal, user);
        return "redirect:/profile";
    }

    @GetMapping("/profile/edit/avatar/delete")
    public String editUserProfileAvatar(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "userEditProfileAvatarDelete";
    }

    @GetMapping("/profile/edit/avatar/change")
    public String deleteUserProfileAvatar(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "userEditProfileAvatar";
    }

    @PostMapping("/profile/edit/avatar/change")
    public String editUserProfileAvatar(Principal principal, MultipartFile file) throws IOException {
        userService.changeUserAvatar(principal, file);
        return "redirect:/profile";
    }

    @PostMapping("/profile/edit/avatar/delete")
    public String deleteUserProfileAvatar(Principal principal) {
        userService.deleteAvatar(principal);
        return "redirect:/profile";
    }
}
