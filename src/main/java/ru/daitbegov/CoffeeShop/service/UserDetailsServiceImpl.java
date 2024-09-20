package ru.daitbegov.CoffeeShop.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.daitbegov.CoffeeShop.dao.UserDaoImpl;
import ru.daitbegov.CoffeeShop.models.User;
import ru.daitbegov.CoffeeShop.repository.UserRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    private UserDaoImpl userDao;


    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, UserDaoImpl userDao) {
        this.userRepository = userRepository;
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userDao.getUserByEmail(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Имя пользователя не найдено!");
        }
        User user = optionalUser.get();
        Hibernate.initialize(user.getRoleList());
        System.out.println(user);
        return new ru.daitbegov.CoffeeShop.security.UserDetails(user);
    }
}
