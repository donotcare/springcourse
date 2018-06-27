package ru.otus.quizapp;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.support.GenericXmlApplicationContext;
import ru.otus.quizapp.question.QuestionService;
import ru.otus.quizapp.ui.MainView;

public class QuizApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("spring-context.xml");
        QuestionService service = context.getBean(QuestionService.class);
        MainView view = new MainView(primaryStage, service);
        view.open();
    }
}