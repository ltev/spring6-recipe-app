package com.ltev.spring6recipeapp.repositories;

import com.ltev.spring6recipeapp.domains.Ingredient;
import com.ltev.spring6recipeapp.domains.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

}
