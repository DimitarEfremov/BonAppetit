package com.bonappetit.model.DTOs;

import com.bonappetit.model.entity.Recipe;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HomeDTO {

    private List<Recipe> desserts;
    int dessertsCount;
    private List<Recipe> mainDishes;
    int mainDishesCount;
    private List<Recipe> cocktails ;
    int cocktailsCount;
    private List<Recipe> favourites ;
    int favouritesCount;

    public HomeDTO(List<Recipe> desserts, List<Recipe> mainDishes, List<Recipe> cocktails, List<Recipe> favourites) {
        this.desserts = desserts;
        dessertsCount = desserts.size();
        this.mainDishes = mainDishes;
        mainDishesCount = mainDishes.size();
        this.cocktails = cocktails;
        cocktailsCount = cocktails.size();
        this.favourites = favourites;
        favouritesCount = favourites.size();
    }
}

