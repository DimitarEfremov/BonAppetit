package com.bonappetit.controller;

import com.bonappetit.model.DTOs.AddRecipeDTO;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.service.RecipeService;
import com.bonappetit.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecipeController {

    private final LoggedUser loggedUser;
    private final RecipeService recipeService;

    public RecipeController(LoggedUser loggedUser, RecipeService recipeService) {
        this.loggedUser = loggedUser;

        this.recipeService = recipeService;
    }

    @GetMapping("/recipes/add")
    public ModelAndView addRecipe(
            @ModelAttribute("addRecipeDTO")AddRecipeDTO addRecipeDTO){

        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("recipe-add");
    }

    @PostMapping("/recipes/add")
    public ModelAndView addRecipe(
            @ModelAttribute("addRecipeDTO") @Valid AddRecipeDTO addRecipeDTO,
            BindingResult bindingResult) {

        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("recipe-add");
        }

        recipeService.add(addRecipeDTO);

            return new ModelAndView("redirect:/home");
    }





}
