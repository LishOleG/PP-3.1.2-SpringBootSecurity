//package ru.kata.spring.boot_security.demo.repositories;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import ru.kata.spring.boot_security.demo.entities.User;
//
//public interface UserRepository extends JpaRepository<User,Long> {
//
//    @Query("SELECT u FROM User u left join fetch u.roles WHERE u.name = :username")
//    User findByUsername (@Param("username") String username);
//
//
//}
