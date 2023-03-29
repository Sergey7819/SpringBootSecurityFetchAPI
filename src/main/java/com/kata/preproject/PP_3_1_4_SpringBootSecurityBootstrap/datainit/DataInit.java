package com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.datainit;

import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.models.Role;
import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.models.User;
import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.service.RoleService;
import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInit {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DataInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        roleService.saveRole(new Role("USER"));
        roleService.saveRole(new Role("ADMIN"));
        Integer[] usersRoles = {1};
        Integer[] adminsRoles = {1, 2};

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleService.getRoleByName("USER"));
        userService.save(new User("user", "user", 22, "user@mail.ru", "100", userRoles), usersRoles);

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(roleService.getRoleByName("ADMIN"));
        adminRoles.add(roleService.getRoleByName("USER"));
        userService.save(new User("admin", "admin", 22, "admin@gmail.com", "100", adminRoles), adminsRoles);
    }
}
