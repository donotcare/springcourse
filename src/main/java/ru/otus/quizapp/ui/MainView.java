package ru.otus.quizapp.ui;

import javafx.stage.Stage;
import ru.otus.quizapp.question.QuestionService;
import ru.otus.quizapp.system.i18n.LocaleMessageSource;

public class MainView {
    private final QuestionService service;
    private final Stage primaryStage;
    private final LocaleMessageSource localeMessageSource;

    public MainView(Stage primaryStage, QuestionService service, LocaleMessageSource localeMessageSource) {
        this.primaryStage = primaryStage;
        this.service = service;
        this.localeMessageSource = localeMessageSource;
    }

    public void open() {
        QuizWindow window = new QuizWindow(localeMessageSource, primaryStage, service.getAll().iterator());
        window.open();
    }

}
