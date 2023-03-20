package com.kata.preproject.PP_3_1_3_SpringBootSecurity.service;

import com.kata.preproject.PP_3_1_3_SpringBootSecurity.dao.RoleDAO;
import com.kata.preproject.PP_3_1_3_SpringBootSecurity.models.Role;
import com.kata.preproject.PP_3_1_3_SpringBootSecurity.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }
    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }
    @Override
    public Role getRoleByName(String name) {
        return roleDAO.getRoleByName(name);
    }
    @Override
    public void saveRole(Role role) {
        roleDAO.saveRole(role);
    }

    public Role determineUserRole(User user) {
        if (user.getRoles() != null && roleDAO.getRoleByName("ADMIN") != null) {
            return roleDAO.getRoleByName("ADMIN");
        } else {
            return roleDAO.getRoleByName("USER");
        }
    }

}
