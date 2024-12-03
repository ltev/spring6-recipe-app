package com.ltev.spring6recipeapp.controllers;

import com.ltev.spring6recipeapp.commands.RecipeCommand;
import com.ltev.spring6recipeapp.domains.Recipe;
import com.ltev.spring6recipeapp.services.RecipeCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {

    IndexController controller;

    @Mock
    RecipeCommandService recipeCommandService;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new IndexController(recipeCommandService);
    }

    @Test
    void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void getIndexPage() {
        when(recipeCommandService.getAll()).thenReturn(List.of(new RecipeCommand(), new RecipeCommand()));

        ArgumentCaptor<Set<Recipe>> captor = ArgumentCaptor.forClass(Set.class);

        assertEquals("index", controller.getIndexPage(model));
        verify(recipeCommandService, times(1)).getAll();
        verify(model, times(1)).addAttribute(eq("recipes"), captor.capture());
        Set<Recipe> setInController = captor.getValue();
        assertEquals(2, setInController.size());
    }
}