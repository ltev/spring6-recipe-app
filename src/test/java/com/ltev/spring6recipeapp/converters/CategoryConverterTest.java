package com.ltev.spring6recipeapp.converters;

import com.ltev.spring6recipeapp.commands.CategoryCommand;
import com.ltev.spring6recipeapp.commands.NoteCommand;
import com.ltev.spring6recipeapp.domains.Category;
import com.ltev.spring6recipeapp.domains.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryConverterTest {

    CategoryConverter converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryConverter();
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    void convert() {
        // given
        CategoryCommand cmd = new CategoryCommand();
        cmd.setId(101L);
        cmd.setDescription("Category Description");;

        // when
        Category category = converter.convert(cmd);

        // then
        assertEquals(cmd.getId(), category.getId());
        assertEquals(cmd.getDescription(), category.getDescription());
    }
}