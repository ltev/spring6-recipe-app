package com.ltev.spring6recipeapp.controllers;

import com.ltev.spring6recipeapp.domains.Recipe;
import com.ltev.spring6recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    @InjectMocks
    RecipeController rc;

    @Mock
    RecipeService recipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        rc = new RecipeController(recipeService);
    }

    @Test
    void testGetRecipeById_found() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(rc).build();

        when(recipeService.getRecipe(1L)).thenReturn(Optional.of(new Recipe()));

        mockMvc.perform(get("/recipes/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes/recipe"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void testGetRecipeById_notFound() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(rc).build();

        when(recipeService.getRecipe(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/recipes/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes/recipe"))
                .andExpect(model().attributeExists("errorMsg"))
                .andExpect(model().attributeDoesNotExist("recipe"));
    }

}
