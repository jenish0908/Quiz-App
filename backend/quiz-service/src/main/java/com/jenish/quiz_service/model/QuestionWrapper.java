package com.jenish.quiz_service.model;

import lombok.Data;

import java.util.List;

@Data
public class QuestionWrapper {
    private Integer id;
    private String question;
    private List<String> options;
    private String category;
}
