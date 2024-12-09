package com.ltev.spring6recipeapp.converters.domain_to_command;

import com.ltev.spring6recipeapp.commands.RecipeCommand;
import com.ltev.spring6recipeapp.converters.using_annotations.AbstractConverterUsingAnnotation;
import com.ltev.spring6recipeapp.domains.Category;
import com.ltev.spring6recipeapp.domains.Note;
import com.ltev.spring6recipeapp.domains.Recipe;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@AllArgsConstructor
public class FromRecipeConverter extends AbstractConverterUsingAnnotation<Recipe, RecipeCommand> {
// AbstractDomainToCommandConverter<Recipe, RecipeCommand>

    private final FromNoteConverter noteConverter;
    private final FromCategoryConverter categoryConverter;
    private final FromIngredientConverter ingredientConverter;

    public static FromRecipeConverter instance() {
        return new FromRecipeConverter(
                new FromNoteConverter(),
                new FromCategoryConverter(),
                new FromIngredientConverter(new FromUnitOfMeasureConverter())
        );
    }

    @Override
    protected void convertIterableFields(Recipe sourceRecipe, RecipeCommand desRecipeCommand) {
        desRecipeCommand.setCategories(
                createCollection(sourceRecipe.getCategories(), new HashSet<>(), categoryConverter));
        desRecipeCommand.setIngredients(
                createCollection(sourceRecipe.getIngredients(), new HashSet<>(), ingredientConverter)
        );
    }

    @Override
    protected RecipeCommand getNewEmptyConvertedInstance() {
        return new RecipeCommand();
    }

    @Override
    protected Converter getConverter(Class<?> fromType) {
        if (fromType == Note.class) {
            return noteConverter;
        }
        throw new RuntimeException("Not found for class: " + fromType);
    }
}
