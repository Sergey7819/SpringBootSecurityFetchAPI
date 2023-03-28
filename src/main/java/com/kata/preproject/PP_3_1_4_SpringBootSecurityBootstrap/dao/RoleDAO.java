package com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.dao;

import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.models.Role;
import java.util.List;


public interface RoleDAO {
    List<Role> getAllRoles();
    Role getRoleByName(String name);
    void saveRole(Role role);
    Role getRoleById(int id);
}


