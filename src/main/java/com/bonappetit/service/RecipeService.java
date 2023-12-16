package com.bonappetit.service;

import com.bonappetit.model.DTOs.AddRecipeDTO;
import com.bonappetit.model.DTOs.HomeDTO;

public interface RecipeService {

    void add(AddRecipeDTO addRecipeDTO);

    HomeDTO getAllRecipes();

}
