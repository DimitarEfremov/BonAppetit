package com.bonappetit.model.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisterDTO {

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    private String username;

    @Email(message = "Email is not valid")
    @NotEmpty (message = "This field can not be empty")
    private String email;

    @NotEmpty (message = "This field can not be empty")
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters")
    private String password;

    @NotEmpty (message = "This field can not be empty")
    private String confirmPassword;

}
