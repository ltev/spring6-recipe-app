package com.ltev.spring6recipeapp.services.impl;

import com.ltev.spring6recipeapp.commands.IngredientCommand;
import com.ltev.spring6recipeapp.converters.command_to_domain.ToIngredientConverter;
import com.ltev.spring6recipeapp.converters.domain_to_command.FromIngredientConverter;
import com.ltev.spring6recipeapp.domains.Ingredient;
import com.ltev.spring6recipeapp.repositories.IngredientRepository;
import com.ltev.spring6recipeapp.services.IngredientCommandService;
import com.ltev.spring6recipeapp.services.UnitOfMeasureCommandService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class IngredientCommandServiceImpl extends AbstractCommandService<IngredientCommand, Ingredient, Long>
        implements IngredientCommandService {

    private final UnitOfMeasureCommandService unitOfMeasureCommandService;

    public IngredientCommandServiceImpl(IngredientRepository repository,
                                        FromIngredientConverter fromDomainConverter,
                                        ToIngredientConverter toDomainConverter,
                                        UnitOfMeasureCommandService unitOfMeasureCommandService) {
        super(repository, fromDomainConverter, toDomainConverter);
        this.unitOfMeasureCommandService = unitOfMeasureCommandService;
    }

    @Override
    @Transactional
    public IngredientCommand save(IngredientCommand ingredient, Long unitOfMeasureId) {
        var uomCmd = unitOfMeasureCommandService.getById(unitOfMeasureId);
        ingredient.setUom(uomCmd.get());
        return super.save(ingredient);
    }
}
