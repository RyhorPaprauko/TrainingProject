package by.itacademy.web.controller;


import by.itacademy.database.entity.enam.Genre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static by.itacademy.web.util.UrlPath.MAIN;

@Controller
public class MainPageController {

    @GetMapping(MAIN)
    public String getPage(Model model) {
        model.addAttribute("genres", Genre.values());
        return "catalog";
    }
}
