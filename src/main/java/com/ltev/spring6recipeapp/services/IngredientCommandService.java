package com.ltev.spring6recipeapp.services;

import com.ltev.spring6recipeapp.commands.IngredientCommand;

public interface IngredientCommandService extends BaseService<IngredientCommand, Long> {

    IngredientCommand save(IngredientCommand ingredient, Long unitOfMeasureId);
}
