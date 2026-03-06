package com.jenish.user_service.dao;

import com.jenish.user_service.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<Users, Integer> {
    Users findByEmail(String email);
}
