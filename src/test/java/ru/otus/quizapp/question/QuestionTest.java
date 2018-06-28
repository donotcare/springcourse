package ru.otus.quizapp.question;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestionTest {
    @Test
    void checkAnswer() {
        Question question = Question.of("Test question", Arrays.asList("A", "B"), 1);
        assertTrue(question.checkAnswer("A"), "Checking correct answer");
        assertFalse(question.checkAnswer("B"), "Checking wrong answer");
        assertFalse(question.checkAnswer("C"), "Checking answer not from question");
    }

}
