package com.ltev.spring6recipeapp.commands;

import lombok.Data;

/**
 * All declared field's names must match field's names in class Category
 */
@Data
public class CategoryCommand {

    public Long id;
    public String description;
}
