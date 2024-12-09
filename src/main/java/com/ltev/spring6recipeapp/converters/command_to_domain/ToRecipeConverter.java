package com.ltev.spring6recipeapp.converters.command_to_domain;

import com.ltev.spring6recipeapp.commands.NoteCommand;
import com.ltev.spring6recipeapp.commands.RecipeCommand;
import com.ltev.spring6recipeapp.converters.using_annotations.AbstractConverterUsingAnnotation;
import com.ltev.spring6recipeapp.domains.Recipe;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@AllArgsConstructor
public class ToRecipeConverter extends AbstractConverterUsingAnnotation<RecipeCommand, Recipe> {
// AbstractCommandToDomainConverter<RecipeCommand, Recipe>

    private final ToNoteConverter noteConverter;
    private final ToCategoryConverter categoryConverter;
    private final ToIngredientConverter ingredientConverter;

    public static ToRecipeConverter instance() {
        return new ToRecipeConverter(
                new ToNoteConverter(),
                new ToCategoryConverter(),
                new ToIngredientConverter(new ToUnitOfMeasureConverter())
        );
    }

    @Override
    protected void convertIterableFields(RecipeCommand sourceRecipeCommand, Recipe desRecipe) {
        desRecipe.setCategories(
                createCollection(sourceRecipeCommand.getCategories(), new HashSet<>(), categoryConverter));
        desRecipe.setIngredients(
                createCollection(sourceRecipeCommand.getIngredients(), new HashSet<>(), ingredientConverter));
    }

    @Override
    protected Recipe getNewEmptyConvertedInstance() {
        return new Recipe();
    }

    @Override
    protected Converter getConverter(Class<?> fromType) {
        if (fromType == NoteCommand.class) {
            return noteConverter;
        }
        throw new RuntimeException("Not found for class: " + fromType);
    }
}
