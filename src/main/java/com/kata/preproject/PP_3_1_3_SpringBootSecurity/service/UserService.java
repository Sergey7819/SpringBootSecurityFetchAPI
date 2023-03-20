package com.kata.preproject.PP_3_1_3_SpringBootSecurity.service;

import com.kata.preproject.PP_3_1_3_SpringBootSecurity.models.Role;
import com.kata.preproject.PP_3_1_3_SpringBootSecurity.models.User;

import java.util.List;

public interface UserService {
    void save(User user, String userRole);
    List<User> getAllUsers();
    User getUserById(Long id);
    void update(User user);
    void delete(Long id);
    User getUserByUsername(String username);

}


