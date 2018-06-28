package ru.otus.quizapp.question;

import java.util.Collections;
import java.util.List;

public class Question {
    private final String text;
    private final List<String> answers;
    private final String correctAnswer;

    private Question(String body, List<String> answers, String correctAnswer) {
        this.text = body;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getText() {
        return text;
    }

    public List<String> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public boolean checkAnswer(String answer) {
        return correctAnswer.equals(answer);
    }

    public static Question of(String text, List<String> answers, int correctAnswerNumber) {
        String correctAnswer = answers.get(correctAnswerNumber - 1);
        return new Question(text, answers, correctAnswer);
    }
}
