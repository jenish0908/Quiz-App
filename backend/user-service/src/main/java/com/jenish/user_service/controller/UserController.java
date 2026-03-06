package com.jenish.user_service.controller;

import com.jenish.user_service.model.RequestUser;
import com.jenish.user_service.model.Users;
import com.jenish.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("signup")
    public String signUp(@RequestBody Users user){
        return userService.signUp(user);
    }

    @PostMapping("login")
    public String login(@RequestBody RequestUser requestUser){
        return userService.login(requestUser);
    }


}
