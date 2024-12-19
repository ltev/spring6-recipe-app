package com.ltev.spring6recipeapp.converters.domain_to_command;

import com.ltev.spring6recipeapp.commands.CategoryCommand;
import com.ltev.spring6recipeapp.commands.IngredientCommand;
import com.ltev.spring6recipeapp.commands.RecipeCommand;
import com.ltev.spring6recipeapp.commands.UnitOfMeasureCommand;
import com.ltev.spring6recipeapp.domains.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FromRecipeConverterTest {

    FromRecipeConverter converter;

    @BeforeEach
    void setUp() {
        converter = FromRecipeConverter.instance();
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    void convert() {
        // given
        Note note = new Note();
        note.setId(1L);
        note.setDescription("Uom Description");

        Category category1 = new Category();
        category1.setId(123L);
        category1.setDescription("Category Description 1");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setDescription("Category Description 2");

        UnitOfMeasure uom1 = new UnitOfMeasure();

        Ingredient ingredient = new Ingredient();
        ingredient.setUom(uom1);

        Recipe recipe = new Recipe();
        recipe.setId(10L);
        recipe.setDescription("Note Description");;
        recipe.setPrepTime(12);
        recipe.setCookTime(45);
        recipe.setServings(3);
        recipe.setSource("source data");
        recipe.setUrl("url data");
        recipe.setDirections("direction data");
        recipe.setDifficulty(Difficulty.MODERATE);
        recipe.setNote(note);
        recipe.addCategory(category1);
        recipe.addCategory(category2);
        recipe.addIngredient(ingredient);

        // when
        RecipeCommand cmd = converter.convert(recipe);

        // then
        assertEquals(recipe.getId(), cmd.getId());
        assertEquals(recipe.getDescription(), cmd.getDescription());
        assertEquals(recipe.getPrepTime(), cmd.getPrepTime());
        assertEquals(recipe.getCookTime(), cmd.getCookTime());
        assertEquals(recipe.getServings(), cmd.getServings());
        assertEquals(recipe.getSource(), cmd.getSource());
        assertEquals(recipe.getUrl(), cmd.getUrl());
        assertEquals(recipe.getDirections(), cmd.getDirections());
        assertEquals(recipe.getDifficulty(), cmd.getDifficulty());

        assertEquals(note.getId(), cmd.getNote().getId());
        assertEquals(note.getDescription(), cmd.getNote().getDescription());

        assertEquals(2, cmd.getCategories().size());
        assertEquals(CategoryCommand.class, cmd.getCategories().stream().findAny().get().getClass());

        IngredientCommand ingredientCmd = cmd.getIngredients().stream().findAny().get();
        assertEquals(1, cmd.getIngredients().size());
        assertEquals(IngredientCommand.class, cmd.getIngredients().stream().findAny().get().getClass());
        assertEquals(UnitOfMeasureCommand.class, ingredientCmd.getUom().getClass());
    }

}