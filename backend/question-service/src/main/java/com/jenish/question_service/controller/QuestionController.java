package com.jenish.question_service.controller;

import com.jenish.question_service.model.Answer;
import com.jenish.question_service.model.Question;
import com.jenish.question_service.model.QuestionWrapper;
import com.jenish.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("health")
    public Boolean health(){
        return true;
    }

    @GetMapping("{id}")
    public Question findQuestionById(@PathVariable int id){
        return questionService.getQuestionById(id);
    }

    @PostMapping("create")
    public Boolean createQuestions(@RequestBody List<Question> questions){
        return questionService.createQuestions(questions);
    }

    @GetMapping("generate")
    public List<Integer> generateQuestions(@RequestParam String category, @RequestParam String level,@RequestParam Integer numQuestions){
        return questionService.generateQuestions(category, level, numQuestions);
    }

    @PostMapping("getQuestions")
    public List<QuestionWrapper> getQuestions(@RequestBody List<Integer> ids){
        return questionService.getQuestions(ids);
    }

    @PostMapping("evaluate")
    public List<Boolean> evaluateQuestions(@RequestBody List<Answer> answers){
        return questionService.evaluateQuestions(answers);
    }
}
