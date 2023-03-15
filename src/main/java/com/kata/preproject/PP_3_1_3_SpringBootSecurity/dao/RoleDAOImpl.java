package com.kata.preproject.PP_3_1_3_SpringBootSecurity.dao;

import com.kata.preproject.PP_3_1_3_SpringBootSecurity.models.Role;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;



@Repository
public class RoleDAOImpl implements RoleDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("FROM Role").getResultList();
    }
    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("select u from Role u where u.name =: name", Role.class)
                .setParameter("name", name).getSingleResult();
    }
    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }
}




