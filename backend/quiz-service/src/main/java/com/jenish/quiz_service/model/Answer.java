package com.jenish.quiz_service.model;

import lombok.Data;

@Data
public class Answer {
    private Integer questionId;
    private String answer;
}
