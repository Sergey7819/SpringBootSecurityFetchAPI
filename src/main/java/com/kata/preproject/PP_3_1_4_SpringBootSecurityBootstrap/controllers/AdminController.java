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

    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    @PutMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "roles", required = false) Integer[] userRoles) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("USER"));
        if (userRoles != null) {
            Arrays.stream(userRoles).forEach(id -> roles.add(roleService.getRoleById(id)));
        }
        System.out.println(userRoles.length);
        user.setRoles(roles);
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute(new User());
        Map<Role, Boolean> roles = new TreeMap<>((Comparator<Role>) this :: sortingRole);
        roleService.getAllRoles().forEach(r -> roles.put(r, false));
        model.addAttribute("roles", roles);
        return "new";

    }


    @PostMapping()
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "roles", required = false) Integer[] userRoles) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("USER"));
        if (userRoles != null) {
            Arrays.stream(userRoles).forEach(id -> roles.add(roleService.getRoleById(id)));
        }
        user.setRoles(roles);
        userService.save(user);
        return "redirect:/admin";
    }

    private int sortingRole(Role r1, Role r2) {
        return (int) (r1.getId() - r2.getId());
    }

}
