package com.ltev.spring6recipeapp.converters;

import com.ltev.spring6recipeapp.commands.CategoryCommand;
import com.ltev.spring6recipeapp.commands.NoteCommand;
import com.ltev.spring6recipeapp.commands.RecipeCommand;
import com.ltev.spring6recipeapp.domains.Difficulty;
import com.ltev.spring6recipeapp.domains.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeConverterTest {

    RecipeConverter converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeConverter(new NoteConverter(), new CategoryConverter());
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

        RecipeCommand cmd = new RecipeCommand();
        cmd.setId(10L);
        cmd.setDescription("Note Description");;
        cmd.setPrepTime(12);
        cmd.setCookTime(45);
        cmd.setServings(3);
        cmd.setSource("source data");
        cmd.setUrl("url data");
        cmd.setDirections("direction data");
        cmd.setDifficulty(Difficulty.MODERATE);
        cmd.setNote(noteCmd);
        cmd.setCategories(Set.of(categoryCmd1, categoryCmd2));

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
    }
}