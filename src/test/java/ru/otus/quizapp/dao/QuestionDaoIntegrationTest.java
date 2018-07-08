package ru.otus.quizapp.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.otus.quizapp.AppConfiguration;
import ru.otus.quizapp.question.Question;
import ru.otus.quizapp.question.QuestionDao;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(AppConfiguration.class)
class QuestionDaoIntegrationTest {
    @Autowired
    private QuestionDao dao;

    @Test
    void daoGetAll() {
        List<Question> actual = dao.getAll();
        assertThat(actual).extracting("text").contains("What is the size of long variable?", "Can we have two main methods in a java class?");
        assertThat(actual.size()).isEqualTo(2);
    }
}
