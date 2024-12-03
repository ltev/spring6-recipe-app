package com.ltev.spring6recipeapp.converters.command_to_domain;

import com.ltev.spring6recipeapp.commands.UnitOfMeasureCommand;
import com.ltev.spring6recipeapp.domains.UnitOfMeasure;
import org.springframework.stereotype.Component;

@Component
public class ToUnitOfMeasureConverter extends AbstractCommandToDomainConverter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        return super.convert(source);
    }

    @Override
    protected UnitOfMeasure getNewEmptyConvertedInstance() {
        return new UnitOfMeasure();
    }
}
