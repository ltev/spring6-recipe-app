package com.ltev.spring6recipeapp.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Integer prepTime;

    @Getter
    @Setter
    private Integer cookTime;

    @Getter
    @Setter
    private Integer servings;

    @Getter
    @Setter
    private String source;

    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    private String directions;

//    New relational table:
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "recipe_ingredient",
//            joinColumns = @JoinColumn(name = "recipe_id"),
//            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))

//     @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)  // by default new table : recipe_ingredients

    @OneToMany(mappedBy="recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER)   // mapped by - no new table needed
    private Set<Ingredient> ingredients = new HashSet<>();

    @Enumerated(EnumType.ORDINAL)
    @Getter
    @Setter
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    @Getter
    @Setter
    private Note note;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable( name = "recipe_category",
                joinColumns = @JoinColumn(name = "recipe_id"),
                inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();


    public Set<Ingredient> getIngredients() {
        return Collections.unmodifiableSet(ingredients);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        ingredient.setRecipe(this);
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public void addCategory(Category category) {
        categories.add(category);
        category.addRecipe(this);
    }
}
