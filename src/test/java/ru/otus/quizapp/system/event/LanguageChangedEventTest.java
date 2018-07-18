package ru.otus.quizapp.system.event;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.quizapp.dao.CsvQuestionDao;
import ru.otus.quizapp.question.QuestionDao;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LanguageChangedEventTest {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private QuestionDao questionDao;

    @Test
    public void daoLanguageChanged() {
        assertThat(questionDao.getAll()).extracting("text").contains("What is the size of long variable?", "Can we have two main methods in a java class?");
        eventPublisher.publishEvent(new LanguageChangedEvent("ru"));
        assertThat(questionDao.getAll()).extracting("text").contains("Какой размер у переменной типа long?");
    }

    @Configuration
    public static class AppConfig {

        @Bean
        public QuestionDao csvQuestionDao() {
            return new CsvQuestionDao("questions", "en");
        }
    }
}
