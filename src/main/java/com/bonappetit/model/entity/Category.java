package com.bonappetit.model.entity;

import com.bonappetit.model.enums.Description;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category extends BaseEntity{

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private Description categoryName;


    private String description;

    @OneToMany(mappedBy = "category")
    private List<Recipe> recipes;

    public Category() {
    }

    public Category(Description categoryName) {
        this.categoryName = categoryName;
        setDescription(categoryName);
    }

    private void setDescription(Description categoryName) {

        String description = "";

        switch (categoryName){
            case DESSERT ->{
                description =  "Sweet finale, indulgent and delightful; dessert crowns the dining experience with joy.";
            }
            case COCKTAIL -> {
                description =  "Sip of sophistication, cocktails blend flavors, creating a spirited symphony in every glass.";
            }
            case MAIN_DISH -> {
                description =  "Heart of the meal, substantial and satisfying; main dish delights taste buds.";
            }
        }
    this.description = description;
    }


}
