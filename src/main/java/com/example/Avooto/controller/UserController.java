package com.example.Avooto.controller;

import com.example.Avooto.dto.UserDto;
import com.example.Avooto.model.Product;
import com.example.Avooto.model.User;
import com.example.Avooto.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "userEditProfile";
    }

    @GetMapping("/profile/edit/username")
    public String editUserProfileUserName(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("username", userService.getUserByPrincipal(principal).getName());
        return "userEditProfileUserName";
    }

    @PostMapping("/profile/edit/username")
    public String editUserProfileUserName(Principal principal, UserDto user) {
        userService.changeUserName(principal, user);
        return "redirect:/profile";
    }

    @GetMapping("/profile/edit/phone")
    public String editUserProfilePhoneNumber(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("phone", userService.getUserByPrincipal(principal).getPhoneNumber());
        return "userEditProfilePhoneNumber";
    }

    @PostMapping("/profile/edit/phone")
    public String editUserProfilePhoneNumber(Principal principal, UserDto user) {
        userService.changeUserPhone(principal, user);
        return "redirect:/profile";
    }

    @GetMapping("/profile/edit/password")
    public String editUserProfilePassword(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "userEditProfilePassword";
    }

    @PostMapping("/profile/edit/password")
    public String editUserProfilePassword(@RequestParam(name = "pass") String password,
                                          @RequestParam(name = "passRep") String passwordRepeat,
                                          Principal principal, UserDto user) {
        userService.changeUserPassword(principal, user, password, passwordRepeat);
            return "redirect:/profile";
        }

    @GetMapping("/profile/edit/avatar/delete")
    public String editUserProfileAvatar(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "userEditProfileAvatarDelete";
    }

    @GetMapping("/profile/edit/avatar/change")
    public String deleteUserProfileAvatar(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
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

    @GetMapping("/profile/edit/delete")
    public String deleteUserGet(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "userDelete";
    }

    @PostMapping("/profile/edit/delete")
    public String deleteUserPost(Principal principal,
                                 @RequestParam(name = "email") String email,
                                 @RequestParam(name = "password") String password) {
        userService.deleteUser(principal, email, password);
        return "redirect:/";
    }

    @GetMapping("/forgetPassword")
    public String forgetPasswordGet(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "forgetPassword";
    }

    @PostMapping("/forgetPassword")
    public String forgetPasswordPost(Principal principal,
                                     @RequestParam(name = "username") String email, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        if (!userService.sendUserMail(email)) {
            model.addAttribute("errorMessage", "Указанный электронный адрес неверный, либо не существует");
            return "forgetPassword";
        }
        return "redirect:/forgetPasswordNumb";
    }

    @GetMapping("/forgetPasswordNumb")
    public String forgetPasswordNumbGet(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "forgetPasswordNumb";
    }

    @PostMapping("/forgetPasswordNumb")
    public String forgetPasswordNumbPost(Principal principal,
                                         @RequestParam(name = "forgetPasswordNumber") Integer forgetPassNum,
                                         @RequestParam(name = "password") String password,
                                         @RequestParam(name = "passwordRepeat") String passwordRepeat,
                                         Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        if (!userService.userMailNumbCompare(forgetPassNum, password, passwordRepeat)) {
            model.addAttribute("errorMessage", "Неверный код доступа, либо пароли не совпадают");
            return "forgetPasswordNumb";
        }
        return "redirect:/login";
    }
}
