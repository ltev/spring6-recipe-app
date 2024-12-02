package com.ltev.spring6recipeapp.converters;

import com.ltev.spring6recipeapp.commands.IngredientCommand;
import com.ltev.spring6recipeapp.commands.UnitOfMeasureCommand;
import com.ltev.spring6recipeapp.domains.Ingredient;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class IngredientConverter extends AbstractCommandToDomainConverter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureConverter unitOfMeasureConverter;

    @Override
    protected Ingredient getNewEmptyConvertedInstance() {
        return new Ingredient();
    }

    @Override
    protected Converter getConverter(Class<?> fromType) {
        if (fromType == UnitOfMeasureCommand.class) {
            return unitOfMeasureConverter;
        }
        throw new RuntimeException("Not found for class: " + fromType);
    }
}
