package com.ltev.spring6recipeapp.controllers;

import com.ltev.spring6recipeapp.commands.IngredientCommand;
import com.ltev.spring6recipeapp.commands.RecipeCommand;
import com.ltev.spring6recipeapp.services.IngredientCommandService;
import com.ltev.spring6recipeapp.services.RecipeCommandService;
import com.ltev.spring6recipeapp.services.UnitOfMeasureCommandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class IngredientController {

    private final RecipeCommandService recipeService;
    private final IngredientCommandService ingredientService;
    private final UnitOfMeasureCommandService uomService;

    @GetMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable("id") Long id, Model model) {
        Optional<RecipeCommand> recipeCmd = recipeService.getById(id);
        if (recipeCmd.isPresent()) {
            model.addAttribute("recipe", recipeCmd.get());
        } else {
            model.addAttribute("errorMsg", "Recipe not found");
            throw new RuntimeException("Not found");
        }
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model) {
        Optional<IngredientCommand> ingredient = ingredientService.getById(ingredientId);
        if (ingredient.isPresent()) {
            System.out.println(ingredient.get());
            model.addAttribute("ingredient", ingredient.get());
        } else {
            throw new RuntimeException("Not found");
        }
        return "recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model) {
        Optional<RecipeCommand> recipe = recipeService.getById(recipeId);
        Optional<IngredientCommand> ingredient = ingredientService.getById(ingredientId);
        if (recipe.isPresent() && ingredient.isPresent()) {
            model.addAttribute("ingredient", ingredient.get());
            model.addAttribute("uomList", uomService.getAll());
        } else {
            throw new RuntimeException("Not found");
        }
        return "recipe/ingredient/form";
    }

    @PostMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String processForm(@ModelAttribute("ingredient") IngredientCommand ingredient,
                              @RequestParam("uomId") Long uomId) {
        ingredient = ingredientService.save(ingredient, uomId);
        return String.format("redirect:/recipe/%d/ingredient/%d/show",
                ingredient.getRecipeId(), ingredient.getId());
    }
}
