package ru.kata.spring.boot_security.demo.springboot_security.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.springboot_security.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setbCryptPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void save(User user) {
        String passBCrypt = bcryptPass(user.getPassword());
        user.setPassword(passBCrypt);
        entityManager.persist(user);
    }

    @Override
    public User showUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(long id, User updatedUser) {
        String passBCrypt = bcryptPass(updatedUser.getPassword());
        updatedUser.setPassword(passBCrypt);
        entityManager.merge(updatedUser);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(showUserById(id));
    }

    @Override
    public User getUserByNameWithRoles(String email) {
        return entityManager.createQuery("SELECT u FROM User u join fetch u.roles WHERE u.email=:email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public String bcryptPass(String pass) {
        return passwordEncoder.encode(pass);
    }
}
