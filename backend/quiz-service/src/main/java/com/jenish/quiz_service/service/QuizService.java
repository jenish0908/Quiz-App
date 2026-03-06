package com.jenish.quiz_service.service;

import com.jenish.quiz_service.dao.QuizDao;
import com.jenish.quiz_service.feign.QuizInterface;
import com.jenish.quiz_service.feign.ResultInterface;
import com.jenish.quiz_service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BooleanSupplier;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;
    @Autowired
    QuizInterface quizInterface;
    @Autowired
    ResultInterface resultInterface;

    public QuizWrapper getQuizById(Integer id) {
        Quiz quiz = quizDao.getQuizById(id);
        QuizWrapper quizWrapper = new QuizWrapper();
        quizWrapper.setId(quiz.getId());
        quizWrapper.setTitle(quiz.getTitle());
        quizWrapper.setCode(quiz.getCode());
        quizWrapper.setAllottedTimeInMinutes(quiz.getAllottedTimeInMinutes());
        quizWrapper.setQuestions(getQuizQuestions(id));
        return quizWrapper;
    }

    public List<QuestionWrapper> getQuizQuestions(Integer id) {
        Quiz quiz = quizDao.getQuizById(id);
        List<Integer> questionIds = quiz.getQuestionIds();
        List<QuestionWrapper> questionWrappers = quizInterface.getQuestions(questionIds);
        return questionWrappers;
    }

    public Boolean createQuiz(RequestQuiz requestQuiz) {
        List<Integer> questions = quizInterface.generateQuestions(requestQuiz.getCategory(), requestQuiz.getLevel(), requestQuiz.getNumQuestions());
        Quiz quiz = new Quiz();
        quiz.setTitle(requestQuiz.getTitle());
        quiz.setQuestionIds(questions);
        quiz.setAllottedTimeInMinutes(requestQuiz.getAllottedTimeInMinutes());

        quizDao.save(quiz);
        return true;
    }

    public Integer submitQuiz(Integer id, List<Answer> answers) {
        List<Boolean> evaluatedQuestions = quizInterface.evaluateQuestions(answers);
        int result = 0;
        for (Boolean evaluatedQuestion : evaluatedQuestions) {
            if (evaluatedQuestion) {
                result += 1;
            }
        }

        RequestResult requestResult = new RequestResult();
        requestResult.setScore(result);
        requestResult.setAnswers(answers);

        resultInterface.addResult(id, requestResult);

        return result;
    }
}
