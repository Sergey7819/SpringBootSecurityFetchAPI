package com.kata.preproject.PP_3_1_5_SpringBootSecurityFetchAPI.service;

import com.kata.preproject.PP_3_1_5_SpringBootSecurityFetchAPI.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllRole();
}

