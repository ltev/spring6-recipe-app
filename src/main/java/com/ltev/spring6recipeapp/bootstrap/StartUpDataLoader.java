package com.ltev.spring6recipeapp.bootstrap;

import com.ltev.spring6recipeapp.domains.Ingredient;
import com.ltev.spring6recipeapp.domains.UnitOfMeasure;
import com.ltev.spring6recipeapp.repositories.IngredientRepository;
import com.ltev.spring6recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class StartUpDataLoader implements CommandLineRunner {

    UnitOfMeasureRepository unitOfMeasureRepository;
    IngredientRepository ingredientRepository;

    public StartUpDataLoader(UnitOfMeasureRepository unitOfMeasureRepository, IngredientRepository ingredientRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        UnitOfMeasure gram = new UnitOfMeasure("gram");
        unitOfMeasureRepository.save(gram);

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
    }
}
