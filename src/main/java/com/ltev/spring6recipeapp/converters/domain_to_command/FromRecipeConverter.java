package com.ltev.spring6recipeapp.converters.domain_to_command;

import com.ltev.spring6recipeapp.commands.RecipeCommand;
import com.ltev.spring6recipeapp.domains.Category;
import com.ltev.spring6recipeapp.domains.Note;
import com.ltev.spring6recipeapp.domains.Recipe;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FromRecipeConverter extends AbstractDomainToCommandConverter<Recipe, RecipeCommand> {

    private final FromNoteConverter noteConverter;
    private final FromCategoryConverter categoryConverter;

    @Override
    protected RecipeCommand getNewEmptyConvertedInstance() {
        return new RecipeCommand();
    }

    @Override
    protected Converter getConverter(Class<?> fromType) {
        if (fromType == Note.class) {
            return noteConverter;
        } else if (fromType == Category.class) {
            return categoryConverter;
        }
        throw new RuntimeException("Not found for class: " + fromType);
    }
}
