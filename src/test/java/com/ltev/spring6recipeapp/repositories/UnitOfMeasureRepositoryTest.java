package com.ltev.spring6recipeapp.repositories;

import com.ltev.spring6recipeapp.domains.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class UnitOfMeasureRepositoryTest {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void save() throws InterruptedException {
        long startRepositorySize = unitOfMeasureRepository.count();

        UnitOfMeasure teaspoon = new UnitOfMeasure("teaspoon");
        UnitOfMeasure gram = new UnitOfMeasure("gram");

        unitOfMeasureRepository.save(teaspoon);
        unitOfMeasureRepository.save(gram);
        unitOfMeasureRepository.save(gram);

        assertNotNull(teaspoon.getId());
        assertNotNull(gram.getId());
        assertEquals(startRepositorySize + 2, unitOfMeasureRepository.count());
    }

    @Test
    void findById() {
        UnitOfMeasure teaspoon = new UnitOfMeasure("teaspoon");
        UnitOfMeasure gram = new UnitOfMeasure("gram");

        unitOfMeasureRepository.save(teaspoon);
        unitOfMeasureRepository.save(gram);

        assertEquals("teaspoon", unitOfMeasureRepository.findById(teaspoon.getId()).get().getDescription());
        assertEquals("gram", unitOfMeasureRepository.findById(gram.getId()).get().getDescription());
    }

    @Test
    void findByDescription() {
        UnitOfMeasure teaspoon = new UnitOfMeasure("teaspoon");
        UnitOfMeasure gram = new UnitOfMeasure("gram");

        unitOfMeasureRepository.save(teaspoon);
        unitOfMeasureRepository.save(gram);

        assertEquals(teaspoon.getId(), unitOfMeasureRepository.findByDescriptionIgnoreCase("teaspoon").get().getId());
        assertEquals(gram.getId(), unitOfMeasureRepository.findByDescriptionIgnoreCase("gram").get().getId());
    }
}