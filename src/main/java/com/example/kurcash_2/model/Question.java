package com.example.kurcash_2.model;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Question {
    private final String question;
    private final String answer;

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question1 = (Question) o;

        if (!question.equals(question1.question)) return false;
        return answer.equals(question1.answer);
    }

    @Override
    public int hashCode() {
        int result = question.hashCode();
        result = 31 * result + answer.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "вопрос= " + question  +
                "ответ= " + answer  +
                '}';
    }
}
