package ru.otus.quizapp.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;
import ru.otus.quizapp.question.Question;
import ru.otus.quizapp.question.QuestionService;
import ru.otus.quizapp.system.i18n.LocaleMessageSource;

import java.util.Iterator;

@Route
public class MainView extends VerticalLayout {
    private final LocaleMessageSource localeMessageSource;
    private final Iterator<Question> questions;
    private int score;

    private final RadioButtonGroup<String> group = new RadioButtonGroup<>();

    public MainView(QuestionService service, LocaleMessageSource localeMessageSource) {
        this.localeMessageSource = localeMessageSource;
        this.questions = service.getAll().iterator();
        nextQuestion();
    }

    private void nextQuestion() {
        if (questions.hasNext()) {
            createQuestionView(questions.next());
        } else {
            add(new Label(localeMessageSource.getMessage("your.score") + ": " + score));
        }
    }

    private void createQuestionView(Question question) {
        Label questionLabel = new Label(question.getText());
        group.setItems(question.getAnswers());
        Button checkAnswerBtn = new Button(localeMessageSource.getMessage("check.answer"), e -> checkAnswerAction(question, group.getValue()));
        removeAll();
        add(questionLabel, group, checkAnswerBtn);
    }

    private void checkAnswerAction(Question question, String answer) {
        if (!question.checkAnswer(answer)) {
            Notification.show(localeMessageSource.getMessage("wrong.answer"));
        } else {
            Notification.show(localeMessageSource.getMessage("correct.answer"));
            score++;
        }
        nextQuestion();
    }
}
