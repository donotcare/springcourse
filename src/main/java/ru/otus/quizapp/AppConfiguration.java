package ru.otus.quizapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.quizapp.dao.CsvQuestionDao;
import ru.otus.quizapp.question.QuestionDao;
import ru.otus.quizapp.question.QuestionService;
import ru.otus.quizapp.question.QuestionServiceImpl;
import ru.otus.quizapp.system.i18n.LocaleMessageSource;

import java.util.Locale;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfiguration {
    @Bean
    public QuestionDao csvQuestionDao(@Value("#{'/${lang}_${file.csv}'}") String file, @Value("${questions}") int questionsNum) {
        return new CsvQuestionDao(file, questionsNum);
    }

    @Bean
    public QuestionService questionService(QuestionDao questionDao) {
        return new QuestionServiceImpl(questionDao);
    }

    @Bean
    public LocaleMessageSource localeMessageSource(@Value("${lang}") String language) {
        Locale locale = Locale.forLanguageTag(language);
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return new LocaleMessageSource(locale, ms);
    }
}

