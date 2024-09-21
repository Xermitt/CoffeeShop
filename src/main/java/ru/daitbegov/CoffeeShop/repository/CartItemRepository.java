package ru.daitbegov.CoffeeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.daitbegov.CoffeeShop.models.CartItem;
import ru.daitbegov.CoffeeShop.models.Product;
import ru.daitbegov.CoffeeShop.models.User;


import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Optional<CartItem> getCartItemsByProductAndCart(Product product, User cart);
    List<CartItem> getCartItemsByCart(User id);
    void deleteCartItemByProductAndCart(Product productId, User cart);
    void deleteCartItemByCart(User id);
}
