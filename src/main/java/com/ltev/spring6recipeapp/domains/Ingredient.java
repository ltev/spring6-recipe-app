package com.ltev.spring6recipeapp.domains;

import java.math.BigDecimal;

public class Ingredient {

    private Long id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasure uom;
    private Recipe recipe;
}
