package ru.kata.spring.boot_security.demo.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

   User findByUsername(String username);

   List<User> findAll();

   Optional<User> findById(Long userId);

   void deleteById(Long userid);
}