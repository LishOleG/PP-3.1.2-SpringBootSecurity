package ru.kata.spring.boot_security.demo.springboot_security.service;


import ru.kata.spring.boot_security.demo.springboot_security.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRolesByIds (List<Long> ids);
}
