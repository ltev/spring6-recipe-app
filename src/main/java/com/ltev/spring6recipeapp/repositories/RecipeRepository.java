package com.ltev.spring6recipeapp.repositories;

import com.ltev.spring6recipeapp.domains.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
