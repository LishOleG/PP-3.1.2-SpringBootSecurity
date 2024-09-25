package ru.kata.spring.boot_security.demo.init;


import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.DAO.RoleDao;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;

@Component
public class AddUseAndRoleInTable {

    private  final RoleDao roleDao;
    private  final UserService userService;

    public AddUseAndRoleInTable(RoleDao roleDao, UserService userService) {
        this.roleDao = roleDao;
        this.userService = userService;
    }

    @PostConstruct
    private  void  init() {
        roleDao.saveAndFlush(new Role(1L, "ROLE_ADMIN"));
        roleDao.saveAndFlush(new Role(2L, "ROLE_USER"));

        //List<Role> adminRole = roleDao.findById(1L).stream().toList();
        //List<Role> userRole = roleDao.findById(2L).stream().;

        userService.saveUser(new User("Adam", 13, "adam4ik@mail.com"));
        userService.saveUser(new User("Alice", 9, "alicka@mail.com"));
    }

}
