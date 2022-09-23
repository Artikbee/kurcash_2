package com.example.kurcash_2.service;

import com.example.kurcash2.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
