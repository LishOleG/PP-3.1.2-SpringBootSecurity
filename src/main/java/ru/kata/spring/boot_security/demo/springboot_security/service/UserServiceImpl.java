package ru.kata.spring.boot_security.demo.springboot_security.service;


import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.springboot_security.dao.UserDao;
import ru.kata.spring.boot_security.demo.springboot_security.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User showUserById(long id) {
        return userDao.showUserById(id);
    }

    @Override
    @Transactional
    public void update(long id, User user) {
        userDao.update(id, user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    public User getUserByNameWithRoles(String name) {
        return userDao.getUserByNameWithRoles(name);
    }

    @Override
    public String bcryptPass(String pass) {
        return userDao.bcryptPass(pass);
    }
}
