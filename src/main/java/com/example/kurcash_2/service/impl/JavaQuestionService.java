package com.example.kurcash_2.service.impl;

import com.example.kurcash2.exception.QuestionAlreadyExistsException;
import com.example.kurcash2.exception.QuestionNotFoundException;
import com.example.kurcash2.model.Question;
import com.example.kurcash2.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions;
    private final Random random;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
        this.random  = new Random();
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question,answer));
    }

    @Override
    public Question add(Question question) {

        if (!questions.add(question)) {
            throw new QuestionAlreadyExistsException();
        }

        return question;
    }

    @Override
    public Question remove(Question question) {
       if(!questions.remove(question)){
           throw new QuestionNotFoundException();
       }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(new HashSet<>(questions));
    }

    @Override
    public Question getRandomQuestion() {
        return (Question) new ArrayList(questions).get(random.nextInt(questions.size()));
    }
}
