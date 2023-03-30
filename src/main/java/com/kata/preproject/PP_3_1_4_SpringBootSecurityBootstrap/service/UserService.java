package com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.service;

import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.models.User;

import java.util.List;

public interface UserService {
    void save(User user, Integer[] userRoles);
    List<User> getAllUsers();
    User getUserById(Long id);
    void update(User user, Integer[] userRoles);
    void delete(Long id);
    User getUserByUsername(String username);

}


