package com.ltev.spring6recipeapp.domains;

import com.ltev.spring6recipeapp.converters.using_annotations.Alias;
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
    @Alias("id")
    private Long id;

    @Getter
    @Setter
    @Alias("des")
    private String description;

    @Getter
    @Setter
    @Alias("prepTime")
    private Integer prepTime;

    @Getter
    @Setter
    @Alias("cookTime")
    private Integer cookTime;

    @Getter
    @Setter
    @Alias("servings")
    private Integer servings;

    @Getter
    @Setter
    @Alias("source")
    private String source;

    @Getter
    @Setter
    @Alias("url")
    private String url;

    @Lob
    @Getter
    @Setter
    @Alias("directions")
    private String directions;

    @Lob
    @Getter
    @Setter
    @Alias("image")
    private Byte[] image;

//    New relational table:
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "recipe_ingredient",
//            joinColumns = @JoinColumn(name = "recipe_id"),
//            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))

//     @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)  // by default new table : recipe_ingredients

    @OneToMany(mappedBy="recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER)   // mapped by - no new table needed
    @Alias("ingredients")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Enumerated(EnumType.ORDINAL)
    @Getter
    @Setter
    @Alias("difficulty")
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    @Getter
    @Alias("note")
    private Note note;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable( name = "recipe_category",
                joinColumns = @JoinColumn(name = "recipe_id"),              // recipies_id - by default
                inverseJoinColumns = @JoinColumn(name = "category_id"))     // categories_id - by default
    @Alias("categories")
    private Set<Category> categories = new HashSet<>();


    public Set<Ingredient> getIngredients() {
        return Collections.unmodifiableSet(ingredients);
    }

    public Recipe addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        ingredient.setRecipe(this);
        return this;
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public Recipe addCategory(Category category) {
        categories.add(category);
        category.addRecipe(this);
        return this;
    }

    public void setNote(Note note) {
        this.note = note;
        note.setRecipe(this);
    }
}
