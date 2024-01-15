package com.alame.lab4.controller;

import com.alame.lab4.model.User;
import com.alame.lab4.model.DefaultPostResponse;
import com.alame.lab4.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(originPatterns = "http://localhost:29381")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/users/register")
    public DefaultPostResponse register(@RequestBody User user){
        return userService.register(user);
    }
    @PostMapping("/users/authorize")
    public DefaultPostResponse authorize(@RequestBody User user){
        return userService.authorize(user);
    }
}
