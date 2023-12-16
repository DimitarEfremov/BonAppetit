package com.bonappetit.model.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    private String username;

    @NotEmpty (message = "This field can not be empty")
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters")
    private String password;
}
