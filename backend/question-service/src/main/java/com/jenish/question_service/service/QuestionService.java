package com.jenish.question_service.service;

import com.jenish.question_service.dao.QuestionDao;
import com.jenish.question_service.model.Answer;
import com.jenish.question_service.model.Question;
import com.jenish.question_service.model.QuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public Question getQuestionById(int id) {
        return questionDao.getQuesionById(id);
    }

    public Boolean createQuestions(List<Question> questions) {
        try{
            questionDao.saveAll(questions);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public List<Integer> generateQuestions(String category, String level, Integer numQuestions) {
        return questionDao.getQuestionByCategory(category, level, numQuestions);
    }

    public List<QuestionWrapper> getQuestions(List<Integer> ids) {
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for(Integer id : ids){
            Question question = questionDao.getQuesionById(id);
            QuestionWrapper questionWrapper = new QuestionWrapper();
            questionWrapper.setId(question.getId());
            questionWrapper.setCategory(question.getCategory());
            questionWrapper.setLevel(question.getLevel());
            questionWrapper.setQuestion(question.getQuestion());
            questionWrapper.setOptions(question.getOptions());
            questionWrappers.add(questionWrapper);
        }
        return questionWrappers;
    }

    public List<Boolean> evaluateQuestions(List<Answer> answers) {
        List<Boolean> evaluation = new ArrayList<>();
        for(Answer answer : answers){
            Question question = questionDao.getQuesionById(answer.getQuestionId());
            if(answer.getAnswer().equals(question.getAnswer())){
                evaluation.add(true);
            }
            else{
                evaluation.add(false);
            }
        }
        return evaluation;
    }
}
