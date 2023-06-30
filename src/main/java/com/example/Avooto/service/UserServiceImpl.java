package com.example.Avooto.service;

import com.example.Avooto.dto.UserDto;
import com.example.Avooto.exception.BanWordsException;
import com.example.Avooto.exception.EntityNotFoundException;
import com.example.Avooto.model.Image;
import com.example.Avooto.model.Product;
import com.example.Avooto.model.Role;
import com.example.Avooto.model.User;
import com.example.Avooto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductService productService;
    private final EmailServiceImpl mailService;

    @Override
    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) {
            return false;
        }
        if (hasBadWords(user.getName())) {
            throw new BanWordsException("Нецензурная лексика на данной площадке не используется");
        }
        if (hasRightPhoneNumber(user.getPhoneNumber())) {
            int passwordNumDefault = 1234;
            user.setForgetPasswordNumb(passwordNumDefault);
            user.setActive(true);
            user.setPassword(user.getPassword());
            user.getRoles().add(Role.ROLE_USER);
            user.setActivationCode(UUID.randomUUID().toString());
            log.info("Saving new User with email: {}", email);
            userRepository.save(user);
            if (!StringUtils.isEmpty(user.getEmail())) {
                String message = String.format(
                        "Здравствуйте, %s! \n" +
                                "Добро пожаловать на AVOOTO, для подтверждения аккаунта на сайе, нажмите: " +
                                "http://localhost:8112/activate/%s" +
                                " your password: " + user.getPassword(),
                        user.getName(),
                        user.getActivationCode()
                );
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
                mailService.sendSimpleMessage(user.getEmail(), "Активация аккаунта AVOOTO", message);
            }
            return true;
        } else {
            throw new BanWordsException("Телефонный номер введен некорректно");
        }
    }

    @Override
    public void toBanUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Пользователь не найден с id " + id));
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}", user.getId(), user.getEmail());
            }
            userRepository.save(user);
        }
    }


    @Override
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

    @Override
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    @Override
    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        userRepository.save(user);
        return true;
    }

    @Override
    public void changeUserName(Principal principal, UserDto userBeforeUpdate) {
        User userAfterUpdate = getUserByPrincipal(principal);
        userAfterUpdate.setName(userBeforeUpdate.getName());
        if (hasBadWords(userAfterUpdate.getName())) {
            throw new BanWordsException("Нецензурная лексика на данной площадке запрещена");
        }
        log.info("Saving changes in Repo. Username: {}; ", userAfterUpdate.getName());
        userRepository.save(userAfterUpdate);
    }

    @Override
    public void changeUserPhone(Principal principal, UserDto userBeforeUpdate) {
        User userAfterUpdate = getUserByPrincipal(principal);
        userAfterUpdate.setPhoneNumber(userBeforeUpdate.getPhoneNumber());
        if (hasRightPhoneNumber(userAfterUpdate.getPhoneNumber())) {
        userAfterUpdate.setActivationCode(UUID.randomUUID().toString());
            if (!StringUtils.isEmpty(userAfterUpdate.getEmail())) {
            String message = String.format(
                    "Здравствуйте, %s! \n" +
                            "Ваш телефон был успешно изменен, если это были не Вы, перейдите по ссылке и " +
                            "измените пароль: http://localhost:8112/activate/%s" +
                            " Ваш новый телефон: " + userAfterUpdate.getPhoneNumber(),
                    userAfterUpdate.getName(),
                    userAfterUpdate.getActivationCode()
            );
            log.info("Saving changes in Repo. Phone-number: {}; ", userAfterUpdate.getPhoneNumber());
            userRepository.save(userAfterUpdate);
            mailService.sendSimpleMessage(userAfterUpdate.getEmail(), "Изменение номера телефона AVOOTO", message);
            }
        } else {
            throw new BanWordsException("ну нееееет");
        }
    }

    @Override
    public boolean changeUserPassword(Principal principal, UserDto userBeforeUpdate, String password, String passwordRepeat) {
        User userAfterUpdate = getUserByPrincipal(principal);
        if (passwordEncoder.matches(password, userAfterUpdate.getPassword())) {
            userAfterUpdate.setPassword(userBeforeUpdate.getPassword());
            if (userAfterUpdate.getPassword().equals(passwordRepeat)) {
                userAfterUpdate.setActivationCode(UUID.randomUUID().toString());
                if (!StringUtils.isEmpty(userAfterUpdate.getEmail())) {
                    String message = String.format(
                            "Здравствуйте, %s! \n" +
                                    "Ваш пароль был успешно изменен, для активации аккаунта, нажмите:" +
                                    " http://localhost:8112/activate/%s" +
                                    " Ваш новый пароль: " + userAfterUpdate.getPassword(),
                            userAfterUpdate.getName(),
                            userAfterUpdate.getActivationCode()
                    );
                    userAfterUpdate.setPassword(passwordEncoder.encode(userBeforeUpdate.getPassword()));
                    log.info("Saving changes in Repo. Password: {}; ", passwordEncoder.encode(userAfterUpdate.getPassword()));
                    userRepository.save(userAfterUpdate);
                    mailService.sendSimpleMessage(userAfterUpdate.getEmail(), "Изменение пароля AVOOTO", message);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void changeUserAvatar(Principal principal, MultipartFile file) throws IOException {
        User userAfterUpdate = getUserByPrincipal(principal);
        userAfterUpdate.getAvatars().clear();
        if (file.getSize() != 0) {
            Image avatar = toImageEntity(file);
            avatar.setPreviewImage(true);
            userAfterUpdate.addAvatarToUser(avatar);
            userRepository.save(userAfterUpdate);
            userAfterUpdate.setPreviewImageId(userAfterUpdate.getAvatars().get(0).getId());
            userRepository.save(userAfterUpdate);
        }
    }

    @Override
    public void deleteAvatar(Principal principal) {
        User user = getUserByPrincipal(principal);
        user.getAvatars().clear();
        user.setPreviewImageId(null);
        userRepository.save(user);
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

    @Override
    public boolean deleteUser(Principal principal, String email, String password) {
        User user = getUserByPrincipal(principal);
        if (email.equals(user.getEmail()) && passwordEncoder.matches(password, user.getPassword())) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    @Override
    public List<User> findUserById(Long id) {
        if (id != null)
            return Collections.singletonList(userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                    "Пользователь не найден с id " + id)));
        return userRepository.findAll();
    }

    @Override
    public void deleteUserFromAdminPanel(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean sendUserMail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        } else {
            Random random = new Random();
            int rage = 9999;
            int generator = random.nextInt(rage);
            user.setForgetPasswordNumb(generator);
            userRepository.save(user);
            if (!StringUtils.isEmpty(user.getEmail())) {
                String message = String.format(
                        "Здравствуйте, %s! \n" +
                                "Для изменения пароля - введите код : " +
                                user.getForgetPasswordNumb(),
                        user.getName());
                mailService.sendSimpleMessage(user.getEmail(), "Код восстановления доступа", message);
            }
        }
        return true;
    }

    @Override
    public boolean userMailNumbCompare(Integer forgetPasswordNumber, String password, String passwordRepeat) {
        User user = userRepository.findByForgetPasswordNumb(forgetPasswordNumber);
        if (user == null | !password.equals(passwordRepeat)) {
            return false;
        } else {
            user.setPassword(password);
            user.setActivationCode(UUID.randomUUID().toString());
            userRepository.save(user);
            if (!StringUtils.isEmpty(user.getEmail())) {
                String message = String.format(
                        "Здравствуйте, %s! \n" +
                                "Ваш пароль был успешно изменен, для активации аккаунта, нажмите:" +
                                " http://localhost:8112/activate/%s" +
                                " Ваш новый пароль: " + user.getPassword(),
                        user.getName(),
                        user.getActivationCode());
                mailService.sendSimpleMessage(user.getEmail(), "Изменение пароля AVOOTO", message);
                user.setPassword(passwordEncoder.encode(password));
                log.info("Saving changes in Repo. Password: {}; ", passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
            }
        }
        return true;
    }

    @Override
    public void addProductToFavorite(Principal principal, Long id) {
        List<Product> products = getFavoriteListFromService(principal);
        User user = getUserByPrincipal(principal);
        Product product = productService.getProductById(id);
        products.add(product);
        userRepository.save(user);
    }

    @Override
    public List<Product> getProductListFavorite(Principal principal) {
        User user = getUserByPrincipal(principal);
        return user.getFavoriteProducts();
    }

    @Override
    public void deleteProductFromListFavorite(Principal principal, Long id) {
        Product product = productService.getProductById(id);
        User user = getUserByPrincipal(principal);
        user.getFavoriteProducts().remove(product);
        userRepository.save(user);
    }

    private static boolean hasBadWords(String title) {
        String titleInLowerCase = title.toLowerCase();
        return Pattern.matches(".*ху[йеё].*", titleInLowerCase) ||
                Pattern.matches(".*пизд.*", titleInLowerCase) ||
                Pattern.matches(".*ебан.*", titleInLowerCase) ||
                Pattern.matches(".*ебл.*", titleInLowerCase) ||
                Pattern.matches("пид.*", titleInLowerCase) ||
                Pattern.matches(".*бляд.*", titleInLowerCase);
    }

    private static boolean hasRightPhoneNumber(String phoneNumber) {
        return Pattern.matches("^\\d{11}$", phoneNumber);
    }

    private List<Product> getFavoriteListFromService(Principal principal) {
        User user = getUserByPrincipal(principal);
        List<Product> products = new ArrayList<>();
        user.setFavoriteProducts(products);
        userRepository.save(user);
        return products;
    }
}