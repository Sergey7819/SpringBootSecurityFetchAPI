package com.kata.preproject.PP_3_1_2_SpringBoot.dao;

import com.kata.preproject.PP_3_1_2_SpringBoot.models.User;

import java.util.List;
public interface UserDAO {

    List<User> getAllUsers();
    User getUserById(Long id);

    void update(User user);

    void save(User user);
    User getUserByUsername (String username);
    void delete(Long id);

}

