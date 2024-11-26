package com.ltev.spring6recipeapp.domains;

import java.util.Set;

public class Recipe {

    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<Ingredient> ingredients;
    private Difficulty difficulty;
    private Note notes;
}
