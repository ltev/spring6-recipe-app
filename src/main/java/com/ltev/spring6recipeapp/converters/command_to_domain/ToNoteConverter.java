package com.ltev.spring6recipeapp.converters.command_to_domain;

import com.ltev.spring6recipeapp.commands.NoteCommand;
import com.ltev.spring6recipeapp.domains.Note;
import org.springframework.stereotype.Component;

@Component
public class ToNoteConverter extends AbstractCommandToDomainConverter<NoteCommand, Note> {

    @Override
    protected Note getNewEmptyConvertedInstance() {
        return new Note();
    }
}
