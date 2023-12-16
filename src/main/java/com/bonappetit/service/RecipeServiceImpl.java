package com.bonappetit.service;

import com.bonappetit.model.DTOs.AddRecipeDTO;
import com.bonappetit.model.DTOs.HomeDTO;
import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.repo.CategoryRepository;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

private final RecipeRepository recipeRepository;
private final UserRepository userRepository;
private final CategoryRepository categoryRepository;
private final LoggedUser loggedUser;

    public RecipeServiceImpl(RecipeRepository recipeRepository, UserRepository userRepository, CategoryRepository categoryRepository, LoggedUser loggedUser) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.loggedUser = loggedUser;
    }

    @Override
    public void add(AddRecipeDTO addRecipeDTO) {

        String categoryName = addRecipeDTO.getCategory();
        List<Category> categoryList = categoryRepository.findAll();
        Category category = new Category();

        for (Category categoryDB : categoryList) {
            if(categoryDB.getCategoryName().name().equals(categoryName)){
                category = categoryDB;
            }
        }

        User user = userRepository.findUserById(loggedUser.getId());

        if (category != null && user != null){
            Recipe recipe = new Recipe();
            recipe.setName(addRecipeDTO.getName());
            recipe.setIngredients(addRecipeDTO.getIngredients());
            recipe.setCategory(category);
            recipe.setAddedBy(user);

            recipeRepository.save(recipe);
        }

    }

    @Override
    public HomeDTO getAllRecipes() {
        List<Recipe> allRecipes = recipeRepository.findAll();

        List<Recipe> mainDishes = new ArrayList<>();
        List<Recipe> cocktails = new ArrayList<>();
        List<Recipe> desserts = new ArrayList<>();
        List<Recipe> favourites = new ArrayList<>();

        for (Recipe recipe : allRecipes) {

            String category = recipe.getCategory().getCategoryName().name();

            switch (category){
                 case "MAIN_DISH" -> mainDishes.add(recipe);
                case "COCKTAIL" -> cocktails.add(recipe);
                case "DESSERT" -> desserts.add(recipe);
            }

            List<User> favouredBy = recipe.getFavouredBy();

            for (User user : favouredBy) {
                if (user.getId().equals(loggedUser.getId()))
                    favourites.add(recipe);
            }
        }

        return new HomeDTO(desserts,mainDishes,cocktails,favourites);
    }
}
