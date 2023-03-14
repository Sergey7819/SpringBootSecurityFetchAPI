package com.kata.preproject.PP_3_1_2_SpringBoot.service;

import com.kata.preproject.PP_3_1_2_SpringBoot.models.Role;

import java.util.List;


public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleByName(String name);
    void saveRole(Role role);
}

