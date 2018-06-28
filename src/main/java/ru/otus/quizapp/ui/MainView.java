package ru.otus.quizapp.ui;

import javafx.stage.Stage;
import ru.otus.quizapp.question.QuestionService;

public class MainView {
    private final QuestionService service;
    private final Stage primaryStage;

    public MainView(Stage primaryStage, QuestionService service) {
        this.primaryStage = primaryStage;
        this.service = service;
    }

    public void open() {
        QuizWindow window = new QuizWindow(primaryStage, service.getAll().iterator());
        window.open();
    }

}
