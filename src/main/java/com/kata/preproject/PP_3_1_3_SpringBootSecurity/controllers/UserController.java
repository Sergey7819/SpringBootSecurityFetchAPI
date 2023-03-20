package com.kata.preproject.PP_3_1_3_SpringBootSecurity.controllers;


import com.kata.preproject.PP_3_1_3_SpringBootSecurity.models.Role;
import com.kata.preproject.PP_3_1_3_SpringBootSecurity.models.User;
import com.kata.preproject.PP_3_1_3_SpringBootSecurity.service.RoleService;
import com.kata.preproject.PP_3_1_3_SpringBootSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user) {
        userService.save(user, "ROLE_USER");
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String getUserPage(Model model, Principal principal) {
        Long id = userService.getUserByUsername(principal.getName()).getId();
        model.addAttribute("user", userService.getUserById(id));
        return "/user";
    }

}