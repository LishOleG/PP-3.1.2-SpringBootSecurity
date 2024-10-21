package ru.kata.spring.boot_security.demo.springboot_security.service;


import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.springboot_security.dao.RoleDao;
import ru.kata.spring.boot_security.demo.springboot_security.model.Role;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public List<Role> getRolesByIds(List<Long> ids) {

        return roleDao.getRolesByIds(ids);
    }
}
