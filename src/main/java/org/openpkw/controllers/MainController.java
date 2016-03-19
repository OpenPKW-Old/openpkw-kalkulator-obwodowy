package org.openpkw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/main")
    public String showRestaurantPreferences(Model model) {
        model.addAttribute("test", "test text");
        return "main";
    }

}