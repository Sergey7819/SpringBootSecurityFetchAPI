package com.kata.preproject.PP_3_1_2_SpringBoot.service;


import com.kata.preproject.PP_3_1_2_SpringBoot.dao.RoleDAO;
import com.kata.preproject.PP_3_1_2_SpringBoot.dao.UserDAO;
import com.kata.preproject.PP_3_1_2_SpringBoot.models.Role;
import com.kata.preproject.PP_3_1_2_SpringBoot.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, BCryptPasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);

    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    public void update(User user) {
        if(!getUserById(user.getId()).getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDAO.update(user);
    }


    @Override
    public void delete(Long id) {
        userDAO.delete(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with username %s not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
