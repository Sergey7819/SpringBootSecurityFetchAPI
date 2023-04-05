package com.kata.preproject.PP_3_1_5_SpringBootSecurityFetchAPI.repository;

import com.kata.preproject.PP_3_1_5_SpringBootSecurityFetchAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN FETCH u.roles where u.email = :email")
    User findByUsername(@Param("email") String email);
}
