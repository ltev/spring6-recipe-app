package com.ltev.spring6recipeapp.converters;

import com.ltev.spring6recipeapp.commands.NoteCommand;
import com.ltev.spring6recipeapp.domains.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteConverter extends AbstractCommandToDomainConverter<NoteCommand, Note> {

    @Override
    protected Note getNewEmptyConvertedInstance() {
        return new Note();
    }
}
