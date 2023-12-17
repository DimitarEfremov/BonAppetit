package com.bonappetit.controller;

import com.bonappetit.model.DTOs.HomeDTO;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import com.bonappetit.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private final LoggedUser loggedUser;
    private final RecipeService recipeService;
    private final UserService userService;

    public HomeController(LoggedUser loggedUser, RecipeService recipeService, UserService userService) {
        this.loggedUser = loggedUser;
        this.recipeService = recipeService;

        this.userService = userService;
    }


    @GetMapping("/")
    public ModelAndView index(){

        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }


    return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home(){

        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }


        HomeDTO allRecipes = recipeService.getAllRecipes();

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("recipes", allRecipes);
        modelAndView.addObject("username", loggedUser.getUsername());

        return modelAndView;
    }

    @PostMapping("/home/add-favourite/{id}")
    public ModelAndView addFavourite(@PathVariable("id") Long id){

        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }



        userService.addFavourite(id);



        return new ModelAndView("redirect:/home");
    }
}
