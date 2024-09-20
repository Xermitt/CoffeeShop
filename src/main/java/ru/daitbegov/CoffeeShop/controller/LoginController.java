package ru.daitbegov.CoffeeShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.daitbegov.CoffeeShop.models.User;
import ru.daitbegov.CoffeeShop.service.UserServiceImpl;
import ru.daitbegov.CoffeeShop.util.UserValidator;

import javax.validation.Valid;

@Controller
public class LoginController {
    private final UserValidator userValidator;
    private final UserServiceImpl userService;

    public LoginController(UserValidator userValidator, UserServiceImpl userServiceImpl) {
        this.userValidator = userValidator;
        this.userService = userServiceImpl;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/reg")
    public String reg(@ModelAttribute("user") User user) {
        return "auth/reg";
    }

    @PostMapping("/reg")
    public String register(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/auth/reg";
        }
        userService.addUser(user);
        return "redirect:/login";
    }

}
