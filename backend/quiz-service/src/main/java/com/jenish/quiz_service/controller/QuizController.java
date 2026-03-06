package com.jenish.quiz_service.controller;

import com.jenish.quiz_service.dao.QuizDao;
import com.jenish.quiz_service.model.Answer;
import com.jenish.quiz_service.model.Quiz;
import com.jenish.quiz_service.model.QuizWrapper;
import com.jenish.quiz_service.model.RequestQuiz;
import com.jenish.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("health")
    public Boolean health(){
        return true;
    }

    @GetMapping("{id}")
    public QuizWrapper getQuizById(@PathVariable Integer id){
        return quizService.getQuizById(id);
    }

    @PostMapping("create")
    public Boolean createQuiz(@RequestBody RequestQuiz requestQuiz){
        return quizService.createQuiz(requestQuiz);
    }

    @PostMapping("submit/{id}")
    public Integer submitQuiz(@PathVariable Integer id, @RequestBody List<Answer> answers){
        return quizService.submitQuiz(id, answers);
    }
}
