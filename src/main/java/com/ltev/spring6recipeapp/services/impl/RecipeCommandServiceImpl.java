package com.ltev.spring6recipeapp.services.impl;

import com.ltev.spring6recipeapp.commands.RecipeCommand;
import com.ltev.spring6recipeapp.converters.command_to_domain.ToRecipeConverter;
import com.ltev.spring6recipeapp.converters.domain_to_command.FromRecipeConverter;
import com.ltev.spring6recipeapp.domains.Recipe;
import com.ltev.spring6recipeapp.repositories.RecipeRepository;
import com.ltev.spring6recipeapp.services.RecipeCommandService;
import org.springframework.stereotype.Service;


@Service
public class RecipeCommandServiceImpl extends AbstractCommandService<RecipeCommand, Recipe, Long>
        implements RecipeCommandService {

    public RecipeCommandServiceImpl(RecipeRepository repository,
                                    FromRecipeConverter fromDomainConverter,
                                    ToRecipeConverter toDomainConverter) {
        super(repository, fromDomainConverter, toDomainConverter);
    }
}
