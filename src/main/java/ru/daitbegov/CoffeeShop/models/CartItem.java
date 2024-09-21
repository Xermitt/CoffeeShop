package ru.daitbegov.CoffeeShop.models;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "cart_items")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User cart;
    @Column(name = "quantity")
    private int quantity;

    public CartItem(Product product, User cart, int quantity) {
        this.product = product;
        this.cart = cart;
        this.quantity = quantity;
    }
}
