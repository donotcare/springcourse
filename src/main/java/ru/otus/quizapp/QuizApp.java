package ru.otus.quizapp;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.quizapp.question.QuestionService;
import ru.otus.quizapp.question.QuestionServiceImpl;
import ru.otus.quizapp.system.i18n.LocaleMessageSource;
import ru.otus.quizapp.ui.MainView;

public class QuizApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        QuestionService service = context.getBean(QuestionServiceImpl.class);
        LocaleMessageSource messageSource = context.getBean(LocaleMessageSource.class);
        MainView view = new MainView(primaryStage, service, messageSource);
        view.open();
    }
}
