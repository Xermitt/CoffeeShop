package ru.daitbegov.CoffeeShop.dao;


import ru.daitbegov.CoffeeShop.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void addUser(User user);
    void deleteUserById(int id);
    void updateUserById(int id, User user);
    Optional<User> getUserById(int id);
    List<User> getUsers();

    Optional<User> getUserByName(String name);
    Optional<User> getUserByEmail(String email);
}
