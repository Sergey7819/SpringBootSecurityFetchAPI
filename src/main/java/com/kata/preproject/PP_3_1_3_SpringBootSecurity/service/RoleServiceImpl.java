package com.kata.preproject.PP_3_1_2_SpringBoot.service;

import com.kata.preproject.PP_3_1_2_SpringBoot.dao.RoleDAO;
import com.kata.preproject.PP_3_1_2_SpringBoot.models.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

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
}
