package com.bonappetit.model.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddRecipeDTO {

    @NotEmpty
    @Size(min = 2, max = 40, message = "name must be between 2 and 40 characters")
    private String name;

    @NotEmpty
    @Size(min = 2, max = 40, message = "ingredients length must be between 2 and 40 characters")
    private String ingredients;

    @NotEmpty(message = "You must select a category")
    private String category;

}
