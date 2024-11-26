package com.ltev.spring6recipeapp.controllers;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"", "/", "index", "index.html"})
public class IndexController {

    @GetMapping
    public String index() {
        return "index";
    }
}
