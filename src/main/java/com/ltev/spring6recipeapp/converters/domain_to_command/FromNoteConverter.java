package com.ltev.spring6recipeapp.converters.domain_to_command;

import com.ltev.spring6recipeapp.commands.NoteCommand;
import com.ltev.spring6recipeapp.domains.Note;
import org.springframework.stereotype.Component;

@Component
public class FromNoteConverter extends AbstractDomainToCommandConverter<Note, NoteCommand> {

    @Override
    protected NoteCommand getNewEmptyConvertedInstance() {
        return new NoteCommand();
    }
}
