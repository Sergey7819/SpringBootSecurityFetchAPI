package com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.service;

import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.dao.UserDAO;
import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@Transactional
public class UserServiceImpl implements UserService {


    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    public void update(User user) {
        if(!getUserById(user.getId()).getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDAO.update(user);
    }
    @Override
    public void delete(Long id) {
        userDAO.delete(id);
    }

}
