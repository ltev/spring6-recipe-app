package com.ltev.spring6recipeapp.repositories;

import com.ltev.spring6recipeapp.domains.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {


}
