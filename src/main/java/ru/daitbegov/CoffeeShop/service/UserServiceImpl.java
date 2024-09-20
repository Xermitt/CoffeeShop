package ru.daitbegov.CoffeeShop.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.daitbegov.CoffeeShop.models.User;
import ru.daitbegov.CoffeeShop.repository.RoleRepository;
import ru.daitbegov.CoffeeShop.repository.UserRepository;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository repository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setRoleList(Collections.singletonList(roleRepository.getRoleById(2)));
        repository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUserById(int id, User user) {
        user.setId(id);
        user.setRoleList(Collections.singletonList(roleRepository.getRoleById(2)));
        repository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserById(int id) {
        return repository.getUserById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return repository.findAll();
    }
}
