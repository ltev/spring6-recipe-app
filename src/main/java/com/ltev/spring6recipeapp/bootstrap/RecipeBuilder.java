package com.ltev.spring6recipeapp.bootstrap;

import com.ltev.spring6recipeapp.domains.*;
import com.ltev.spring6recipeapp.repositories.UnitOfMeasureRepository;

import java.math.BigDecimal;
import java.util.List;


public class RecipeBuilder {

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    private final Recipe recipe = new Recipe();

    public RecipeBuilder(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    /**
     * data order -> description, amount, unit of measure
     * @param data
     * @return
     */
    RecipeBuilder addIngredient(List<String> data) {
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription(data.get(0));
        ingredient.setAmount(new BigDecimal(data.get(1)));
        ingredient.setUom(findOrSaveUnitOfMeasure(data.get(2)));
        recipe.addIngredient(ingredient);
        return this;
    }

    RecipeBuilder addCategories(List<String> data) {
        data.forEach(category -> recipe.addCategory(new Category(category)));
        return this;
    }

    RecipeBuilder setDescription(String description) {
        recipe.setDescription(description);
        return this;
    }

    RecipeBuilder setPrepTime(int prepTime) {
        recipe.setPrepTime(prepTime);
        return this;
    }

    RecipeBuilder setServings(int servings) {
        recipe.setServings(servings);
        return this;
    }

    RecipeBuilder setDifficulty(Difficulty difficulty) {
        recipe.setDifficulty(difficulty);
        return this;
    }

    RecipeBuilder setNote(String difficulty) {
        recipe.setNote(new Note(difficulty));
        return this;
    }

    public Recipe build() {
        return recipe;
    }

    // == PRIVATE HELPER METHODS ==

    private UnitOfMeasure findOrSaveUnitOfMeasure(String description) {
        return unitOfMeasureRepository.findByDescriptionIgnoreCase(description)
                .orElseGet(() -> unitOfMeasureRepository.save(new UnitOfMeasure(description)));
    }


}
