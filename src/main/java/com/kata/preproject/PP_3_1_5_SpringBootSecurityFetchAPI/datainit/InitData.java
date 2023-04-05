package com.kata.preproject.PP_3_1_5_SpringBootSecurityFetchAPI.datainit;

import com.kata.preproject.PP_3_1_5_SpringBootSecurityFetchAPI.models.Role;
import com.kata.preproject.PP_3_1_5_SpringBootSecurityFetchAPI.models.User;
import com.kata.preproject.PP_3_1_5_SpringBootSecurityFetchAPI.repository.RoleRepository;
import com.kata.preproject.PP_3_1_5_SpringBootSecurityFetchAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InitData {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitData(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void postConstruct() {
        Role admin_role = new Role();
        admin_role.setId(1L);
        admin_role.setRole("ROLE_ADMIN");

        Role user_role = new Role();
        user_role.setId(2L);
        user_role.setRole("ROLE_USER");

        roleRepository.saveAll(List.of(admin_role, user_role));

        User user = new User();
        user.setName("User");
        user.setLastName("User");
        user.setEmail("user@mail.ru");
        user.setAge((byte)30);
        user.setPassword(passwordEncoder.encode("100"));
        user.addRole(user_role);

        User admin = new User();
        admin.setName("Admin");
        admin.setLastName("Admin");
        admin.setEmail("admin@gmail.com");
        admin.setAge((byte)35);
        admin.setPassword(passwordEncoder.encode("100"));
        admin.addRole(admin_role);

        userRepository.save(admin);
        userRepository.save(user);
    }
}


