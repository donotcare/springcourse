package ru.otus.quizapp.dao;

import org.junit.jupiter.api.Test;
import ru.otus.quizapp.question.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CsvQuestionDaoTest {

    @Test
    void csvDaoGetAll(){
        CsvQuestionDao dao = new CsvQuestionDao("questions", "en");
        List<Question> actual = dao.getAll();
        assertThat(actual).extracting("text").contains("What is the size of long variable?", "Can we have two main methods in a java class?");
    }
}
