package com.ltev.spring6recipeapp.converters;

import com.ltev.spring6recipeapp.commands.CategoryCommand;
import com.ltev.spring6recipeapp.domains.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter extends AbstractCommandToDomainConverter<CategoryCommand, Category> {

    @Override
    protected Category getNewEmptyConvertedInstance() {
        return new Category();
    }
}
