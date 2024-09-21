package ru.daitbegov.CoffeeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.daitbegov.CoffeeShop.models.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Modifying
    @Transactional
    @Query("update Product p set p.count = p.count - :amount WHERE p.id = :productId and p.count > 0 ")
    void decreaseProductCount(Integer productId, Integer amount);
}
