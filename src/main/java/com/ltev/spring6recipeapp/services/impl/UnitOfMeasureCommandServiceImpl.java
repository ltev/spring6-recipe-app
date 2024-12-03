package com.ltev.spring6recipeapp.services.impl;

import com.ltev.spring6recipeapp.commands.UnitOfMeasureCommand;
import com.ltev.spring6recipeapp.converters.command_to_domain.ToUnitOfMeasureConverter;
import com.ltev.spring6recipeapp.converters.domain_to_command.FromUnitOfMeasureConverter;
import com.ltev.spring6recipeapp.domains.UnitOfMeasure;
import com.ltev.spring6recipeapp.repositories.UnitOfMeasureRepository;
import com.ltev.spring6recipeapp.services.UnitOfMeasureCommandService;
import org.springframework.stereotype.Service;


@Service
public class UnitOfMeasureCommandServiceImpl extends AbstractCommandService<UnitOfMeasureCommand, UnitOfMeasure, Long>
        implements UnitOfMeasureCommandService {

    public UnitOfMeasureCommandServiceImpl(UnitOfMeasureRepository repository,
                                           FromUnitOfMeasureConverter fromDomainConverter,
                                           ToUnitOfMeasureConverter toDomainConverter) {
        super(repository, fromDomainConverter, toDomainConverter);
    }
}
