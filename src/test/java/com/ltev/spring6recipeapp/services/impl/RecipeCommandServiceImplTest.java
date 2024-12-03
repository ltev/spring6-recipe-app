package com.ltev.spring6recipeapp.services.impl;

import com.ltev.spring6recipeapp.converters.command_to_domain.ToCategoryConverter;
import com.ltev.spring6recipeapp.converters.command_to_domain.ToNoteConverter;
import com.ltev.spring6recipeapp.converters.command_to_domain.ToRecipeConverter;
import com.ltev.spring6recipeapp.converters.domain_to_command.FromCategoryConverter;
import com.ltev.spring6recipeapp.converters.domain_to_command.FromNoteConverter;
import com.ltev.spring6recipeapp.converters.domain_to_command.FromRecipeConverter;
import com.ltev.spring6recipeapp.domains.Difficulty;
import com.ltev.spring6recipeapp.domains.Recipe;
import com.ltev.spring6recipeapp.repositories.RecipeRepository;
import com.ltev.spring6recipeapp.services.RecipeCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


class RecipeCommandServiceImplTest {

    RecipeCommandService recipeCommandService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeCommandService = new RecipeCommandServiceImpl(recipeRepository,
                new FromRecipeConverter(new FromNoteConverter(), new FromCategoryConverter()),
                new ToRecipeConverter(new ToNoteConverter(), new ToCategoryConverter()));
    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        recipe.setDescription("Some hard recipe");
        recipe.setDifficulty(Difficulty.HARD);
        recipe.setServings(4);
        List<Recipe> recipes = List.of(recipe);

        when(recipeRepository.findAll()).thenReturn(recipes);

        assertEquals(1, recipeCommandService.getAll().size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void getRecipeById() {
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(new Recipe()));

        assertTrue(recipeCommandService.getById(1L).isPresent());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    void deleteById() {
        recipeCommandService.deleteById(1L);

        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}