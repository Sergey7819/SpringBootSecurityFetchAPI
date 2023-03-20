package com.kata.preproject.PP_3_1_3_SpringBootSecurity.service;

import com.kata.preproject.PP_3_1_3_SpringBootSecurity.dao.UserDAO;
import com.kata.preproject.PP_3_1_3_SpringBootSecurity.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Service
@Transactional
public class UserServiceImpl implements UserService {


    private final UserDAO userDAO;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAO userDAO, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleService = roleService;
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
    public void save(User user, String userRole) {
        if (userRole != null && roleService.getRoleByName(userRole) != null) {
            user.setRoles(Set.of(roleService.getRoleByName(userRole)));
        } else {
        user.setRoles(Set.of(roleService.getRoleByName("USER")));
        }
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
