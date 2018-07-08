package ru.otus.quizapp.dao;

import org.junit.jupiter.api.Test;
import ru.otus.quizapp.question.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvQuestionDaoTest {

    @Test
    void csvDaoGetAll(){
        CsvQuestionDao dao = new CsvQuestionDao("/en_questions.csv", 2);
        List<Question> actual = dao.getAll();
        assertThat(actual).extracting("text").contains("What is the size of long variable?", "Can we have two main methods in a java class?");
    }

    @Test
    void numberOfQuestions() {
        CsvQuestionDao dao = new CsvQuestionDao("/en_questions.csv", 1);
        List<Question> actual = dao.getAll();
        assertEquals(1, actual.size());
    }
}
