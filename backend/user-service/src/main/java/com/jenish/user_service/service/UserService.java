package com.jenish.user_service.service;

import com.jenish.user_service.dao.UserDao;
import com.jenish.user_service.model.RequestUser;
import com.jenish.user_service.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public String signUp(Users user) {

        Users userFromDB = userDao.findByEmail(user.getEmail());
        if (userFromDB != null) {
            return "user already exists.";
        }

        userDao.save(user);
        return "User created successfully";
    }

    public String login(RequestUser requestUser) {
        Users userFromDB = userDao.findByEmail(requestUser.getEmail());
        if (userFromDB == null) {
            return "user not found";
        }
        if (!(userFromDB.getPassword().equals(requestUser.getPassword()))) {
            return "invalid password";
        }
        return "login successful";
    }
}
