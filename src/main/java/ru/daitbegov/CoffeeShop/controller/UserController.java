package ru.daitbegov.CoffeeShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.daitbegov.CoffeeShop.models.Carts;
import ru.daitbegov.CoffeeShop.models.Product;
import ru.daitbegov.CoffeeShop.repository.CartRepository;
import ru.daitbegov.CoffeeShop.repository.ProductRepository;

@Controller
@RequestMapping("/user")
public class UserController {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public UserController(ProductRepository productRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    @GetMapping("")
    public String user(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "user/customerHome";
    }
}
