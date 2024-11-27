package com.ltev.spring6recipeapp.services.impl;

import com.ltev.spring6recipeapp.domains.Recipe;
import com.ltev.spring6recipeapp.repositories.RecipeRepository;
import com.ltev.spring6recipeapp.services.RecipeService;
import jakarta.transaction.NotSupportedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    @Override
    public Set<Recipe> getRecipes() {
        return StreamSupport.stream(recipeRepository.findAll().spliterator(), false)
                .reduce(new HashSet<>(),
                        (identity, recipe) -> {identity.add(recipe); return identity;},
                        (identity1, identity2) -> {throw new RuntimeException("Only for parallel.");});
    }
}
