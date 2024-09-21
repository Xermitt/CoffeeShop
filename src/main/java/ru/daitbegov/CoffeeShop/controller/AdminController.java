package ru.daitbegov.CoffeeShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.daitbegov.CoffeeShop.models.Product;
import ru.daitbegov.CoffeeShop.repository.OrderRepository;
import ru.daitbegov.CoffeeShop.repository.ProductRepository;
import ru.daitbegov.CoffeeShop.repository.UserRepository;
import ru.daitbegov.CoffeeShop.service.ProductService;
import ru.daitbegov.CoffeeShop.util.UserValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductRepository productRepository;
    private final UserValidator userValidator;
    private final ProductService productService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public AdminController(ProductRepository productRepository, UserValidator userValidator, ProductService productService, UserRepository userRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.userValidator = userValidator;
        this.productService = productService;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "admin/homePage";
    }
    @GetMapping("/products/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("product", productRepository.getById(id));
        return "admin/update";
    }

    @PostMapping("/products/update/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute("product") @Valid Product product,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "/admin/update";
        }
        productService.updateById(id, product);
        return "redirect:/admin/products/update/"+id;
    }

    @PostMapping("/products/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        productService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/products/create")
    public String showCreate(@ModelAttribute("product") Product product) {
        return "admin/create";
    }
    @PostMapping("/products/create")
    public String create(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/create";
        }
        productRepository.save(product);
        return "redirect:/admin";
    }
    @GetMapping("/customers")
    public String showCustomers(Model model) {
        model.addAttribute("customers",userRepository.findAll());
        return "/admin/customers";
    }

    @GetMapping("/customers/{id}")
    public String showCustomers(Model model, @PathVariable int id) {
        model.addAttribute("orders",orderRepository.getOrOrderByUserId(userRepository.getUserById(id).get()));
        return "/admin/customers-orders";
    }
}
