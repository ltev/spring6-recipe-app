package com.ltev.spring6recipeapp.commands;

import lombok.Data;

@Data
public class NoteCommand {

    private Long id;
    private String description;
}
