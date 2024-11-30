package com.ltev.spring6recipeapp.bootstrap;

import com.ltev.spring6recipeapp.domains.Difficulty;
import com.ltev.spring6recipeapp.repositories.RecipeRepository;
import com.ltev.spring6recipeapp.repositories.UnitOfMeasureRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@AllArgsConstructor
public class StartUpDataLoader implements CommandLineRunner {

    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Override
    public void run(String... args) {
        addRecipe1();
        addRecipe2();
    }

    private void addRecipe1() {
        RecipeBuilder creator = new RecipeBuilder(unitOfMeasureRepository);

        creator.setDescription("New recipe with a lot of olive oil");
        creator.setPrepTime(45);
        creator.setServings(4);
        creator.setDifficulty(Difficulty.HARD);
        creator.setNote("This is a note for the new recipe with a lot of olive oil");

        // description, amount, unit of measure
        creator.addIngredient(List.of("Olive Oil", "100", "Gram"));
        creator.addIngredient(List.of("Butter", "2.5", "Teaspoon"));

        // categories
        creator.addCategories(List.of("grill", "extra", "super"));
        recipeRepository.save(creator.build());
    }

    private void addRecipe2() {
        RecipeBuilder creator = new RecipeBuilder(unitOfMeasureRepository);

        creator.setDescription("Ham Sandwich");
        creator.setPrepTime(15);
        creator.setCookTime(0);
        creator.setServings(2);
        creator.setDifficulty(Difficulty.EASY);
        creator.setDirections("""
                1. Buy some bread
                2. Cut some slices
                3. Put some butter on the slices
                4. Put some ham on the slices""");
        creator.setNote("You only need basic food preparation skills.");

        // description, amount, unit of measure
        creator.addIngredient(List.of("Ham", "100", "Gram"));
        creator.addIngredient(List.of("Butter", "50", "Gram"));
        creator.addIngredient(List.of("Bread", "2", "Slice"));

        // categories
        creator.addCategories(List.of("sandwich", "ham", "easy"));
        recipeRepository.save(creator.build());
    }
}
