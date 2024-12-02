package com.ltev.spring6recipeapp.converters;

import com.ltev.spring6recipeapp.commands.CategoryCommand;
import com.ltev.spring6recipeapp.commands.NoteCommand;
import com.ltev.spring6recipeapp.commands.RecipeCommand;
import com.ltev.spring6recipeapp.domains.Recipe;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RecipeConverter extends AbstractCommandToDomainConverter<RecipeCommand, Recipe> {

    private final NoteConverter noteConverter;
    private final CategoryConverter categoryConverter;

    @Override
    protected Recipe getNewEmptyConvertedInstance() {
        return new Recipe();
    }

    @Override
    protected Converter getConverter(Class<?> fromType) {
        if (fromType == NoteCommand.class) {
            return noteConverter;
        } else if (fromType == CategoryCommand.class) {
            return categoryConverter;
        }
        throw new RuntimeException("Not found for class: " + fromType);
    }
}
