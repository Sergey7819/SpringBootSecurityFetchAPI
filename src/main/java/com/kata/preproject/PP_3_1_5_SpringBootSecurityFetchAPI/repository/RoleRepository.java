package com.kata.preproject.PP_3_1_5_SpringBootSecurityFetchAPI.repository;

import com.kata.preproject.PP_3_1_5_SpringBootSecurityFetchAPI.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
