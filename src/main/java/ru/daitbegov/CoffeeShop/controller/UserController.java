package ru.daitbegov.CoffeeShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.daitbegov.CoffeeShop.models.User;
import ru.daitbegov.CoffeeShop.repository.ProductRepository;
import ru.daitbegov.CoffeeShop.repository.UserRepository;
import ru.daitbegov.CoffeeShop.security.UserDetails;
import ru.daitbegov.CoffeeShop.service.CartItemService;
import ru.daitbegov.CoffeeShop.service.OrderService;
import ru.daitbegov.CoffeeShop.service.ProductService;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    private final ProductRepository productRepository;
    private final CartItemService cartItemService;
    private final UserRepository userRepository;
    private final OrderService orderService;
    private final ProductService productService;


    @Autowired
    public UserController(ProductRepository productRepository, CartItemService cartItemService,
                          UserRepository userRepository, OrderService orderService, ProductService productService) {
        this.productRepository = productRepository;
        this.cartItemService = cartItemService;
        this.userRepository = userRepository;
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("")
    public String user(Model model) {
        model.addAttribute("products", productRepository.findAll().stream().filter(p -> p.getCount() > 0).collect(Collectors.toList()));
        return "user/customerHome";
    }

    @PostMapping("/add/{id}")
    public String addToBusket(Model model, @PathVariable int id) {
        Integer userId = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId();
        cartItemService.save(cartItemService.getCartItemByProductId(id));
        return "redirect:/user/busket";
    }
    @PostMapping("/busket/{id}")
    public String deleteFromBusket(Model model, @PathVariable int id) {
        cartItemService.deleteCartItemByProductId(id);
        return "redirect:/user/busket";
    }

    @GetMapping("busket")
    public String getBusket(Model model) {
        User userId = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        model.addAttribute("cartItems", cartItemService.getCartItemsByCart(userId));
        model.addAttribute("sum", cartItemService.getCartItemsByCart(userId)
                .stream()
                .map(cart -> (cart.getQuantity() * cart.getProduct().getPrice())).reduce(0d, Double::sum));
        return "user/busket";
    }

    @PostMapping("busket")
    public String confirmBusket() {
        User user = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        Double sum = cartItemService.getCartItemsByCart(user)
                .stream()
                .map(cart -> (cart.getQuantity() * cart.getProduct().getPrice())).reduce(0d, Double::sum);
        orderService.saveOrderWithSumAndUser(sum, user);
        cartItemService.getCartItemsByCart(user)
                .stream()
                .forEach(cartItem ->
                        productService.decreaseProductCount(cartItem.getProduct().getId(), cartItem.getQuantity()));
        cartItemService.clearUserCartItem();
        return "redirect:/user";
    }
}
