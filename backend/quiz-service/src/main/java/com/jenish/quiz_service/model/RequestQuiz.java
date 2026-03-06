package com.jenish.quiz_service.model;

import lombok.Data;

@Data
public class RequestQuiz {
    private String title;
    private Integer allottedTimeInMinutes;
    private String category;
    private String level;
    private Integer numQuestions;
}
