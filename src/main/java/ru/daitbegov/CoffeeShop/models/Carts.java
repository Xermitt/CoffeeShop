package ru.daitbegov.CoffeeShop.models;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Component
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Carts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> items = new ArrayList<>();
}
