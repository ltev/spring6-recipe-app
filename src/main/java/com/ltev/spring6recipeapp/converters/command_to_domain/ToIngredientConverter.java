package com.ltev.spring6recipeapp.converters.command_to_domain;

import com.ltev.spring6recipeapp.commands.IngredientCommand;
import com.ltev.spring6recipeapp.commands.UnitOfMeasureCommand;
import com.ltev.spring6recipeapp.domains.Ingredient;
import com.ltev.spring6recipeapp.domains.Recipe;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ToIngredientConverter extends AbstractCommandToDomainConverter<IngredientCommand, Ingredient> {

    private final ToUnitOfMeasureConverter unitOfMeasureConverter;

    @Override
    protected Ingredient getNewEmptyConvertedInstance() {
        return new Ingredient();
    }

    /**
     * Uses Java Reflection to set values on destination object's fields
     *
     * @param source
     */
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) {
            return null;
        }

        Ingredient ingredient = super.convert(source);
        /*
         * Working with dumbRecipe
         * MIGHT NEED change to read recipe - from repository
         */
        Recipe dumbRecipe = new Recipe();
        dumbRecipe.setId(source.getRecipeId());
        dumbRecipe.addIngredient(ingredient);
        return ingredient;
    }

    @Override
    protected Converter getConverter(Class<?> fromType) {
        if (fromType == UnitOfMeasureCommand.class) {
            return unitOfMeasureConverter;
        }
        throw new RuntimeException("Not found for class: " + fromType);
    }
}
