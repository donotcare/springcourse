package ru.otus.quizapp.ui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.otus.quizapp.question.Question;

import java.util.Iterator;

public class QuizWindow {
    private final Iterator<Question> questions;
    private final Stage primaryStage;
    private int score;

    QuizWindow(Stage primaryStage, Iterator<Question> questions) {
        this.questions = questions;
        this.primaryStage = primaryStage;
    }

    public void open() {
        nextQuestion();
    }

    private void nextQuestion() {
        if (questions.hasNext()) {
            createQuestionView(questions.next());
        } else {
            StackPane root = new StackPane();
            root.getChildren().add(new Label(String.format("Your score: %s", score)));
            Scene scene = new Scene(root, 400, 250);
            primaryStage.setTitle("Java quiz");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    private void createQuestionView(Question question) {
        GridPane gridPane = createFormPane();
        Label headerLabel = new Label(question.getText());
        gridPane.add(headerLabel, 0, 0);
        int i = 1;
        final ToggleGroup group = new ToggleGroup();
        VBox verticalLayout = new VBox();
        verticalLayout.setAlignment(Pos.CENTER_LEFT);
        for (String answer : question.getAnswers()) {
            verticalLayout.getChildren().add(createRadioButton(group, answer));
        }
        gridPane.add(verticalLayout, 0, i++);

        Button checkAnswerButton = new Button("Check answer");
        checkAnswerButton.setOnAction(event -> checkAnswerAction(question, (String) group.getSelectedToggle().getUserData()));

        gridPane.add(checkAnswerButton, 0, i);

        Scene scene = new Scene(gridPane, 500, 250);
        primaryStage.setTitle("Java quiz");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void checkAnswerAction(Question question, String answer) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information");
        alert.setHeaderText(null);
        if (!question.checkAnswer(answer)) {
            alert.setContentText("Wrong answer");
        } else {
            alert.setContentText("Correct answer");
            score++;
        }
        alert.showAndWait();
        nextQuestion();
    }

    private RadioButton createRadioButton(ToggleGroup group, String answer) {
        RadioButton btn = new RadioButton(answer);
        btn.setToggleGroup(group);
        btn.setUserData(answer);
        return btn;
    }

    private GridPane createFormPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        ColumnConstraints columnOneConstraints = new ColumnConstraints(400, 400, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.CENTER);

        gridPane.getColumnConstraints().addAll(columnOneConstraints);

        return gridPane;
    }
}
