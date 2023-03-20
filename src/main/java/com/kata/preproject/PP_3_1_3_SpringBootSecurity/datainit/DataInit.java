package com.kata.preproject.PP_3_1_3_SpringBootSecurity.datainit;

import com.kata.preproject.PP_3_1_3_SpringBootSecurity.models.Role;
import com.kata.preproject.PP_3_1_3_SpringBootSecurity.models.User;
import com.kata.preproject.PP_3_1_3_SpringBootSecurity.service.RoleService;
import com.kata.preproject.PP_3_1_3_SpringBootSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void initialization() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);

        User admin = new User("admin", 34, "admin@gmail.com", "100", Set.of(roleAdmin, roleUser));
        userService.save(admin, "ROLE_ADMIN");

        User user = new User("user", 22, "user@gmail.com",  "100", Set.of(roleUser));
        userService.save(user, "ROLE_USER");
    }
}
