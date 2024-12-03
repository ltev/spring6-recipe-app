package com.ltev.spring6recipeapp.converters.command_to_domain;

import com.ltev.spring6recipeapp.commands.UnitOfMeasureCommand;
import com.ltev.spring6recipeapp.domains.UnitOfMeasure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToUnitOfMeasureConverterTest {

    static ToUnitOfMeasureConverter converter = new ToUnitOfMeasureConverter();

    @BeforeAll
    static void setUpBeforeClass() {
        converter = new ToUnitOfMeasureConverter();
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    void convert() {
        // 1 convert
        // given
        UnitOfMeasureCommand cmd = new UnitOfMeasureCommand();
        cmd.id = 10L;
        cmd.description = "This is a description";

        // when
        UnitOfMeasure uom = converter.convert(cmd);

        // then
        assertEquals(cmd.id, uom.getId());
        assertEquals(cmd.description, uom.getDescription());

        // 2 convert
        cmd = new UnitOfMeasureCommand();
        cmd.id = 12L;
        cmd.description = "This description is second";

        uom = converter.convert(cmd);
        assertEquals(12L, uom.getId());
        assertEquals("This description is second", uom.getDescription());

        // 3 convert
        cmd = new UnitOfMeasureCommand();
        cmd.id = null;
        cmd.description = null;

        uom = converter.convert(cmd);
        assertNull(uom.getId());
        assertNull(uom.getDescription());
    }
}