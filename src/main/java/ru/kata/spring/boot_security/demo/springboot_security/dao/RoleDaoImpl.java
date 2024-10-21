package ru.kata.spring.boot_security.demo.springboot_security.dao;


import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.springboot_security.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getRolesByIds(List<Long> ids) {
        return entityManager.createQuery("SELECT DISTINCT r FROM Role r WHERE r.id IN :ids", Role.class).setParameter("ids", ids).getResultList();
    }
}
