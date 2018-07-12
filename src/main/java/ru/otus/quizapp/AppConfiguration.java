package ru.otus.quizapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.quizapp.dao.CsvQuestionDao;
import ru.otus.quizapp.question.QuestionDao;
import ru.otus.quizapp.question.QuestionService;
import ru.otus.quizapp.question.QuestionServiceImpl;
import ru.otus.quizapp.system.ApplicationSettings;
import ru.otus.quizapp.system.i18n.LocaleMessageSource;

import java.util.Locale;

@Configuration
public class AppConfiguration {
    private final ApplicationSettings settings;

    public AppConfiguration(ApplicationSettings settings) {
        this.settings = settings;
    }

    @Bean
    public QuestionDao csvQuestionDao() {
        return new CsvQuestionDao(settings.getCsvFileName(), settings.getQuizLang());
    }

    @Bean
    public QuestionService questionService(QuestionDao questionDao) {
        return new QuestionServiceImpl(questionDao);
    }

    @Bean
    public LocaleMessageSource localeMessageSource() {
        Locale locale = Locale.forLanguageTag(settings.getQuizLang());
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return new LocaleMessageSource(locale, ms);
    }
}

