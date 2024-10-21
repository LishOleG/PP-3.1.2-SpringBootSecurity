package ru.kata.spring.boot_security.demo.springboot_security.service;


import ru.kata.spring.boot_security.demo.springboot_security.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void save(User user);

    User showUserById(long id);

    void update(long id, User user);

    void delete(long id);

    User getUserByNameWithRoles(String name);

    String bcryptPass(String pass);
}
