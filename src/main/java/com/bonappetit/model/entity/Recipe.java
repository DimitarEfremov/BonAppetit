package com.bonappetit.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "recipes" )
@Getter
@Setter
public class Recipe extends BaseEntity {

    @Column(nullable = false)
    @Size(min = 2, max = 40)
    private String name;

    @Size(min = 2, max = 150)
    private String ingredients;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User addedBy;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> favouredBy;


}
