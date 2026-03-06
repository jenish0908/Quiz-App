package com.jenish.quiz_service.feign;

import com.jenish.quiz_service.model.Answer;
import com.jenish.quiz_service.model.QuestionWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("question/generate")
    public List<Integer> generateQuestions(@RequestParam String category, @RequestParam String level, @RequestParam Integer numQuestions);

    @PostMapping("question/getQuestions")
    public List<QuestionWrapper> getQuestions(@RequestBody List<Integer> ids);

    @PostMapping("question/evaluate")
    public List<Boolean> evaluateQuestions(@RequestBody List<Answer> answers);
}
