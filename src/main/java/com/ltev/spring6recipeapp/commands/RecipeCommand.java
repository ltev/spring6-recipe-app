package com.ltev.spring6recipeapp.commands;

import com.ltev.spring6recipeapp.domains.Difficulty;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RecipeCommand {

    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Difficulty difficulty;
    private NoteCommand note;
    private Set<CategoryCommand> categories = new HashSet<>();
}
