package com.ltev.spring6recipeapp.controllers;

import com.ltev.spring6recipeapp.commands.RecipeCommand;
import com.ltev.spring6recipeapp.services.RecipeCommandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/recipe")
@AllArgsConstructor
public class RecipeController {

    private RecipeCommandService recipeCommandService;

    @GetMapping("/{id}/show")
    public String showById(@PathVariable Long id, Model model) {
        Optional<RecipeCommand> recipe = recipeCommandService.getById(id);
        if (recipe.isPresent()) {
            model.addAttribute("recipe", recipe.get());
        } else {
            model.addAttribute("errorMsg", "Recipe not found");
        }
        return "recipe/show";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/form";
    }

    @PostMapping
    public String processForm(@ModelAttribute("recipe") RecipeCommand recipe, Errors errors) {
        System.out.println(errors.hasErrors());
        System.out.println(errors.getAllErrors());
        recipe = recipeCommandService.save(recipe);
        return "redirect:/recipe/" + recipe.getId() + "/show";
    }

    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeCommandService.getById(id));
        return "recipe/form";
    }

    @GetMapping("/{id}/delete")
    public String deleteRecipe(@PathVariable Long id) {
        recipeCommandService.deleteById(id);
        return "redirect:/";
    }
}
