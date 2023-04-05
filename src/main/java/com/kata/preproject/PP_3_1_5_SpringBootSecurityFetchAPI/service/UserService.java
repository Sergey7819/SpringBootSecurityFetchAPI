package com.kata.preproject.PP_3_1_5_SpringBootSecurityFetchAPI.service;

import com.kata.preproject.PP_3_1_5_SpringBootSecurityFetchAPI.models.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();
    User getUserById(Long id);
    void createUser(User user);
    void deleteUserById(Long id);
    User findByUsername(String username);
    void editUser(User user);
}


