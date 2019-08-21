package com.example.taxi.controller;

import com.example.taxi.domain.user.User;
import com.example.taxi.domain.user.UserService;
import com.example.taxi.dto.LoginDto;
import com.example.taxi.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDto.UserResponse saveUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public LoginDto.LoginResponse login(@RequestBody LoginDto.LoginRequest user) {
        return userService.login(user);
    }


}
