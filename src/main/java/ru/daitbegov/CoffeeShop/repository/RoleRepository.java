package ru.daitbegov.CoffeeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.daitbegov.CoffeeShop.models.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role getRoleById(int id);
}
