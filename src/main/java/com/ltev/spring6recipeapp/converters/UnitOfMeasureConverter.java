package com.ltev.spring6recipeapp.converters;

import com.ltev.spring6recipeapp.commands.UnitOfMeasureCommand;
import com.ltev.spring6recipeapp.domains.UnitOfMeasure;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureConverter extends AbstractCommandToDomainConverter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        return super.convert(source);
    }

    @Override
    protected UnitOfMeasure getNewEmptyConvertedInstance() {
        return new UnitOfMeasure();
    }
}
