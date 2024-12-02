package com.ltev.spring6recipeapp.converters.domain_to_command;

import com.ltev.spring6recipeapp.commands.IngredientCommand;
import com.ltev.spring6recipeapp.domains.Ingredient;
import com.ltev.spring6recipeapp.domains.UnitOfMeasure;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FromIngredientConverter extends AbstractDomainToCommandConverter<Ingredient, IngredientCommand> {

    private final FromUnitOfMeasureConverter unitOfMeasureConverter;

    @Override
    protected IngredientCommand getNewEmptyConvertedInstance() {
        return new IngredientCommand();
    }

    @Override
    protected Converter getConverter(Class<?> fromType) {
        if (fromType == UnitOfMeasure.class) {
            return unitOfMeasureConverter;
        }
        throw new RuntimeException("Not found for class: " + fromType);
    }
}
