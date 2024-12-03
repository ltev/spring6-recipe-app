package com.ltev.spring6recipeapp.controllers;

import com.ltev.spring6recipeapp.commands.RecipeCommand;
import com.ltev.spring6recipeapp.services.RecipeCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    @InjectMocks
    RecipeController rc;

    @Mock
    RecipeCommandService recipeCommandService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        rc = new RecipeController(recipeCommandService);
        mockMvc = MockMvcBuilders.standaloneSetup(rc).build();
    }

    @Test
    void testGetRecipeById_found() throws Exception {
        when(recipeCommandService.getById(1L)).thenReturn(Optional.of(new RecipeCommand()));

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void testGetRecipeById_notFound() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(rc).build();

        when(recipeCommandService.getById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("errorMsg"))
                .andExpect(model().attributeDoesNotExist("recipe"));
    }

    @Test
    void testDeleteRecipeById() throws Exception {
        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(recipeCommandService, times(1)).deleteById(anyLong());
    }
}
