package com.bonappetit.service;

import com.bonappetit.model.DTOs.UserLoginDTO;
import com.bonappetit.model.DTOs.UserRegisterDTO;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.util.LoggedUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final PasswordEncoder passwordEncoder;
    private final RecipeRepository recipeRepository;

    public UserServiceImpl(UserRepository userRepository, LoggedUser loggedUser, PasswordEncoder passwordEncoder, RecipeRepository recipeRepository) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.passwordEncoder = passwordEncoder;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public boolean register(UserRegisterDTO userRegisterDTO) {

        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }

        boolean usernameOrPassExist = userRepository.existsByUserNameAndEmail(userRegisterDTO.getUsername(), userRegisterDTO.getPassword());

        if (usernameOrPassExist) {
            return false;
        }

        User user = new User();

        user.setUserName(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        userRepository.save(user);
        return true;
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        User user = userRepository.findByUserName(username);

        if (user != null && passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            loggedUser.login(user);
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        loggedUser.logout();
    }

    @Override
    public void addFavourite(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        User user = userRepository.findUserById(loggedUser.getId());
        user.addFavourite(recipe);
        userRepository.save(user);

    }
}
