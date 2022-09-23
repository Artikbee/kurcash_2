package com.example.kurcash_2.service.impl;

import com.example.kurcash2.exception.IncorrectAmountOfQuestionsException;
import com.example.kurcash2.model.Question;
import com.example.kurcash2.service.ExaminerService;
import com.example.kurcash2.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if(amount > questionService.getAll().size() || amount <= 0){
            throw new IncorrectAmountOfQuestionsException();
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount){
            questions.add(questionService.getRandomQuestion());
        }
        return  questions;
    }
}
