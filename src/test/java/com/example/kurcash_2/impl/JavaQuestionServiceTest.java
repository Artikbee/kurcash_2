package com.example.kurcash_2.impl;

import com.example.kurcash2.exception.QuestionAlreadyExistsException;
import com.example.kurcash2.exception.QuestionNotFoundException;
import com.example.kurcash2.model.Question;
import com.example.kurcash2.service.QuestionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class JavaQuestionServiceTest {

    private final QuestionService questionService = new JavaQuestionService();

    @AfterEach
    public void afterEach() {
        Collection<Question> questions = questionService.getAll();
        questions.forEach(questionService::remove);
    }

    @Test
    public void addTest() {
        assertThat(questionService.getAll()).hasSize(0);

        Question expected1 = new Question("Q1", "A1");
        Question expected2 = new Question("Q2", "A2");
        questionService.add(expected1);
        questionService.add(expected2.getQuestion(), expected2.getAnswer());

        assertThat(questionService.getAll()).hasSize(2);
        assertThat(questionService.getAll()).contains(expected1, expected2);

    }

    @Test
    public void addNegativeTest() {
        assertThat(questionService.getAll()).hasSize(0);

        Question expected = new Question("Q1", "A1");

        questionService.add(expected);

        assertThat(questionService.getAll()).hasSize(1);
        assertThat(questionService.getAll()).contains(expected);

        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionService.add(expected.getQuestion(), expected.getAnswer()
                ));
    }

    @Test
    public void removeTest() {
        assertThat(questionService.getAll()).hasSize(0);

        Question expected = new Question("Q1", "A1");
        questionService.add(expected);

        assertThat(questionService.getAll()).hasSize(1);
        assertThat(questionService.getAll()).contains(expected);

        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(new Question("Q2", "A2")
                ));

        questionService.remove(expected);
        assertThat(questionService.getAll()).isEmpty();

    }

    @Test
    public void getRandomQuestionTest() {
        assertThat(questionService.getAll()).isEmpty();

        int size = 5;
        for (int i = 0; i < size; i++) {
            addOneQuestion("Q"+i,"A"+i);
        }

        assertThat(questionService.getAll()).hasSize(size);
        assertThat(questionService.getRandomQuestion()).isIn(questionService.getAll());


    }

    private Question addOneQuestion(String question, String answer) {
        int size = questionService.getAll().size();
        Question expected = new Question(question, answer);
        questionService.add(expected);

        assertThat(questionService.getAll()).hasSize(size+1);
        assertThat(questionService.getAll()).contains(expected);

        return expected;
    }
}
