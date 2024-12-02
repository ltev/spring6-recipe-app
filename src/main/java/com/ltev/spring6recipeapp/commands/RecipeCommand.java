package com.ltev.spring6recipeapp.commands;

import com.ltev.spring6recipeapp.converters.using_annotations.Alias;
import com.ltev.spring6recipeapp.domains.Difficulty;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@ToString
public class RecipeCommand {

    @Alias("id")
    private Long id;

    @Alias("des")
    private String description;

    @Alias("prepTime")
    private Integer prepTime;

    @Alias("cookTime")
    private Integer cookTime;

    @Alias("servings")
    private Integer servings;

    @Alias("source")
    private String source;

    @Alias("url")
    private String url;

    @Alias("directions")
    private String directions;

    @Alias("image")
    private Byte[] image;

    @Alias("ingredients")
    private Set<IngredientCommand> ingredients = new HashSet<>();

    @Alias("difficulty")
    private Difficulty difficulty;
    @Alias("note")
    private NoteCommand note;

    @Alias("categories")
    private Set<CategoryCommand> categories = new HashSet<>();
}
