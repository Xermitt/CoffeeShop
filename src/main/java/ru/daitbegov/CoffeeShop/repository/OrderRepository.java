package ru.daitbegov.CoffeeShop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.daitbegov.CoffeeShop.models.Order;
import ru.daitbegov.CoffeeShop.models.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> getOrOrderByUserId(User user);
}
