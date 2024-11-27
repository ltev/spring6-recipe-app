package com.ltev.spring6recipeapp.controllers;

import com.ltev.spring6recipeapp.domains.Recipe;
import com.ltev.spring6recipeapp.repositories.RecipeRepository;
import com.ltev.spring6recipeapp.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"", "/", "index", "index.html"})
@AllArgsConstructor
public class IndexController {

    private RecipeService recipeService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
