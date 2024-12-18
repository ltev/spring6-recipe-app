package com.ltev.spring6recipeapp.converters.command_to_domain;

import com.ltev.spring6recipeapp.commands.*;
import com.ltev.spring6recipeapp.domains.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ToRecipeConverterTest {

    ToRecipeConverter converter;

    @BeforeEach
    void setUp() {
        converter = new ToRecipeConverter(
                new ToNoteConverter(),
                new ToCategoryConverter(),
                new ToIngredientConverter(new ToUnitOfMeasureConverter()));
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    void convert() {
        // given
        NoteCommand noteCmd = new NoteCommand();
        noteCmd.setId(1L);
        noteCmd.setDescription("Uom Description");

        CategoryCommand categoryCmd1 = new CategoryCommand();
        categoryCmd1.setId(123L);
        categoryCmd1.setDescription("Category Description 1");

        CategoryCommand categoryCmd2 = new CategoryCommand();
        categoryCmd2.setId(2L);
        categoryCmd2.setDescription("Category Description 2");

        UnitOfMeasureCommand unitOfMeasureCmd1 = new UnitOfMeasureCommand();
        unitOfMeasureCmd1.setId(1L);
        unitOfMeasureCmd1.setDescription("gram");

        UnitOfMeasureCommand unitOfMeasureCmd2 = new UnitOfMeasureCommand();
        unitOfMeasureCmd1.setId(2L);
        unitOfMeasureCmd1.setDescription("ml");

        IngredientCommand ingredientCmd1 = new IngredientCommand();
        ingredientCmd1.setId(1L);
        ingredientCmd1.setDescription("Ingredient Description 1");
        ingredientCmd1.setUom(unitOfMeasureCmd1);

        IngredientCommand ingredientCmd2 = new IngredientCommand();
        ingredientCmd2.setId(2L);
        ingredientCmd2.setDescription("Ingredient Description 2");
        ingredientCmd2.setUom(unitOfMeasureCmd2);

        RecipeCommand cmd = new RecipeCommand();
        cmd.setId(10L);
        cmd.setDescription("Recipe Description");;
        cmd.setPrepTime(12);
        cmd.setCookTime(45);
        cmd.setServings(3);
        cmd.setSource("source data");
        cmd.setUrl("url data");
        cmd.setDirections("direction data");
        cmd.setDifficulty(Difficulty.MODERATE);
        cmd.setNote(noteCmd);
        cmd.setCategories(Set.of(categoryCmd1, categoryCmd2));
        cmd.setIngredients(Set.of(ingredientCmd1, ingredientCmd2));

        // when
        Recipe recipe = converter.convert(cmd);

        // then
        assertEquals(cmd.getId(), recipe.getId());
        assertEquals(cmd.getDescription(), recipe.getDescription());
        assertEquals(cmd.getPrepTime(), recipe.getPrepTime());
        assertEquals(cmd.getCookTime(), recipe.getCookTime());
        assertEquals(cmd.getServings(), recipe.getServings());
        assertEquals(cmd.getSource(), recipe.getSource());
        assertEquals(cmd.getUrl(), recipe.getUrl());
        assertEquals(cmd.getDirections(), recipe.getDirections());
        assertEquals(cmd.getDifficulty(), recipe.getDifficulty());

        assertEquals(noteCmd.getId(), recipe.getNote().getId());
        assertEquals(noteCmd.getDescription(), recipe.getNote().getDescription());

        assertEquals(2, recipe.getCategories().size());
        assertEquals(Category.class, recipe.getCategories().stream().findAny().get().getClass());

        Ingredient ingredient = recipe.getIngredients().stream().findAny().get();
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(Ingredient.class, ingredient.getClass());
        assertEquals(UnitOfMeasure.class, ingredient.getUom().getClass());
    }
}