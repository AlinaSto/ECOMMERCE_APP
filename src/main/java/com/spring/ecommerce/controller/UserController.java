package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.AddUserDTO;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/add")
//    public User addUser(@RequestBody AddUserDTO addUserDTO) {
//       return userService.addUser(addUserDTO);
//    }
}