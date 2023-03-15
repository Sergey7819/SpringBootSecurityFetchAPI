package com.kata.preproject.PP_3_1_3_SpringBootSecurity.dao;

import com.kata.preproject.PP_3_1_3_SpringBootSecurity.models.User;
import java.util.List;
public interface UserDAO {

    List<User> getAllUsers();
    User getUserById(Long id);

    void update(User user);

    void save(User user);
    User getUserByUsername (String username);
    void delete(Long id);

}

