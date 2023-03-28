package com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.controllers;

import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {
     private final UserService userService;

     public UserController(UserService userService) {
         this.userService = userService;
     }

     @GetMapping()
    public String show(Model model, Authentication authentication) {
         model.addAttribute("user", userService.getUserByUsername(authentication.getName()));
         return "user";
     }

}
