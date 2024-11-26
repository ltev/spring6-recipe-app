package com.ltev.spring6recipeapp.bootstrap;

import com.ltev.spring6recipeapp.domains.*;
import com.ltev.spring6recipeapp.repositories.IngredientRepository;
import com.ltev.spring6recipeapp.repositories.RecipeRepository;
import com.ltev.spring6recipeapp.repositories.UnitOfMeasureRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@AllArgsConstructor
public class StartUpDataLoader implements CommandLineRunner {

    RecipeRepository recipeRepository;
    UnitOfMeasureRepository unitOfMeasureRepository;
    IngredientRepository ingredientRepository;

    @Override
    public void run(String... args) throws Exception {
        UnitOfMeasure gram = new UnitOfMeasure("gram");
        // unitOfMeasureRepository.save(gram);      // detached entity to persist exception in ingredientRepository save

        UnitOfMeasure teaspoon = new UnitOfMeasure("teaspoon");

        Ingredient ingredient = new Ingredient();
        ingredient.setDescription("Olive Oil");
        ingredient.setAmount(new BigDecimal("2.5"));
        ingredient.setUom(gram);

        try {
            ingredientRepository.save(ingredient);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ingredient.setUom(teaspoon);
        ingredientRepository.save(ingredient);

        ingredient.setUom(new UnitOfMeasure("liter"));
        ingredientRepository.save(ingredient);

        Recipe recipe = new Recipe();
        recipe.setDescription("New recipe with a lot of olive oil");
        recipe.setPrepTime(45);
        recipe.setServings(4);
        recipe.setDifficulty(Difficulty.HARD);
        recipe.setNote(new Note("This is a note for the new recipe with a lot of olive oil"));
        recipeRepository.save(recipe);


        Recipe foundRecipe = recipeRepository.findById(1L).get();
        System.out.println(foundRecipe);

    }
}
