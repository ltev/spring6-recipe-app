package com.ltev.spring6recipeapp.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String description;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
    private Set<Recipe> recipes = new HashSet<>();

    public Category(String description) {
        this.description = description;
    }

    /**
     * This method will be called automatically from:
     * Recipe -> addCategory()
     */
    void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public Set<Recipe> getRecipes() {
        return Collections.unmodifiableSet(recipes);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + description + '\'' +
                ", recipesId=" + recipes.stream().map(Recipe::getId).toList() +
                '}';
    }
}
