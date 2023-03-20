package com.kata.preproject.PP_3_1_3_SpringBootSecurity.service;

import com.kata.preproject.PP_3_1_3_SpringBootSecurity.models.Role;
import com.kata.preproject.PP_3_1_3_SpringBootSecurity.models.User;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleByName(String name);
    void saveRole(Role role);
    public Role determineUserRole(User user);
}

