package com.ltev.spring6recipeapp.converters.domain_to_command;

import com.ltev.spring6recipeapp.commands.CategoryCommand;
import com.ltev.spring6recipeapp.domains.Category;
import org.springframework.stereotype.Component;

@Component
public class FromCategoryConverter extends AbstractDomainToCommandConverter<Category, CategoryCommand> {

    @Override
    protected CategoryCommand getNewEmptyConvertedInstance() {
        return new CategoryCommand();
    }
}
