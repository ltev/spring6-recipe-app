package com.ltev.spring6recipeapp.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class UnitOfMeasureRepositoryTestIT {

    @Autowired
    UnitOfMeasureRepository repo;

    @Test
    void findByDescription() {
        assertEquals("Teaspoon", repo.findByDescriptionIgnoreCase("Teaspoon").get().getDescription());
    }
}