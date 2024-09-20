package ru.daitbegov.CoffeeShop.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.daitbegov.CoffeeShop.models.User;
import ru.daitbegov.CoffeeShop.repository.UserRepository;


@Component
public class UserValidator implements Validator {
    private final UserRepository userRepository;
    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(userRepository.getUserByEmail(user.getEmail()).isPresent()) {
            if(user.getId()==0 || !(user.getId() == userRepository.getUserByEmail(user.getEmail()).get().getId())) {
                errors.rejectValue("email", null, "Email уже занят!");
            }
        }
    }
}
