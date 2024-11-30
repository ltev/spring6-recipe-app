package com.ltev.spring6recipeapp.services.impl;

import com.ltev.spring6recipeapp.domains.Difficulty;
import com.ltev.spring6recipeapp.domains.Recipe;
import com.ltev.spring6recipeapp.repositories.RecipeRepository;
import com.ltev.spring6recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


class RecipeServiceImplTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);

        System.out.println(recipeRepository);
    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        recipe.setDescription("Some hard recipe");
        recipe.setDifficulty(Difficulty.HARD);
        recipe.setServings(4);
        List<Recipe> recipes = List.of(recipe);

        when(recipeRepository.findAll()).thenReturn(recipes);

        assertEquals(1, recipeService.getRecipes().size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void getRecipeById() {
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(new Recipe()));

        assertTrue(recipeService.getRecipe(1L).isPresent());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }
}