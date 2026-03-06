package com.jenish.quiz_service.model;

import lombok.Data;

import java.util.List;

@Data
public class RequestResult {
    private Integer score;
    private List<Answer> answers;
}
