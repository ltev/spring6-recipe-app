package com.ltev.spring6recipeapp.converters.domain_to_command;

import com.ltev.spring6recipeapp.commands.UnitOfMeasureCommand;
import com.ltev.spring6recipeapp.domains.UnitOfMeasure;
import org.springframework.stereotype.Component;

@Component
public class FromUnitOfMeasureConverter extends AbstractDomainToCommandConverter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Override
    protected UnitOfMeasureCommand getNewEmptyConvertedInstance() {
        return new UnitOfMeasureCommand();
    }
}
