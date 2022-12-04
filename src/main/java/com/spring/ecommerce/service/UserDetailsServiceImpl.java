package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.RegisterDTO;
import com.spring.ecommerce.model.Role;
import com.spring.ecommerce.model.RoleType;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.repository.RoleRepository;
import com.spring.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;


    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), buildSimpleGrantedAuthorities(user));
    }

    private List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(User user) {
//        List<Role> userRoles = user.getRoleList();
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for (Role role : userRoles){
//            authorities.add(new SimpleGrantedAuthority(role.getRoleType().name()));
//        }
//        return authorities;
        return user.getRoleList().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleType().name())).toList();
    }
}