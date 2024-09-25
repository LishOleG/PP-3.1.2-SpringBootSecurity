package ru.kata.spring.boot_security.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.UserDao;
import ru.kata.spring.boot_security.demo.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entiman;

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> userFromDB = userDao.findById(userId);
        return userFromDB.orElse(new User());
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setEmail(new BCryptPasswordEncoder().encode(user.getPassword())); //тут вместо setPassport->setEmail
        user.setRoles(user.getRoles());
        userDao.saveAndFlush(user);//тут IDEA предложила saveandFlush вместо save
    }


    @Override
    @Transactional
    public boolean updateUser(User user) {
        Optional<User> userFromDB = userDao.findById(user.getId());
        if (userFromDB.isPresent()) {
            userFromDB.get().setName(user.getName());
        }
        userFromDB.get().setEmail(user.getPassword());
        userDao.saveAndFlush(userFromDB.get());
        return true;
    }

    @Override
    @Transactional
    public boolean deleteUser(Long userid) {
        if (userDao.findById(userid).isPresent()) {
            userDao.deleteById(userid);
            return true;
        }
        return false;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}
