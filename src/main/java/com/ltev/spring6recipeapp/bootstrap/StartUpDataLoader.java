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

        Ingredient oliveOil = new Ingredient();
        oliveOil.setDescription("Olive Oil");
        oliveOil.setAmount(new BigDecimal("2.5"));
        oliveOil.setUom(gram);

        Ingredient butter = new Ingredient();
        butter.setDescription("Butter");
        butter.setAmount(new BigDecimal("100"));
        butter.setUom(new UnitOfMeasure("teaspoon"));

        try {
            // ingredientRepository.save(oliveOil);
        } catch (Exception e) {
            e.printStackTrace();
        }

        oliveOil.setUom(teaspoon);
        //ingredientRepository.save(oliveOil);

        oliveOil.setUom(new UnitOfMeasure("liter"));
        //ingredientRepository.save(oliveOil);

        Recipe recipe = new Recipe();
        recipe.setDescription("New recipe with a lot of olive oil");
        recipe.setPrepTime(45);
        recipe.setServings(4);
        recipe.setDifficulty(Difficulty.HARD);
        recipe.setNote(new Note("This is a note for the new recipe with a lot of olive oil"));
        recipe.addIngredient(oliveOil);
        recipe.addIngredient(butter);
        recipeRepository.save(recipe);

        Recipe foundRecipe = recipeRepository.findById(1L).get();
        Ingredient foundIngredient1 = ingredientRepository.findById(1L).get();
        Ingredient foundIngredient2 = ingredientRepository.findById(2L).get();

        System.out.println(foundRecipe);
        System.out.println(foundIngredient1);
        System.out.println(foundIngredient2);
    }
}
