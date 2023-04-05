package com.kata.preproject.PP_3_1_5_SpringBootSecurityFetchAPI.service;


import com.kata.preproject.PP_3_1_5_SpringBootSecurityFetchAPI.models.Role;
import com.kata.preproject.PP_3_1_5_SpringBootSecurityFetchAPI.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }
}



