package com.jenish.quiz_service.model;

import lombok.Data;

import java.util.List;

@Data
public class QuizWrapper {
    private Integer id;
    private String code;
    private String title;
    private Integer allottedTimeInMinutes;
    List<QuestionWrapper> questions;
    List<String> options;
}
