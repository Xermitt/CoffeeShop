package ru.daitbegov.CoffeeShop.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    private int id;
    @Column(name = "role")
    private String role;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
//    public String getShortRole() {
//        return role.replaceAll("ROLE_", "");
//    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }

}
