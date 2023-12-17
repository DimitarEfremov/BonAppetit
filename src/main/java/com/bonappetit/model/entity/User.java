package com.bonappetit.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 20)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Email
    @Column(nullable = false, unique = true)
    private String email;


    @OneToMany(mappedBy = "addedBy", fetch = FetchType.EAGER)
    private List<Recipe> addedRecipes;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Recipe> favouriteRecipes;

    public void addFavourite(Recipe recipe){
        this.favouriteRecipes.add(recipe);
    }



}
