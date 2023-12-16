package com.bonappetit.service;

import com.bonappetit.model.DTOs.UserLoginDTO;
import com.bonappetit.model.DTOs.UserRegisterDTO;

public interface UserService {

    boolean register(UserRegisterDTO userRegisterDTO);

    boolean login(UserLoginDTO userLoginDTO);

    void logout();

    void addFavourite(Long id);
}
