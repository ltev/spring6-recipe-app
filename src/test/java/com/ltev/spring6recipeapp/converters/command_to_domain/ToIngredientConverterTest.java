package com.ltev.spring6recipeapp.converters.command_to_domain;

import com.ltev.spring6recipeapp.commands.IngredientCommand;
import com.ltev.spring6recipeapp.commands.UnitOfMeasureCommand;
import com.ltev.spring6recipeapp.domains.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ToIngredientConverterTest {

    ToIngredientConverter converter;

    @BeforeEach
    void setUp() {
        converter = new ToIngredientConverter(new ToUnitOfMeasureConverter());
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    void convert() {
        // given
        UnitOfMeasureCommand uomCmd = new UnitOfMeasureCommand();
        uomCmd.setId(1L);
        uomCmd.setDescription("Uom Description");;

        IngredientCommand cmd = new IngredientCommand();
        cmd.setId(10L);
        cmd.setDescription("Note Description");;
        cmd.setAmount(new BigDecimal("2.8"));
        cmd.setUom(uomCmd);

        // when
        Ingredient ingredient = converter.convert(cmd);

        // then
        assertEquals(cmd.getId(), ingredient.getId());
        assertEquals(cmd.getDescription(), ingredient.getDescription());
        assertEquals(cmd.getAmount(), ingredient.getAmount());

        assertEquals(uomCmd.getId(), ingredient.getUom().getId());
        assertEquals(uomCmd.getDescription(), ingredient.getUom().getDescription());
    }
}