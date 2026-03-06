package com.jenish.quiz_service.dao;

import com.jenish.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
    Quiz getQuizById(Integer id);
}
