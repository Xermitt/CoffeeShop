package ru.daitbegov.CoffeeShop.models;

import lombok.*;
import org.springframework.stereotype.Component;
import ru.daitbegov.CoffeeShop.annotations.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Component
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name="discount_percentage")
    private Double discount_percentage = 0.0;


    @Column(name = "phone")
    @Pattern(regexp="^(8)[0-9]{10}$", message = "Номер должен быть формата 81234567890")
    private String phone;

    @Column(name = "email")
    @Email(message = "Не верно указан email!")
    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(min = 1, max = 60, message = "Email должен содержать более 6 символов")
    private String email;

    @Column(name = "password")
    @Size(min = 1, max = 45, message = "Неверный формат пароля!")
    @NotEmpty(message = "Поле не может быть пустым!")
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "id_users", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_roles", referencedColumnName = "id")
    )
    private List<Role> roleList;
}

