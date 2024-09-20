package ru.daitbegov.CoffeeShop.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@Entity
@Table(name = "Products")
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    @Size(min = 1, max = 100, message = "Не должен содержать более 100 символов")
    private String description;

    @Column(name = "price")
    @Min(value = 1, message = "Не может быть меньше 0")
    private Double price;

    @Column(name = "category")
    @Size(min = 1, max = 60, message = "Не должен содержать более 60 символов")
    private String category;

    @Column(name = "count")
    @Min(value = 1, message = "Не может быть меньше 0")
    private Integer count;
    @Column(name = "image")
    private String image;
}
