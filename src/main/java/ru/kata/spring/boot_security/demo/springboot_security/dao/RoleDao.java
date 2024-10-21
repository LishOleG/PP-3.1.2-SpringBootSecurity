package ru.kata.spring.boot_security.demo.springboot_security.dao;


import ru.kata.spring.boot_security.demo.springboot_security.model.Role;

import java.util.List;

public interface RoleDao {

    List<Role> getRolesByIds (List<Long> ids);
}
