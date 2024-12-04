package com.ltev.spring6recipeapp.repositories;

import com.ltev.spring6recipeapp.domains.Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    @Query(nativeQuery = true, name = "DELETE FROM INGREDIENT WHERE ID = :id")
    @Override
    void deleteById(@Param("id") Long id);
}
