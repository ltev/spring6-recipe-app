package com.ltev.spring6recipeapp.controllers;

import com.ltev.spring6recipeapp.domains.Recipe;
import com.ltev.spring6recipeapp.services.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/recipes")
@AllArgsConstructor
public class RecipeController {

    private RecipeService recipeService;

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Optional<Recipe> recipe = recipeService.getRecipe(id);
        if (recipe.isPresent()) {
            model.addAttribute("recipe", recipe.get());
        } else {
            model.addAttribute("errorMsg", "Recipe not found");
        }
        return "recipes/recipe";
    }
}
