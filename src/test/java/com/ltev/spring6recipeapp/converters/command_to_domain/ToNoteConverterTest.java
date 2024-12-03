package com.ltev.spring6recipeapp.converters.command_to_domain;

import com.ltev.spring6recipeapp.commands.NoteCommand;
import com.ltev.spring6recipeapp.domains.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToNoteConverterTest {

    ToNoteConverter converter;

    @BeforeEach
    void setUp() {
        converter = new ToNoteConverter();
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new NoteCommand()));
    }

    @Test
    void convert() {
        // given
        NoteCommand cmd = new NoteCommand();
        cmd.setId(10L);
        cmd.setDescription("Note Description");;

        // when
        Note note = converter.convert(cmd);

        // then
        assertEquals(cmd.getId(), note.getId());
        assertEquals(cmd.getDescription(), note.getDescription());
    }
}