package com.jenish.question_service.dao;

import com.jenish.question_service.model.Question;
import com.jenish.question_service.model.QuestionWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question,Integer> {
    Question getQuesionById(int id);

    @Query(value = "SELECT q.id FROM question q WHERE q.category=:category AND q.level=:level ORDER BY RANDOM() LIMIT :numQuestion", nativeQuery = true)
    List<Integer> getQuestionByCategory(String category, String level, Integer numQuestion);
}

