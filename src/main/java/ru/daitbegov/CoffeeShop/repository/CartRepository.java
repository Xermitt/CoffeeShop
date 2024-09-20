package ru.daitbegov.CoffeeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.daitbegov.CoffeeShop.models.Carts;

@Repository
public interface CartRepository extends JpaRepository<Carts, Integer> {
}