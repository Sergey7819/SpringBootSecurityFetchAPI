package com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.service;

import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.dao.RoleDAO;
import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.models.Role;
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

    @Override
    public Role getRoleById(int id) {
        return roleDAO.getRoleById(id);
    }


}
