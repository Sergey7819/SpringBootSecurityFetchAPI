package com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.service;

import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.dao.RoleDAO;
import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.dao.UserDAO;
import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.models.Role;
import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class UserServiceImpl implements UserService {


    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
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
    public void save(User user, Integer[] userRoles) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleDAO.getRoleByName("USER"));
        if (userRoles != null) {
            Arrays.stream(userRoles).forEach(id -> roles.add(roleDAO.getRoleById(id)));
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    public void update(User user, Integer[] userRoles) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleDAO.getRoleByName("USER"));
        if (userRoles != null) {
            Arrays.stream(userRoles).forEach(id -> roles.add(roleDAO.getRoleById(id)));
        }
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info("userRoles length: {}", userRoles.length);
        user.setRoles(roles);
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
