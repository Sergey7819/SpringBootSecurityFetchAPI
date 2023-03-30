package com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.controllers;

import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.models.Role;
import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.models.User;
import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.service.RoleService;
import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RoleService roleService;
    private final UserService userService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String adminPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }


    @PutMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "roles", required = false) Integer[] userRoles) {
        userService.update(user, userRoles);
        return "redirect:/admin";
    }


    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute(new User());
        Map<Role, Boolean> roles = new TreeMap<>(Comparator.comparing(Role::getId));
        roleService.getAllRoles().forEach(r -> roles.put(r, false));
        model.addAttribute("roles", roles);
        return "new";
    }



    @PostMapping()
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "roles", required = false) Integer[] userRoles) {
        userService.save(user, userRoles);
        return "redirect:/admin";
    }
}



