package ru.otus.quizapp.ui;

import javafx.stage.Stage;
import ru.otus.quizapp.question.IQuestionService;

public class MainView {
    private final IQuestionService service;
    private final Stage primaryStage;

    public MainView(Stage primaryStage, IQuestionService service) {
        this.primaryStage = primaryStage;
        this.service = service;
    }

    public void open() {
        QuizWindow window = new QuizWindow(primaryStage, service.getAll().iterator());
        window.open();
    }

}
