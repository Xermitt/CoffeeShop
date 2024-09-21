package ru.daitbegov.CoffeeShop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
    @Column(name = "cost")
    private int cost;
    @Column(name = "date")
    private Timestamp date;

    public Order(User userId, int cost, Timestamp date) {
        this.userId = userId;
        this.cost = cost;
        this.date = date;
    }
}
