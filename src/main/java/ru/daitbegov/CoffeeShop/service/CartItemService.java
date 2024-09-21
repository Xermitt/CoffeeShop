package ru.daitbegov.CoffeeShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.daitbegov.CoffeeShop.models.CartItem;
import ru.daitbegov.CoffeeShop.models.User;
import ru.daitbegov.CoffeeShop.repository.CartItemRepository;
import ru.daitbegov.CoffeeShop.repository.ProductRepository;
import ru.daitbegov.CoffeeShop.security.UserDetails;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, ProductService productService,
                           ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
        this.productRepository = productRepository;
    }

    public void save(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public CartItem getCartItemByProductId(Integer id) {
        User user = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        Optional<CartItem> optional = cartItemRepository.getCartItemsByProductAndCart(productService.getProductById(id), user);
        CartItem cartItem = null;
        if (optional.isEmpty()) {
            cartItem = new CartItem();
            cartItem.setCart(user);
            cartItem.setProduct(productService.getProductById(id));
            cartItem.setQuantity(1);
            return cartItem;
        }
        cartItem = optional.get();
        cartItem.setQuantity(cartItem.getQuantity()+1);
        return cartItem;

    }
    @Transactional
    public void deleteCartItemByProductId(Integer id) {
        User user = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        cartItemRepository.deleteCartItemByProductAndCart(productService.getProductById(id), user);
    }

    public List<CartItem> getCartItemsByCart(User id){
        return cartItemRepository.getCartItemsByCart(id);
    }
    @Transactional
    public void clearUserCartItem(){
        User user = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        cartItemRepository.deleteCartItemByCart(user);
    }
}
