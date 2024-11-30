package com.ltev.spring6recipeapp.repositories;

import com.ltev.spring6recipeapp.domains.Ingredient;
import com.ltev.spring6recipeapp.domains.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class IngredientRepositoryTest {

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    void save() {
        long startSizeIngredient = ingredientRepository.count();
        long startSizeUom = unitOfMeasureRepository.count();

        Ingredient ingredient = new Ingredient();
        ingredient.setDescription("Olive Oil");
        ingredient.setAmount(new BigDecimal("2.5"));
        ingredient.setUom(new UnitOfMeasure("teaspoon"));

        ingredientRepository.save(ingredient);

        assertEquals(startSizeIngredient + 1, ingredientRepository.count());

        assertEquals(startSizeUom + 1, unitOfMeasureRepository.count());
        assertNotNull(unitOfMeasureRepository.findByDescriptionIgnoreCase("teaspoon").get());
    }
}