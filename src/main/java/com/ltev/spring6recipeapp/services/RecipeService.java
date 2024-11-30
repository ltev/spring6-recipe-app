package com.ltev.spring6recipeapp.services;

import com.ltev.spring6recipeapp.domains.Recipe;

import java.util.Optional;
import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Optional<Recipe> getRecipe(Long id);
}
