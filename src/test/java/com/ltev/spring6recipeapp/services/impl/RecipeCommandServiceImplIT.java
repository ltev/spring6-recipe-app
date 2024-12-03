package com.ltev.spring6recipeapp.services.impl;

import com.ltev.spring6recipeapp.commands.RecipeCommand;
import com.ltev.spring6recipeapp.converters.command_to_domain.ToRecipeConverter;
import com.ltev.spring6recipeapp.converters.domain_to_command.FromRecipeConverter;
import com.ltev.spring6recipeapp.domains.Category;
import com.ltev.spring6recipeapp.domains.Note;
import com.ltev.spring6recipeapp.domains.Recipe;
import com.ltev.spring6recipeapp.repositories.RecipeRepository;
import com.ltev.spring6recipeapp.services.RecipeCommandService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RecipeCommandServiceImplIT {

    @Autowired
    RecipeCommandService recipeCommandService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    ToRecipeConverter toRecipeConverter;

    @Autowired
    FromRecipeConverter fromRecipeConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Transactional
    void save() {
        // given
        Recipe recipe = new Recipe();
        recipe.setDescription("Some hard recipe");
        recipe.setId(null);
        recipe.setNote(new Note());
        recipe.addCategory(new Category());
        recipe.addCategory(new Category());
        RecipeCommand testedRecipeCommand = fromRecipeConverter.convert(recipe);

        // when
        String NEW_DESCRIPTION = "Some very new hard recipe";
        testedRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeCommandService.save(testedRecipeCommand);

        // then
        assertNotNull(savedRecipeCommand.getId());
        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        assertNotNull(savedRecipeCommand.getNote());
        assertEquals(2, savedRecipeCommand.getCategories().size());
    }
}