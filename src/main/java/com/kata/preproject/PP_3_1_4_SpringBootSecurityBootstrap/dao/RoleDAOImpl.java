package com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.dao;

import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.models.Role;
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
        return entityManager.createQuery("FROM Role", Role.class).getResultList();
    }
    @Override
    public Role getRoleByName(String roleName) {
        return entityManager.createQuery("select u from Role u where u.roleName =: roleName", Role.class)
                .setParameter("roleName", roleName).getSingleResult();
    }

    @Override
    public Role getRoleById(int id) {
        return entityManager.createQuery("SELECT u FROM Role u WHERE u.id = :id", Role.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }
}




