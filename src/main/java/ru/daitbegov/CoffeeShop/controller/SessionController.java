package ru.daitbegov.CoffeeShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.daitbegov.CoffeeShop.annotations.Check;
import ru.daitbegov.CoffeeShop.models.User;

@Controller
public class SessionController {
    @GetMapping("/api")
    @Check
    public String make(@RequestBody User user, Model model){
        model.addAttribute("user", user);
        return "/api";
    }
}
