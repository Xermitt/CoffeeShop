package ru.daitbegov.CoffeeShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.daitbegov.CoffeeShop.models.Order;
import ru.daitbegov.CoffeeShop.models.User;
import ru.daitbegov.CoffeeShop.repository.OrderRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    public final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public List<Order> getOrdersByUser(User userId) {
        return orderRepository.getOrOrderByUserId(userId);
    }

    public void saveOrderWithSumAndUser(Double sum, User user) {
        orderRepository.save(new Order(user, sum.intValue(), new Timestamp(new Date().getTime())));
    }
}
