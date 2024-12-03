package com.ltev.spring6recipeapp.converters.command_to_domain;

import com.ltev.spring6recipeapp.commands.CategoryCommand;
import com.ltev.spring6recipeapp.domains.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToCategoryConverterTest {

    ToCategoryConverter converter;

    @BeforeEach
    void setUp() {
        converter = new ToCategoryConverter();
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