package com.ltev.spring6recipeapp.domains;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;

    /*
     * When using @OneToOne - exception from database (private key violation),
     * when trying to save uom with the same id for the second time
     */
    @ManyToOne(cascade = CascadeType.DETACH)
    private UnitOfMeasure uom;

    // In new table
    // @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ManyToOne              // creates new column recipe_id in table Ingredient
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UnitOfMeasure getUom() {
        return uom;
    }

    public void setUom(UnitOfMeasure uom) {
        this.uom = uom;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    /**
     * This method will be called automatically from:
     * Recipe -> addIngredient()
     */
    void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", uom=" + uom +
                ", recipeId=" + (recipe != null ? recipe.getId() : null) +
                '}';
    }
}
