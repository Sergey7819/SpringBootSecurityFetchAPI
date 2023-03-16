package com.kata.preproject.PP_3_1_3_SpringBootSecurity.controllers;

import com.kata.preproject.PP_3_1_3_SpringBootSecurity.models.Role;
import com.kata.preproject.PP_3_1_3_SpringBootSecurity.models.User;
import com.kata.preproject.PP_3_1_3_SpringBootSecurity.service.RoleService;
import com.kata.preproject.PP_3_1_3_SpringBootSecurity.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getAllPeople(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new";
    }


    @PostMapping()
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(required = false) String userRole) {
//        Set<Role> roles = new HashSet<>();
        if (userRole != null && roleService.getRoleByName(userRole) != null) {
            user.setRoles(Set.of(roleService.getRoleByName(userRole)));
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roleList", roleService.getAllRoles());
        return "edit";
    }

    @PatchMapping(value = "/{id}")
    public String updateUser(@ModelAttribute("user") User updatedUser,
                             @RequestParam(value = "nameRole") String nameRole) {
        Role role = roleService.getRoleByName(nameRole);

        if (role == null) {
            role = new Role(nameRole);
            roleService.saveRole(role);
        }

        updatedUser.setRoles(Set.of(role));
        userService.update(updatedUser);

        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}

